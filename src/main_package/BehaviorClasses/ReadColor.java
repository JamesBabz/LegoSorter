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
		float point = navigator.getPoseProvider().getPose().angleTo(new Point(0,0));
		System.out.println("CURR ANGLE: " + gyroAdapter.getAngle());
		System.out.println("ANGLE TO:" + point);
		int x = 0;
		while(gyroAdapter.getAngle() != Math.floor(point)) {
			navigator.rotateTo(point+x);
			System.out.println("CURR ANGLE: " + gyroAdapter.getAngle());
			x += 20;
		}
				
		    		while( !suppressed && pilot.isMoving()) {
		    	        Thread.yield();
		         	 }
}

}
