package de.neuwirthinformatik.Alexander.NAC.COM;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.PacketHandler;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXT;

public class BTMasterThread extends Thread
{
	NXT nxt;
	PacketHandler ph = null;
	BTMasterListener btml;
	BTConnection btc;
	
	public BTMasterThread(NXT nxt)
	{
		this.nxt = nxt;
		this.btml = new BTMasterListener(nxt);
	}
	
	public void run()
	{
		
		LCD.drawString("Waiting...", 0, 0);
		btc = Bluetooth.waitForConnection(0,NXTConnection.RAW);
		LCD.drawString("Connected...", 0, 1);
		ph = new PacketHandler(btc.openOutputStream(),btc.openInputStream());
		while(true)
		{
			Packet p = ph.readPacket();
			if(p!=null)btml.onPacket(p);
		}
	}
	
	public void sendAndro(Packet p)
	{
		if(ph != null)ph.sendPacket(p);
	}
}
