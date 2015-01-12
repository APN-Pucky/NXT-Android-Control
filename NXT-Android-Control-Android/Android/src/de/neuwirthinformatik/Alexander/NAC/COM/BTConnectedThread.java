package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.PacketHandler;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;

public class BTConnectedThread extends Thread
{
	PacketHandler ph;
	BluetoothSocket bs;
	BTClientListener btcl;
	
	public BTConnectedThread(BluetoothSocket bs)
	{                 
		this.bs = bs;
		try 
		{
			ph = new PacketHandler(bs.getOutputStream(),bs.getInputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Log.e("BTCT","ggf. fatal!");
		}
		btcl = new BTClientListener();
	}
	
	public void run()
	{
		while(true)
		{
			Packet p = ph.readPacket();
			if(p!=null)btcl.onPacket(p);
		}
	}
	
	public void sendNXT(Packet p)
	{
		//Log.e("btct",""+System.nanoTime());
		if(ph != null)ph.sendPacket(p);
	}
}
