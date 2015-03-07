package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;

public class BTClient extends Thread
{
	BTConnectedThread btct;
	String mac;
	
	public BTClient(String mac)
	{
		this.mac = mac;
	}
	
	public void run()
	{
		
		boolean connected = true;
		BluetoothAdapter bta;
		BluetoothDevice btd;
		BluetoothSocket bts = null;
		//Log.e("puckylog","pre do");
		do
		{
			connected = true;
			try
			{
				bta = BluetoothAdapter.getDefaultAdapter();
				bta.cancelDiscovery();
				btd = bta.getRemoteDevice(mac);
				//Log.e("puckylog","prerf");
				//bts = btd.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
				bts = btd.createRfcommSocketToServiceRecord(UUID.randomUUID());
				//Log.e("puckylog","postrf");
				bts.connect();
				//Log.e("puckylog","postcon");
			}
			catch(IOException e)
			{
				Log.e("btc","unknown error e: " + e);
				connected = false;
			}
			
		}
		while(!connected);
		//Log.e("puckylog","post do");
		
		btct = new BTConnectedThread(bts);
		btct.start();
		
	}
	
	public void sendNXT(Packet p)
	{
		if(btct != null)btct.sendNXT(p);
	}
}
