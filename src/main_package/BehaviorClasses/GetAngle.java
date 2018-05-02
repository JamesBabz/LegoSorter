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
	    
	    public GetAngle(MovePilot pilot, Port port) {
	    	this.port = port;
	    	this.pilot = pilot;
	    	this.sensor = new EV3GyroSensor(port);
	    	this.adapter = new GyroscopeAdapter(sensor, 360);
	    	
	    }

	    @Override
		public boolean takeControl() {
	    	return this.adapter.getAngle() >= 360;
	    }

	    @Override
		public void suppress() {
	       suppressed = true;
	    }

	    @Override
		public void action() {
	    	suppressed = false;
	    	this.adapter.reset();
	    	Main.turnRadius = Main.turnRadius+10;
	    }
}
