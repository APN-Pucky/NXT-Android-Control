package de.neuwirthinformatik.Alexander.NAC;

import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;
import de.neuwirthinformatik.Alexander.NAC.COM.BTMaster;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXT;

public class Main 
{
	public static void main(String[] args)
	{
		//Motor.B.rotateTo(0);
		Motor.B.resetTachoCount();
		
		
		NXT nxt = new NXT(43.2,43.2,92,true);
		new BTMaster(nxt);
		new DataThread(nxt).start();
		
	}
}
