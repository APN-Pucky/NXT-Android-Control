package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketInteger extends Packet
{
	public static final short id = 2;
	int in;
	
	public PacketInteger(int i) 
	{
		this.in =i;
		len = 4;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketInteger(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		in = BAC.toInt(getValueData());
	}
	
	public void fillValueData()
	{
		byte[] b_int = BAC.toByteArray(in);
		
		this.data[6] = b_int[0];
		this.data[7] = b_int[1];
		this.data[8] = b_int[2];
		this.data[9] = b_int[3];
	}
	
	public int getInteger()
	{
		return in;
	}
	
}
