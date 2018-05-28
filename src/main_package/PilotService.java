package main_package;

import java.util.HashMap;

import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;

public class PilotService {

	private static PilotService INSTANCE;
	
	
	private final float WHEEL_DIAMETER_MM = 56f;
	private final float DISTANCE_BETWEEN_WHEEL_CENTER_MM = 119f;
	private final float DISTANCE_FROM_CENTER_TO_WHEEL_MM = DISTANCE_BETWEEN_WHEEL_CENTER_MM/2;
	private final RegulatedMotor LEFT_WHEEL_MOTOR = Motor.A;
	private final RegulatedMotor RIGHT_WHEEL_MOTOR = Motor.D;
	private final Wheel LEFT_WHEEL = WheeledChassis.modelWheel(LEFT_WHEEL_MOTOR, WHEEL_DIAMETER_MM).offset(DISTANCE_FROM_CENTER_TO_WHEEL_MM);
	private final Wheel RIGHT_WHEEL = WheeledChassis.modelWheel(RIGHT_WHEEL_MOTOR, WHEEL_DIAMETER_MM).offset(-DISTANCE_FROM_CENTER_TO_WHEEL_MM);
	private final Chassis CHASSIS = new WheeledChassis(new Wheel[] { LEFT_WHEEL, RIGHT_WHEEL }, WheeledChassis.TYPE_DIFFERENTIAL);
	private final MovePilot PILOT = new MovePilot(CHASSIS);
	private final Navigator NAVIGATOR = new Navigator(PILOT);
	
	private final Waypoint GREEN_WP = new Waypoint(1000, 0);
	private final Waypoint RED_WP = new Waypoint(0, 1000);
	private final Waypoint BLUE_WP = new Waypoint(1000, 1000);
	private final Waypoint YELLOW_WP = new Waypoint(0, 0);
	
	private int startRadius = 500;
	private int turnRadius = 500;
	
	
	
	private PilotService() {
			startRadius = turnRadius;
			this.PILOT.setLinearSpeed(100);
			this.PILOT.setAngularSpeed(150);
	}
	
	public static PilotService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new PilotService();
		}
		return INSTANCE;
	}
	
	public MovePilot getPilot() {
		return this.PILOT;
	}
	
	public Navigator getNavigator() {
		return this.NAVIGATOR;
	}
	
	public int getTurnRadius() {
		return this.turnRadius;
	}
	
	public void setTurnRadius(int turnRadius) {
		this.turnRadius = turnRadius;
	}
	
	public int getStartRadius() {
		return this.startRadius;
	}
	
	public HashMap<Integer, Waypoint> getDropoff() {
		 this.GREEN_WP.setMaxPositionError(50);
		 this.RED_WP.setMaxPositionError(50);
		 this.BLUE_WP.setMaxPositionError(50);
		 this.YELLOW_WP.setMaxPositionError(50);
		 HashMap<Integer, Waypoint> dropoffPoints = new HashMap<>();
		 dropoffPoints.put(Color.GREEN, this.GREEN_WP);
		 dropoffPoints.put(Color.RED, this.RED_WP);
		 dropoffPoints.put(Color.BLUE, this.BLUE_WP);
		 dropoffPoints.put(Color.YELLOW, this.YELLOW_WP);
		 return dropoffPoints;
	}
	
}
