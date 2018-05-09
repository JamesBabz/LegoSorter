package main_package.BehaviorClasses;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;

public class CircleOutward implements Behavior {
	   private boolean suppressed = false;
	   private RegulatedMotor leftWheelMotor;
	   private RegulatedMotor rightWheelMotor;
	   private MovePilot pilot;
	   
	 
	public CircleOutward(Navigator nav) {
		   this.pilot = (MovePilot) nav.getMoveController();
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

	@Override
	public boolean takeControl() {
	      return true;
	   }
	

	   @Override
	public void suppress() {
	      suppressed = true;
	   }

	   @Override
	public void action() {
		     suppressed = false;
		     	pilot.arcForward(Main.turnRadius);
		     while( !suppressed) {
		        Thread.yield();
	     	 }
		     pilot.stop();
	   }}