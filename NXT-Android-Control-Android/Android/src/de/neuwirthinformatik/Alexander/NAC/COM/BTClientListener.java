package de.neuwirthinformatik.Alexander.NAC.COM;

import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.CompassListener;
import de.neuwirthinformatik.Alexander.NAC.GPSListener;
import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMapData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketShort;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;

public class BTClientListener 
{
	public BTClientListener()
	{
		
	}
	
	public void onPacket(Packet p)
	{
		if(p.getId() == PacketStop.id)
		{
			COM.sendPC(p);
			PacketStop ps = (PacketStop)p;
			MainActivity._this.exit(ps.getCause()==-1?"Error":"Exit");
		}
		if(p.getId() == PacketMapData.id)
		{
			PacketMapData pmd = (PacketMapData)p;
			//Log.e("SONAR", "VALUE: " +pmd.getSonar());
			PacketMapData npmd = new PacketMapData(pmd.getMotor_B_Rotation(),GPSListener.getLongitude(),GPSListener.getLatitude(),CompassListener.getAzimuth(),pmd.getSonar());
			COM.sendPC(npmd);
		}
	}
}
