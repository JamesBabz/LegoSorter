package main_package;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import main_package.BehaviorClasses.CircleOutward;
import main_package.BehaviorClasses.EscapeKeyPressed;
import main_package.BehaviorClasses.GetAngle;
import main_package.BehaviorClasses.ReadColor;

public class Main {

	
	static EV3ColorSensor colorSensor = new EV3ColorSensor(LocalEV3.get().getPort("S1"));
	static EV3GyroSensor gyroSensor = new EV3GyroSensor(LocalEV3.get().getPort("S3"));
	
	 public static void main(String[] args) throws Exception
	 {
		  Behavior b1 = new CircleOutward();
		  Behavior b2 = new GetAngle(gyroSensor);
		  Behavior b3 = new EscapeKeyPressed();
		  Behavior b4 = new ReadColor(colorSensor, gyroSensor);
	      Behavior [] bArray = {b1, b2, b4, b3};
	      Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
	      
	 }


	
}

