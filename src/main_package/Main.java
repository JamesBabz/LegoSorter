package main_package;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import main_package.BehaviorClasses.CircleOutward;
import main_package.BehaviorClasses.EscapeKeyPressed;
import main_package.BehaviorClasses.GetAngle;
import main_package.BehaviorClasses.ReadColor;

public class Main {

	private final static float WHEEL_DIAMETER_MM = 56;
	private final static float DISTANCE_BETWEEN_WHEEL_CENTER_MM = 11.9f;
	private final static RegulatedMotor LEFT_WHEEL_MOTOR = Motor.A;
	private final static RegulatedMotor RIGHT_WHEEL_MOTOR = Motor.D;
	
	public static int turnRadius = 2;
	
	static Port colorSensorPort = LocalEV3.get().getPort("S1");
	static Port eyesPort = LocalEV3.get().getPort("S2");
	static Port gyroscopePort = LocalEV3.get().getPort("S3");
	
	private final static MovePilot pilot = new MovePilot(WHEEL_DIAMETER_MM, DISTANCE_BETWEEN_WHEEL_CENTER_MM, LEFT_WHEEL_MOTOR, RIGHT_WHEEL_MOTOR);
	
	 public static void main(String[] args) throws Exception
	 {
		 
		 
		 
		  Behavior b1 = new CircleOutward(pilot);
		  Behavior b2 = new GetAngle(pilot, gyroscopePort);
		  Behavior b3 = new EscapeKeyPressed();
		  Behavior b4 = new ReadColor(pilot, colorSensorPort);
	      Behavior [] bArray = {b1, b2, b4, b3};
	      Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
	      
	 }
}
