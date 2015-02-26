package de.neuwirthinformatik.Alexander.NAC;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketPing;

public class PingThread extends Thread
{
	boolean running = true;

	public void run()
	{
		while (running)
		{
			try
			{
				sleep(1500);//testvalue
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			COM.sendNXT(new PacketPing());
		}
	}
}
