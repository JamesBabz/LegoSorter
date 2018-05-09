package main_package.BehaviorClasses;



import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.GyroscopeAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import main_package.Main;

public class GetAngle implements Behavior{
	  	private EV3GyroSensor sensor;
	  	private GyroscopeAdapter adapter;
	    private boolean suppressed = false;
	    private Port port;
	    private MovePilot pilot;
	    private int maxRange = 30;
		private boolean hasExtended;
		private int increaseAmount = 1;
	    
	    public GetAngle(MovePilot pilot, Port port) {
	    	this.port = port;
	    	this.pilot = pilot;
	    	this.sensor = new EV3GyroSensor(port);
	    	this.adapter = new GyroscopeAdapter(sensor, 360);
	    	
	    }

	    @Override
		public boolean takeControl() {
//	    	if(this.adapter.getAngle() >= 270 && !hasExtended)
//	    	{
//	    		return true;
//	    	}
//
//	    	return this.adapter.getAngle() >= 360;
	    	
	    	
	    	
	    	return this.adapter.getAngle() >= 90;
	    }

	    @Override
		public void suppress() {
	       suppressed = true;
	    }

	    @Override
		public void action() {
	    	suppressed = false;
	    	System.out.println(this.adapter.getAngle());
	    	this.adapter.reset();
//	    	if(!hasExtended)
//	    	{
	    		
//	    		hasExtended = true;
	    		pilot.travel(40);
//	    		suppress();
//	    		return;
//	    	}
	    	
	    	Main.turnRadius = Main.turnRadius+increaseAmount;
//	    	hasExtended = false;
//	    	System.out.println("ACTION");
	    	
	    	
	    	

	    }
}
