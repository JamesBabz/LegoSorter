package main_package.BehaviorClasses;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class EscapeKeyPressed implements Behavior {

	private boolean suppressed = false;
	private boolean exiting = false;
	
	@Override
	public boolean takeControl() {
		if(Button.ESCAPE.isDown())
			exiting = true;
		return exiting;
	}

	@Override
	public void action() {
		System.exit(0);
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
