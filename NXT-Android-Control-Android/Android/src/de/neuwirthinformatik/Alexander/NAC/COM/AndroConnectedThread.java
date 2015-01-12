package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.net.Socket;

import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.PacketHandler;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;

public class AndroConnectedThread extends Thread
{
	Socket s;
	PacketHandler ph;
	AndroClientListener acl;
	
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
		this.acl = new AndroClientListener();
	}

	@Override
	public void run() 
	{
		while(true)
		{
			Packet p = ph.readPacket();
			if(p!=null)acl.onPacket(p);
		}
	}
	
	public void sendPC(Packet p)
	{
		if(ph != null)ph.sendPacket(p);
	}

}
