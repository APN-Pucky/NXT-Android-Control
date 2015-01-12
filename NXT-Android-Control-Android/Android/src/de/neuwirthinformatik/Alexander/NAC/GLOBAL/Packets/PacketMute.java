package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

public class PacketMute extends Packet
{
	public static final short id = 14;
	
	public PacketMute() 
	{
		len = 0;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketMute(byte[] data)
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
