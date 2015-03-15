package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketSwapCamera extends Packet
{

	public static final short id = 12;
	
	public PacketSwapCamera() 
	{
		len = 0;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketSwapCamera(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		//v = BAC.toShort(getValueData());
	}
	
	public void fillValueData()
	{
		//byte[] b_short = BAC.toByteArray(v);
		
		//this.data[6] = b_short[0];
		//this.data[7] = b_short[1];
	}
}
