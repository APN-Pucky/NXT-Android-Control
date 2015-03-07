package de.neuwirthinformatik.Alexander.NAC.COM;

import java.util.Locale;

import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketLang;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMute;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSpeakString;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSwapCamera;

public class AndroClientListener 
{
	public AndroClientListener()
	{
		
	}
	
	public void onPacket(Packet p)
	{
		if(p.getId() == PacketNxtCommand.id)
		{
			PacketNxtCommand pnc = (PacketNxtCommand)p;
			if(pnc.getNxtId()==1)
			{
				COM.sendNXT1(pnc);
			}
			else if(pnc.getNxtId()==2)
			{
				COM.sendNXT2(pnc);
			}
		}
		if(p.getId() == PacketStop.id)
		{
			COM.sendNXT1(p);
			COM.sendNXT2(p);
			PacketStop ps = (PacketStop)p;
			MainActivity._this.exit(ps.getCause()==-1?"Error":"Exit");
		}
		if(p.getId() == PacketSpeakString.id)
		{
			PacketSpeakString pss = (PacketSpeakString)p;
			COM.sendTTS(pss.getString());
		}
		if(p.getId() == PacketFlash.id)
		{
			MainActivity._this.mPreview.toggleFlash();
			COM.sendNXT1(p);
			COM.sendNXT2(p);
		} 
		if(p.getId() == PacketSwapCamera.id)
		{
			MainActivity._this.mPreview.swapCamera();
		}
		if(p.getId() == PacketMute.id)
		{
			MainActivity._this.mAudio.toggleMute();
		}
		if(p.getId() == PacketLang.id)
		{
			PacketLang pl = (PacketLang)p;
			COM.tts.setLanguage(new Locale(pl.getLang()));
		}
	}
}
