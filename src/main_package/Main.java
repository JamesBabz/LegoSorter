package main_package;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorAdapter;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import main_package.BehaviorClasses.*;

public class Main {

	private final static float WHEEL_DIAMETER_MM = 56;
	private final static float DISTANCE_BETWEEN_WHEEL_CENTER_MM = 11.9f;
	private final static RegulatedMotor LEFT_WHEEL_MOTOR = Motor.A;
	private final static RegulatedMotor RIGHT_WHEEL_MOTOR = Motor.D;
	
	
	static Port port = LocalEV3.get().getPort("S1");

	
	private final static MovePilot pilot = new MovePilot(WHEEL_DIAMETER_MM, DISTANCE_BETWEEN_WHEEL_CENTER_MM, LEFT_WHEEL_MOTOR, RIGHT_WHEEL_MOTOR);
	
	 public static void main(String[] args) throws Exception
	 {
		 
		 
		 
//		  Behavior b1 = new CircleOutward(LEFT_WHEEL_MOTOR, RIGHT_WHEEL_MOTOR);
		  Behavior b1 = new CircleOutward(pilot);
//		  Behavior b2 = new DetectWall(pilot, eyesPort);
		  Behavior b3 = new EscapeKeyPressed();
		  Behavior b4 = new ReadColor(pilot, port);
//	      Behavior [] bArray = {b1, b2, b3};
	      Behavior [] bArray = {b1, b4, b3};
	      Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
	      
	 }
}
