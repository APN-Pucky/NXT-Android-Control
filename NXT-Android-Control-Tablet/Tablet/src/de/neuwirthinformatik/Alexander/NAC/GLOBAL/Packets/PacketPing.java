package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

public class PacketPing extends Packet
{
	public static final short id = 16;
	
	public PacketPing() 
	{
		len = 0;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketPing(byte[] data)
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
