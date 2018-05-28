package main_package.BehaviorClasses;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import main_package.PilotService;

public class CircleOutward implements Behavior {
	   private boolean suppressed = false;
	   private MovePilot pilot;
	   private PilotService pilotService;
	   
	 
	public CircleOutward() {
		this.pilotService = PilotService.getInstance();
		   this.pilot = pilotService.getPilot();
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
		     	pilot.arcForward(pilotService.getTurnRadius());
		     while( !suppressed) {
		        Thread.yield();
	     	 }
		     pilot.stop();
	   }}