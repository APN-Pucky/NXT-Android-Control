package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketAudio;

public class COM 
{
	static AndroConnectedThread ac;
	
	public static void setup(AndroConnectedThread act)
	{
		ac = act;
	}
	
	public static void stop()
	{
		if(ac !=null)
		{
			if(ac.s != null)
			{
				try 
				{
					ac.s.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if(ac.ph != null)
			{
				try 
				{
					ac.ph.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		ac = null;
	}
	
	public static void sendAndro(Packet p)
	{
		if(ac != null)ac.sendAndro(p);
	}
}
