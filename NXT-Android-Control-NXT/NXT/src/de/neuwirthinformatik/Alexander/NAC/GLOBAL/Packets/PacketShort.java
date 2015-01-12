package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketShort extends Packet
{
	public static final short id = 1;
	short s;
	
	public PacketShort(short sh) 
	{
		this.s =sh;
		len = 2;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketShort(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		s = BAC.toShort(getValueData());
	}
	
	public void fillValueData()
	{
		byte[] b_short = BAC.toByteArray(s);
		
		this.data[6] = b_short[0];
		this.data[7] = b_short[1];
	}
	
	public short getShort()
	{
		return s;
	}
}
