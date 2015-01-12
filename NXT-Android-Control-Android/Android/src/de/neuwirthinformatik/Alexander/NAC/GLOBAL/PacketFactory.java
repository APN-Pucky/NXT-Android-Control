package de.neuwirthinformatik.Alexander.NAC.GLOBAL;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketAudio;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketDouble;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketInteger;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketLang;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMapData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMute;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtData;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketShort;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSpeakString;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketString;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSwapCamera;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketFactory 
{
	public static Packet createPacket(byte[] data)
	{
		byte[] b_short = new byte[2];
		
		b_short[0] = data[0];
		b_short[1] = data[1];
		
		short id = BAC.toShort(b_short);
		
		return constructPacket(id,data);
	}
	
	public static Packet constructPacket(short id, byte[] data)
	{
		switch(id)
		{
			case(1):return new PacketShort(data); 
			case(2):return new PacketInteger(data); 
			case(3):return new PacketDouble(data); 
			case(4):return new PacketString(data); 
			case(5):return new PacketImage(data); 
			case(6):return new PacketNxtCommand(data); 
			case(7):return new PacketNxtData(data);
			case(8):return new PacketStop(data);
			case(9):return new PacketMapData(data);
			case(10):return new PacketSpeakString(data);
			case(11):return new PacketFlash(data);
			case(12):return new PacketSwapCamera(data);
			case(13):return new PacketAudio(data);
			case(14):return new PacketMute(data);
			case(15):return new PacketLang(data);
		}
		return null;
	}
}
