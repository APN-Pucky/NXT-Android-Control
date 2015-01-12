package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public abstract class Packet 
{
	public byte[] data;
	public int len;
	public static short id;
	
	public Packet()
	{
		
	}
	
	public Packet(byte[] data)
	{
		this.data = data;
	}
	
	public byte[] getData()
	{
		return data;
	}
	
	public void setData(byte[] data)
	{
		this.data = data;
	}
	
	public short getId()
	{
		byte[] b_short = new byte[2];
		b_short[0] = data[0];
		b_short[1] = data[1];
		return BAC.toShort(b_short);
	}
	
	public int getLen()
	{
		byte[] b_int = new byte[4];
		b_int[0] = data[2];
		b_int[1] = data[3];
		b_int[2] = data[4];
		b_int[3] = data[5];
		return BAC.toInt(b_int);
	}
	
	public byte[] getValueData()
	{
		int len = getLen();
		byte[] r = new byte[len];
		for(int i = 0; i< r.length;i++)
		{
			r[i] = data[i+6];
		}
		return r;
	}
	
	public void fillIdLenData(short id, int len)
	{
		byte[] b_id = BAC.toByteArray(id);
		byte[] b_len = BAC.toByteArray(len);
		
		this.data[0] = b_id[0];
		this.data[1] = b_id[1];
		this.data[2] = b_len[0];
		this.data[3] = b_len[1];
		this.data[4] = b_len[2];
		this.data[5] = b_len[3];
	}
	
	public abstract void fillValues();
	public abstract void fillValueData();
}
