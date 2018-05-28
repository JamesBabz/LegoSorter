package main_package.BehaviorClasses;

import java.util.HashMap;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorAdapter;
import lejos.robotics.GyroscopeAdapter;
import lejos.robotics.geometry.Point;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.subsumption.Behavior;
import main_package.PilotService;

public class ReadColor implements Behavior {

	
    Port port;
    int rgb;
    ColorAdapter colorAdapter;
  	GyroscopeAdapter gyroAdapter;
	private MovePilot pilot;
	private Navigator navigator;
	private boolean suppressed = false;
	private HashMap<Integer, Waypoint> dropoffPoints = new HashMap<>();
	private PilotService pilotService;
	private Waypoint currentPos;
	private boolean goingToWP = false;
	
	public ReadColor(EV3ColorSensor colorSensor, EV3GyroSensor gyroSensor)
	{
		this.pilotService = PilotService.getInstance();
		this.dropoffPoints = pilotService.getDropoff();
		this.navigator = pilotService.getNavigator();
		this.pilot = pilotService.getPilot();
    	this.gyroAdapter = new GyroscopeAdapter(gyroSensor, 360);
		
		 colorAdapter = new ColorAdapter(colorSensor);
		 colorSensor.setCurrentMode("RGB");
		 colorSensor.setFloodlight(Color.WHITE);
	}


	
	@Override
	public boolean takeControl() {
		rgb = colorAdapter.getColorID();
		return rgb == Color.GREEN || rgb == Color.RED || rgb == Color.YELLOW || rgb == Color.BLUE;
	}

	@Override
	public void suppress() {
	      suppressed = true;
	   }

	@Override
	public void action() {
		suppressed = false;
		if(navigator.pathCompleted()) {
			if(goingToWP) {
				pilot.travel(-100);
				navigator.addWaypoint(currentPos);
				System.out.println(currentPos.toString());
			}else {
				currentPos = new Waypoint(navigator.getPoseProvider().getPose());
				navigator.addWaypoint(dropoffPoints.get(rgb));
				
			}
			goingToWP = !goingToWP;
			navigator.followPath();
		}
			
		    		while( !suppressed) {
		    	        Thread.yield();
		         	 }
}

}
