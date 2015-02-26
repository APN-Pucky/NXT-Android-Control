package de.neuwirthinformatik.Alexander.NAC.COM;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketAudio;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMapData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketString;
import de.neuwirthinformatik.Alexander.NAC.GUI.ImageGUI;

public class AndroConnectedListener
{
	ImageGUI ig;
	
	public AndroConnectedListener(ImageGUI ig)
	{
		this.ig = ig;
	}
	
	public void onPacket(Packet p)
	{
		if(p.getId() == PacketImage.id)
		{
			PacketImage pi = (PacketImage)p;
			ig.updateImage(pi.getImage());
		}
		if(p.getId() == PacketAudio.id)
		{
			PacketAudio pa = (PacketAudio)p;
			COM.sendSpeaker(pa.getAudio());
		}
		if(p.getId() == PacketString.id)
		{
			PacketString ps = (PacketString)p;
			System.out.println("Recv String: " + ps.getString());
		}
		if(p.getId() == PacketNxtData.id)
		{
			PacketNxtData pnd = (PacketNxtData)p;
			System.out.println("Recv NxtData: " + pnd.getNxtData().getType());
		}
		if(p.getId() == PacketMapData.id)
		{
			PacketMapData pmd = (PacketMapData)p;
			ig.updatePosition(pmd.getLatitude(),pmd.getLongitude(),pmd.getCompass(),pmd.getMotor_B_Rotation(),pmd.getSonar());
			//System.out.println("Rotation Motor B: " + pmd.getMotor_B_Rotation());
			//System.out.println("Longitude: " + pmd.getLongitude() + " Latitude: " + pmd.getLatitude());
			//System.out.println("Compass: " + pmd.getCompass());
			//System.out.println("Dis: " + pmd.getSonar());
		}
		if(p.getId() == PacketStop.id)
		{
			PacketStop ps = (PacketStop)p;
			System.exit(ps.getCause());
		}
	}
}
