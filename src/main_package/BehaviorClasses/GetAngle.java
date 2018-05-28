package main_package.BehaviorClasses;



import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.GyroscopeAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import main_package.PilotService;

public class GetAngle implements Behavior{
	  	private GyroscopeAdapter adapter;
	    private boolean suppressed = false;
	    private MovePilot pilot;
		private int increaseAmount = 1;
		private PilotService pilotService;
	    
	    public GetAngle(EV3GyroSensor gyroSensor) {
	    	this.pilotService = PilotService.getInstance();
	    	this.pilot = pilotService.getPilot();
	    	this.adapter = new GyroscopeAdapter(gyroSensor, 360);
	    	
	    }

	    @Override
		public boolean takeControl() {
	    	return this.adapter.getAngle() >= 90;
	    }

	    @Override
		public void suppress() {
	       suppressed = true;
	    }

	    @Override
		public void action() {
	    	suppressed = false;
	    	this.adapter.reset();
    		pilot.travel(40);
    		this.pilotService.setTurnRadius(this.pilotService.getTurnRadius()+increaseAmount);
	    }
}
