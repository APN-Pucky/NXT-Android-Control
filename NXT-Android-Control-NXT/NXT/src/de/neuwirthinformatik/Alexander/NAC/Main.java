package de.neuwirthinformatik.Alexander.NAC;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import de.neuwirthinformatik.Alexander.NAC.COM.BTMaster;
import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
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
		
		//----
		Button.waitForAnyPress();
		COM.sendAndro(new PacketStop((short) 0));
		nxt.execComand(new NxtCommand((short)8,0));//Reset B
		System.exit(0);
		
	}
}
