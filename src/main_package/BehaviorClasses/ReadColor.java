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

		
		 color = new EV3ColorSensor(port);
		 color.setCurrentMode("RGB");
		 
		 
		  
	
		
		    while (Button.ESCAPE.isUp())
		    {
	    //   System.out.println("RED: " + rgb.getRed() + " GREEN: " + rgb.getGreen() + " BLUE: " + rgb.getBlue());
	        Delay.msDelay(250);
	        if (rgb == Color.GREEN) // color is red
			{
	        	  rgb = adapter.getColorID();
	  		    color.setFloodlight(Color.WHITE);
	  		    adapter = new ColorAdapter(color);
	        	
			 pilot.stop();
			 Motor.B.setSpeed(50);
			 Motor.B.rotateTo(150);
			 Motor.B.stop();
			 pilot.travel(-30);
			 Motor.B.setSpeed(50);
			 Motor.B.rotateTo(0);
			 Motor.B.stop();
			}

		    		System.out.println(rgb);
		    }
		    	
		    		while( !suppressed ) {
		    	        Thread.yield();
		         	 }
}

}
