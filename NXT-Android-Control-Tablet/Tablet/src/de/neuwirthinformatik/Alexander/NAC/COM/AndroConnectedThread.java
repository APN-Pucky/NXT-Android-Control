package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.net.Socket;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.PacketHandler;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.NXT.NxtCommander;

public class AndroConnectedThread extends Thread
{
	Socket s;
	PacketHandler ph;
	AndroConnectedListener apl;
	
	public AndroConnectedThread(Socket socket)
	{
		this.s = socket;
		try 
		{
			ph = new PacketHandler(s.getOutputStream(), s.getInputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.err.println(e);
			System.exit(1);
		}
		//tmp
		apl = new AndroConnectedListener();
	}
	
	public void run()
	{
		while(true)
		{
			Packet p = ph.readPacket();
			if(p!=null)apl.onPacket(p);
		}
	}
	
	public void sendAndro(Packet p)
	{
		if(ph != null)ph.sendPacket(p);
	}
}
