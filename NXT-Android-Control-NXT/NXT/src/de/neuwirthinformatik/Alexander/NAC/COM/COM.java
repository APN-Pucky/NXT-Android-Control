package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;

public class COM 
{
	static BTMasterThread btmt;
	
	public static void setup(BTMasterThread btm)
	{
		btmt = btm;
	}
	
	public static void stop()
	{
		if(btmt != null)
		{
			if(btmt.btc != null)
			{
				btmt.btc.close();
			}
			if(btmt.ph != null)
			{
				
				try 
				{
					btmt.ph.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		btmt = null;
	}
	
	public static void sendAndro(Packet p)
	{
		if(btmt != null)btmt.sendAndro(p);
	}
}
