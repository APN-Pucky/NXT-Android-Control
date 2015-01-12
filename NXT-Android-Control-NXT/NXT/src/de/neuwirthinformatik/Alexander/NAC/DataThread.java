package de.neuwirthinformatik.Alexander.NAC;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMapData;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXT;

public class DataThread extends Thread
{
	NXT nxt;
	
	public DataThread(NXT nxt)
	{
		this.nxt = nxt;
	}
	public void run()
	{
		while(true)
		{
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			LCD.drawString(""+ nxt.getDistance(), 0, 7);
			COM.sendAndro(new PacketMapData((((Motor.B.getTachoCount()%360)+360)%360),-1,-1,-1,nxt.getDistance()));
		}
	}
	
}
