package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import java.util.Arrays;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketNxtCommand extends Packet
{
	public static final short id = 6;
	NxtCommand nc;
	
	public PacketNxtCommand(NxtCommand n) 
	{
		this.nc =n;
		len = 2+4;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketNxtCommand(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		byte[] values = getValueData();
		byte[] b_type = Arrays.copyOfRange(values, 0, 2);
		byte[] b_add = Arrays.copyOfRange(values, 2, 6);
		nc = new NxtCommand(BAC.toShort(b_type),BAC.toInt(b_add));
	}
	
	public void fillValueData()
	{
		byte[] b_type = BAC.toByteArray(nc.getType());
		byte[] b_add = BAC.toByteArray(nc.getAdd());
		
		this.data[6] = b_type[0];
		this.data[7] = b_type[1];
		this.data[8] = b_add[0];
		this.data[9] = b_add[1];
		this.data[10] = b_add[2];
		this.data[11] = b_add[3];
	}
	
	public NxtCommand getNxtComand()
	{
		return nc;
	}
}
