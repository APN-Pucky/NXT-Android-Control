package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;

public class AndroClient extends Thread
{
	AndroConnectedThread act;
	String host;
	int port;
	
	public AndroClient(String host, int port)
	{
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() 
	{
		Socket server = null;
		try 
		{
			server = new Socket(host,port);
			server.setTcpNoDelay(true);
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if(server != null)
		{
			act = new AndroConnectedThread(server);
			act.start();
		}
	}
	
	public void sendPC(Packet p)
	{
		if(act != null)act.sendPC(p);
	}
}
