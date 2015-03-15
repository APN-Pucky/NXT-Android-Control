package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import java.util.Arrays;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtData;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketNxtData extends Packet
{
	public static final short id = 7;
	NxtData nd;
	
	public PacketNxtData(NxtData n) 
	{
		this.nd =n;
		len = 2+8+8;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketNxtData(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		byte[] values = getValueData();
		byte[] b_type = Arrays.copyOfRange(values, 0, 2);
		byte[] b_add1 = Arrays.copyOfRange(values, 2, 10);
		byte[] b_add2 = Arrays.copyOfRange(values, 10, 18);
		nd = new NxtData(BAC.toShort(b_type),BAC.toDouble(b_add1),BAC.toDouble(b_add2));
	}
	
	public void fillValueData()
	{
		byte[] b_type = BAC.toByteArray(nd.getType());
		byte[] b_add1 = BAC.toByteArray(nd.getAdd()[0]);
		byte[] b_add2 = BAC.toByteArray(nd.getAdd()[1]);
		
		this.data[6] = b_type[0];
		this.data[7] = b_type[1];
		this.data[8] = b_add1[0];
		this.data[9] = b_add1[1];
		this.data[10] = b_add1[2];
		this.data[11] = b_add1[3];
		this.data[12] = b_add2[0];
		this.data[13] = b_add2[1];
		this.data[14] = b_add2[2];
		this.data[15] = b_add2[3];
	}
	
	public NxtData getNxtData()
	{
		return nd;
	}
}
