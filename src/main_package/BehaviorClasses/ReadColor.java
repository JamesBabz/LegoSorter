package main_package.BehaviorClasses;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class ReadColor implements Behavior {

	
    Port port;
    int rgb;
    EV3ColorSensor color;
    ColorAdapter adapter;
	private MovePilot pilot;
	 private boolean suppressed = false;
	
	public ReadColor(MovePilot pilot, Port port)
	{
		this.pilot = pilot;
		this.port = port;
		
  		 color = new EV3ColorSensor(port);
		 adapter = new ColorAdapter(color);
   		 color.setCurrentMode("RGB");
   		 color.setFloodlight(Color.WHITE);
	}


	
	@Override
	public boolean takeControl() {
		 rgb = adapter.getColorID();
		return rgb == Color.GREEN;
	}

	@Override
	public void suppress() {
	      suppressed = true;
	   }

	@Override
	public void action() {
		
		suppressed = false;

		
		    	
		    		while( !suppressed && pilot.isMoving()) {
		    	        Thread.yield();
		         	 }
}

}
