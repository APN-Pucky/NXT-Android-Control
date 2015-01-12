package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AndroServer 
{
	int port;
	ServerSocket server;
	Socket client;
	AndroServerThread ast;
	
	public AndroServer(int port)
	{
		this.port = port;
		try
		{
			server = new ServerSocket(port);
		}
		catch(IOException e)
		{
			System.err.println("Could not listen on port: " + port);
			System.err.println(e);
			System.exit(1);
		}
		if(server != null)
		{
			ast = new AndroServerThread(server);
			ast.start();
		}
	}
}
