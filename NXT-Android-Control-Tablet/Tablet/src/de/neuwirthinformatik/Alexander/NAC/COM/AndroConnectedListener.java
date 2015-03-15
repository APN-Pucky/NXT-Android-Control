package de.neuwirthinformatik.Alexander.NAC.COM;

import java.util.Locale;

import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketLang;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMute;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSpeakString;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSwapCamera;

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
			MainActivity._this.camview.setImage(pi.getImage());
		}
	}
}
