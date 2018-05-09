package main_package.BehaviorClasses;

import lejos.robotics.geometry.Point;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Pose;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;

public class GetCenter implements Behavior {
	
	private boolean suppressed = false;
	Pose position;
	Point center;
	private MovePilot pilot;
	
	
	public GetCenter(MovePilot pilot) {
		center = new Point(0, Main.startRadius);
		this.pilot = pilot;
	}

	@Override
	public boolean takeControl() {
		position = new Pose();
		return position.distanceTo(center) < Main.turnRadius - 2;
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
