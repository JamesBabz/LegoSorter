package main_package.BehaviorClasses;

import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class CircleOutward implements Behavior {
	   private boolean suppressed = false;
	   private RegulatedMotor leftWheelMotor;
	   private RegulatedMotor rightWheelMotor;
	   private MovePilot pilot;
	   
	   public CircleOutward(MovePilot pilot) {
		   this.pilot = pilot;
		   this.pilot.setLinearSpeed(100);
		   this.pilot.setAngularSpeed(150);
	   }
	   
	   //Not used
	   public CircleOutward(RegulatedMotor leftWheelMotor, RegulatedMotor rightWheelMotor) {
		this.leftWheelMotor = leftWheelMotor;
		this.rightWheelMotor = rightWheelMotor;
		this.leftWheelMotor.setSpeed(100);
		this.rightWheelMotor.setSpeed(80);
	}

	public boolean takeControl() {
	      return true;
	   }
	
	private void move(int radius, int angle){
		if(!suppressed) {
			pilot.travelArc(radius, angle);
		
			//Repeat with bigger angle to make it go outwards
   	 		while(!pilot.isMoving()) {
   	 			move(radius, angle+10);
   	 		}
		}
	}

	   public void suppress() {
	      suppressed = true;
	   }

	   public void action() {
	     suppressed = false;
	     int radius = 100;
	     int angle = 360;
	     
		 move(radius, angle);
	     
	     while( !suppressed ) {
	        Thread.yield();
     	 }
	   }
	}