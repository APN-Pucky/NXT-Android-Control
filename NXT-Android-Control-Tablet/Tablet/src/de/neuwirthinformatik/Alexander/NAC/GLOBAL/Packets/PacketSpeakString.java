package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketSpeakString extends Packet
{


	public static final short id = 10;
	String txt;
	
	public PacketSpeakString(String s) 
	{
		this.txt = s;
		len = s.length();
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketSpeakString(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues() 
	{
		len = getLen();
		txt = BAC.toString(getValueData());
	}

	public void fillValueData() 
	{
		byte[] b_string = BAC.toByteArray(txt);
		
		for(int i = 0; i< b_string.length;i++)
		{
			data[6+i] = b_string[i];
		}
	}

	public String getString() 
	{
		return txt;
	}
}
