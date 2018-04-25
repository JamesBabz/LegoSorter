package main_package.BehaviorClasses;

import java.util.Random;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;

public class DetectWall implements Behavior{
	  	private EV3UltrasonicSensor sensor;
	    private boolean suppressed = false;
	    private Port port;
	    private RangeFinderAdapter rangeFinder;
	    private EV3UltrasonicSensor usSensor;
	    private MovePilot pilot;
	    private int reverseDistance = 20;
	    private int maxRange = 30;
	    
	    public DetectWall(MovePilot pilot, Port port) {
	    	this.port = port;
	    	usSensor = new EV3UltrasonicSensor(port);
	    	rangeFinder = new RangeFinderAdapter(usSensor.getDistanceMode());
	    	this.pilot = pilot;
	    }

	    public boolean takeControl() {
	    	return rangeFinder.getRange() < maxRange;
	    }

	    public void suppress() {
	       suppressed = true;
	    }

	    public void action() {
	    	suppressed = false;
	    	Random random = new Random();
	    	pilot.stop();
	    	Sound.beep();
	    	pilot.travel(-reverseDistance, true);
	    	while(!suppressed && pilot.isMoving())
	    		Thread.yield();
	    	pilot.rotate(random.nextInt(360), true);
	    	while(!suppressed && pilot.isMoving())
	    		Thread.yield();
	    }
}
