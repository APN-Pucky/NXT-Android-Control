package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;


public class PacketStop extends Packet
{
	public static final short id = 8;
	//-1 -> err
	//0  -> norm
	short v;
	
	public PacketStop(short v) 
	{
		this.v =v;
		len = 2;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketStop(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		v = BAC.toShort(getValueData());
	}
	
	public void fillValueData()
	{
		byte[] b_short = BAC.toByteArray(v);
		
		this.data[6] = b_short[0];
		this.data[7] = b_short[1];
	}
	
	public short getCause()
	{
		return v;
	}
}