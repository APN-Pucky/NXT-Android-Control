package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketFlash extends Packet
{
	public static final short id = 11;
	
	public PacketFlash() 
	{
		len = 0;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketFlash(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
	}
	
	public void fillValueData()
	{
		
	}

}
