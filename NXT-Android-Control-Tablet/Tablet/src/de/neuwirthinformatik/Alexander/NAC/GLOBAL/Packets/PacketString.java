package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketString extends Packet
{

	public static final short id = 4;
	String s;
	
	public PacketString(String t) 
	{
		this.s = t;
		len = t.length();
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketString(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues() 
	{
		len = getLen();
		s = BAC.toString(getValueData());
	}

	public void fillValueData() 
	{
		byte[] b_string = BAC.toByteArray(s);
		
		for(int i = 0; i< b_string.length;i++)
		{
			data[6+i] = b_string[i];
		}
	}

	public String getString() 
	{
		return s;
	}

}
