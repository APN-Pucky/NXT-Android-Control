package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketAudio;

public class COM 
{
	static AndroConnectedThread ac;
	static Speaker s;
	
	public static void setup(AndroConnectedThread act)
	{
		ac = act;
		s = new Speaker();
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
		if(s != null)
		{
			s.close();
		}
		ac = null;
		s = null;
	}
	
	public static void sendAndro(Packet p)
	{
		if(ac != null)ac.sendAndro(p);
	}

	public static void sendSpeaker(byte[] audio) 
	{
		if(s != null)s.speak(audio);
	}
}
