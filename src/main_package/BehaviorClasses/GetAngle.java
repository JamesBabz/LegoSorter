package main_package.BehaviorClasses;

import java.util.Random;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;

public class GetAngle implements Behavior{
	  	private EV3GyroSensor sensor;
	    private boolean suppressed = false;
	    private Port port;
	    private MovePilot pilot;
	    private int maxRange = 30;
	    
	    public GetAngle(MovePilot pilot, Port port) {
	    	this.port = port;
	    	this.pilot = pilot;
	    }

	    public boolean takeControl() {
	    	
	    	
			return true;
	    	
	    }

	    public void suppress() {
	       suppressed = true;
	    }

	    public void action() {
	
	    }
}
