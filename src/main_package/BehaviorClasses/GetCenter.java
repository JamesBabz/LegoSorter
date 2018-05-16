package main_package.BehaviorClasses;

import lejos.robotics.geometry.Point;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;
import main_package.PilotService;

public class GetCenter implements Behavior {
	
	private boolean suppressed = false;
	Pose position;
	Point center;
	private MovePilot pilot;
	private PilotService pilotService;
	
	
	public GetCenter() {
		center = new Point(0, pilotService.getStartRadius());
		this.pilot = pilotService.getPilot();
	}

	@Override
	public boolean takeControl() {
		position = new Pose();
		return position.distanceTo(center) < pilotService.getTurnRadius() - 2;
	}

	@Override
	public void action() {
		suppressed = false;
		pilot.forward();
		System.out.println("action");
		
		while( !suppressed) {
	        Thread.yield();
     	 }
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}
}
