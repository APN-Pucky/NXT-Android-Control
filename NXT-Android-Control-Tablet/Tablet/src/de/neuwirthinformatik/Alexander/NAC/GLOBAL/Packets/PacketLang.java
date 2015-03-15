package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketLang extends Packet
{


	public static final short id = 15;
	String lang;
	
	public PacketLang(String l) 
	{
		this.lang = l;
		len = lang.length();
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketLang(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues() 
	{
		len = getLen();
		lang = BAC.toString(getValueData());
	}

	public void fillValueData() 
	{
		byte[] b_string = BAC.toByteArray(lang);
		
		for(int i = 0; i< b_string.length;i++)
		{
			data[6+i] = b_string[i];
		}
	}

	public String getLang() 
	{
		return lang;
	}
}
