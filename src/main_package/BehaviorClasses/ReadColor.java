package main_package.BehaviorClasses;

import java.util.HashMap;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorAdapter;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.subsumption.Behavior;

public class ReadColor implements Behavior {

	
    Port port;
    int rgb;
    EV3ColorSensor color;
    ColorAdapter adapter;
	private MovePilot pilot;
	private Navigator navigator;
	private boolean suppressed = false;
	private HashMap<Integer, Waypoint> dropoffPoints = new HashMap<>();
	
	public ReadColor(Navigator navigator, Port port, HashMap<Integer, Waypoint> dropoffPoints)
	{
		this.pilot = (MovePilot) navigator.getMoveController();
		this.port = port;
		this.navigator = navigator;
		this.dropoffPoints = dropoffPoints;
		
  		 color = new EV3ColorSensor(port);
		 adapter = new ColorAdapter(color);
   		 color.setCurrentMode("RGB");
   		 color.setFloodlight(Color.WHITE);
	}


	
	@Override
	public boolean takeControl() {
		rgb = adapter.getColorID();
		return rgb == Color.GREEN || rgb == Color.RED || rgb == Color.YELLOW || rgb == Color.BLUE;
	}

	@Override
	public void suppress() {
	      suppressed = true;
	   }

	@Override
	public void action() {
		suppressed = false;
		System.out.println("ACTION: " + navigator.getPoseProvider().getPose().getLocation().toString());
		//navigator.addWaypoint(dropoffPoints.get(rgb));
		navigator.goTo(dropoffPoints.get(rgb));
		System.out.println("DISTANCE: " + navigator.getPoseProvider().getPose().distanceTo(dropoffPoints.get(rgb)));
		    	
		    		while( !suppressed && pilot.isMoving()) {
		    	        Thread.yield();
		         	 }
		    		System.out.println("AFTER: " + navigator.getPoseProvider().getPose().getLocation().toString());
}

}
