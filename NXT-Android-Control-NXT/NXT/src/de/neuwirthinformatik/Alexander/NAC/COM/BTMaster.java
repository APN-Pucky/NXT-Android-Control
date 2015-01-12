package de.neuwirthinformatik.Alexander.NAC.COM;

import lejos.nxt.comm.Bluetooth;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXT;

public class BTMaster 
{
	public BTMaster(NXT nxt)
	{
		Bluetooth.reset();
		//Bluetooth.btEnable();
		//Bluetooth.setPower(false);
		//Bluetooth.setPower(true);
		//Bluetooth.btEnable();
		//Bluetooth.reset();
		
		BTMasterThread btmt = new BTMasterThread(nxt);
		COM.setup(btmt);
		btmt.start();
	}
	
}
