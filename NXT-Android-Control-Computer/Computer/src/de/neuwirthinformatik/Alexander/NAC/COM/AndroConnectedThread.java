package de.neuwirthinformatik.Alexander.NAC.COM;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.PacketHandler;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketString;
import de.neuwirthinformatik.Alexander.NAC.GUI.ImageGUI;
import de.neuwirthinformatik.Alexander.NAC.GUI.KeyAndMouseListenerIG;
import de.neuwirthinformatik.Alexander.NAC.GUI.WindowListenerIG;
import de.neuwirthinformatik.Alexander.NAC.NXT.NxtCommander;

public class AndroConnectedThread extends Thread
{
	Socket s;
	PacketHandler ph;
	AndroConnectedListener apl;
	ImageGUI ig;
	
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
		NxtCommander nc = new NxtCommander();
		ig = new ImageGUI(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height,15,nc);
		apl = new AndroConnectedListener(ig);
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
