package main_package.BehaviorClasses;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;

public class DriveForward implements Behavior {
	   private boolean suppressed = false;
	   private DifferentialPilot pilot;
	   
	   public DriveForward(DifferentialPilot pilot) {
		   this.pilot = pilot;
	}
	   
	   public boolean takeControl() {
	      return true;
	   }

	   public void suppress() {
	      suppressed = true;
	   }

	   public void action() {
	     suppressed = false;
	     pilot.forward();
	     while( !suppressed )
	        Thread.yield();
	     pilot.stop();
	   }
	}