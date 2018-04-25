package main_package;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import main_package.BehaviorClasses.*;

public class Main {

	public final static Port eyesPort = LocalEV3.get().getPort("S1");
	public final static Port colorSensorPort = LocalEV3.get().getPort("S2");
	public final static DifferentialPilot pilot = new DifferentialPilot(DifferentialPilot.WHEEL_SIZE_EV3, 12.0f, Motor.A, Motor.D);
	
	 public static void main(String[] args) throws Exception
	 {
		  Behavior b1 = new DriveForward(pilot);
		  Behavior b2 = new DetectWall(pilot, eyesPort);
		  Behavior b3 = new EscapeKeyPressed();
	      Behavior [] bArray = {b1, b2, b3};
	      Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
	 }
}
