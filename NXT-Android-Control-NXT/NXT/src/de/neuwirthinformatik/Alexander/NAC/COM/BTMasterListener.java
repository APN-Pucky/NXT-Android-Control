package de.neuwirthinformatik.Alexander.NAC.COM;

import lejos.nxt.LCD;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketString;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXT;

public class BTMasterListener 
{
	NXT nxt;
	int i=0;
	
	public BTMasterListener(NXT nxt)
	{
		this.nxt = nxt;
	}
	
	public void onPacket(Packet p)
	{
		if(p.getId() == PacketNxtCommand.id)
		{
			PacketNxtCommand pnc = (PacketNxtCommand)p;
			i++;
			LCD.drawString(""+i, 0, 4);
			nxt.execComand(pnc.getNxtComand());
		}
		if(p.getId() == PacketString.id)
		{
			LCD.drawString(((PacketString)p).getString(), 0, 6);
		}
		if(p.getId() == PacketStop.id)
		{
			PacketStop ps = (PacketStop)p;
			COM.stop();
			System.exit(ps.getCause());
		}
		if(p.getId() == PacketFlash.id)
		{
			nxt.toggleFlash();
		}
	}
}
