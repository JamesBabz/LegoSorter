package main_package;

import java.util.HashMap;
import java.util.Iterator;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
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
	private final static MovePilot PILOT = new MovePilot(WHEEL_DIAMETER_MM, DISTANCE_BETWEEN_WHEEL_CENTER_MM, LEFT_WHEEL_MOTOR, RIGHT_WHEEL_MOTOR);
	private final static Navigator NAVIGATOR = new Navigator(PILOT);
	private final static Waypoint GREEN_WP = new Waypoint(500, 0);
	private final static Waypoint RED_WP = new Waypoint(0, 500);
	private final static Waypoint BLUE_WP = new Waypoint(500, 500);
	private final static Waypoint YELLOW_WP = new Waypoint(0, 0);
	
	public static int startRadius = 50;
	public static int turnRadius = 50;
	
	static Port colorSensorPort = LocalEV3.get().getPort("S1");
	static Port gyroscopePort = LocalEV3.get().getPort("S3");
	
	
	 public static void main(String[] args) throws Exception
	 {
		 HashMap<Integer, Waypoint> dropoffPoints = getDropoff();
		 
		 
		 
		  Behavior b1 = new CircleOutward(NAVIGATOR);
		  Behavior b2 = new GetAngle(NAVIGATOR, gyroscopePort);
		  Behavior b3 = new EscapeKeyPressed();
		  Behavior b4 = new ReadColor(NAVIGATOR, colorSensorPort, dropoffPoints);
	      Behavior [] bArray = {b1, b2, b4, b3};
	      Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
	      
	 }


	private static HashMap<Integer, Waypoint> getDropoff() {
		 GREEN_WP.setMaxPositionError(50);
		 RED_WP.setMaxPositionError(50);
		 BLUE_WP.setMaxPositionError(50);
		 YELLOW_WP.setMaxPositionError(50);
		 HashMap<Integer, Waypoint> dropoffPoints = new HashMap<>();
		 dropoffPoints.put(Color.GREEN, GREEN_WP);
		 dropoffPoints.put(Color.RED, RED_WP);
		 dropoffPoints.put(Color.BLUE, BLUE_WP);
		 dropoffPoints.put(Color.YELLOW, YELLOW_WP);
		 return dropoffPoints;
	}
}

