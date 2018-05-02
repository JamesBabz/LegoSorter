package main_package.BehaviorClasses;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class ReadColor implements Behavior {

	
    Port port;
    int rgb;
    EV3ColorSensor color;
    ColorAdapter adapter;
	private MovePilot pilot;
	 private boolean suppressed = false;
	
	public ReadColor(MovePilot pilot, Port port)
	{
		System.out.println(rgb);
		this.pilot = pilot;
		this.port = port;
		
  		 color = new EV3ColorSensor(port);
		 adapter = new ColorAdapter(color);
   		 color.setCurrentMode("RGB");
   		 color.setFloodlight(Color.WHITE);
   		 
	}


	
	@Override
	public boolean takeControl() {
		
	      
			
		//System.out.println(rgb);
		 rgb = adapter.getColorID();

		return rgb == Color.GREEN;
	}

	@Override
	public void suppress() {
	      suppressed = true;
	   }

	@Override
	public void action() {

		System.out.println(adapter.getColorID());
		System.out.println("FUCK ROBOT");
		
		suppressed = false;

		
		 
		 //pilot.stop();
		 Motor.B.setSpeed(50);
		 Motor.B.rotateTo(150);
		 Motor.B.stop();
		 pilot.travel(-30);
		 Motor.B.setSpeed(50);
		 Motor.B.rotateTo(0);
		 Motor.B.stop();
		    	
		    		while( !suppressed && pilot.isMoving()) {
		    	        Thread.yield();
		         	 }
}

}
