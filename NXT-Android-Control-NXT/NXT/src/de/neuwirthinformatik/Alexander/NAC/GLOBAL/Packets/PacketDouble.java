package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketDouble extends Packet
{
	public static final short id = 3;
	double d;
	
	public PacketDouble(double d) 
	{
		this.d =d;
		len = 8;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketDouble(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		d = BAC.toDouble(getValueData());
	}
	
	public void fillValueData()
	{
		byte[] b_short = BAC.toByteArray(d);
		
		this.data[6] = b_short[0];
		this.data[7] = b_short[1];
		this.data[8] = b_short[2];
		this.data[9] = b_short[3];
		this.data[10] = b_short[4];
		this.data[11] = b_short[5];
		this.data[12] = b_short[6];
		this.data[13] = b_short[7];
	}
	
	public double getDouble()
	{
		return d;
	}
}
