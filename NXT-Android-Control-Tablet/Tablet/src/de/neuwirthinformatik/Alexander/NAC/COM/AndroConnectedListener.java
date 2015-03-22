package de.neuwirthinformatik.Alexander.NAC.COM;

import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketAudio;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMapData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;

public class AndroConnectedListener 
{
	public AndroConnectedListener()
	{
		
	}
	
	public void onPacket(Packet p)
	{
		
		if(p.getId() == PacketImage.id)
		{
			PacketImage pi = (PacketImage)p;
			MainActivity._this.camview.setImage(pi.data,pi.len);
		}
		if(p.getId() == PacketAudio.id)
		{
			PacketAudio pa = (PacketAudio)p;
			COM.sendSpeaker(pa.getAudio());
			
		}
		if(p.getId() == PacketMapData.id)
		{
			PacketMapData pmd = (PacketMapData)p;
			MainActivity._this.updatePosition(pmd.getLatitude(),pmd.getLongitude(),pmd.getCompass(),pmd.getMotor_B_Rotation(),pmd.getSonar());
		}
		if(p.getId() == PacketStop.id)
		{
			PacketStop ps = (PacketStop)p;
			MainActivity._this.exit(ps.getCause()==-1?"Error":"Exit");
		}
	}
}
