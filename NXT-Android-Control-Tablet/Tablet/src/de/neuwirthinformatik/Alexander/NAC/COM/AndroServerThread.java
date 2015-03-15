package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AndroServerThread extends Thread
{
	ServerSocket server;
	ArrayList<AndroConnectedThread> al_ac = new ArrayList<AndroConnectedThread>();
	
	public AndroServerThread(ServerSocket server)
	{
		this.server = server;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			Socket client = null;
			try
			{
				client = server.accept();
				client.setTcpNoDelay(true);
			}
			catch(IOException e)
			{
				System.err.println("Accept failed");
				System.err.println(e);
			}
			if(client != null)
			{
				AndroConnectedThread ac = new AndroConnectedThread(client);
				al_ac.add(ac);
				COM.setup(ac);
				ac.start();
			}
		}
	}
}
