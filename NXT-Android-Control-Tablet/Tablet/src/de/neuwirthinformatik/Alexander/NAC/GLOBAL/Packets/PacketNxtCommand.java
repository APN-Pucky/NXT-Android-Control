package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import java.util.Arrays;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketNxtCommand extends Packet
{
	public static final short id = 6;
	NxtCommand nc;
	short nxt_id;
	public PacketNxtCommand(NxtCommand n, short nxt_id) 
	{
		this.nc = n;
		this.nxt_id = nxt_id;
		len = 2+4+2;
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
		byte[] b_nxt_id = Arrays.copyOfRange(values, 6, 8);
		nc = new NxtCommand(BAC.toShort(b_type),BAC.toInt(b_add));
		nxt_id = BAC.toShort(b_nxt_id);
	}
	
	public void fillValueData()
	{
		byte[] b_type = BAC.toByteArray(nc.getType());
		byte[] b_add = BAC.toByteArray(nc.getAdd());
		byte[] b_nxt_id = BAC.toByteArray(nxt_id);
		
		this.data[6] = b_type[0];
		this.data[7] = b_type[1];
		this.data[8] = b_add[0];
		this.data[9] = b_add[1];
		this.data[10] = b_add[2];
		this.data[11] = b_add[3];
		this.data[12] = b_nxt_id[0];
		this.data[13] = b_nxt_id[1];
	}
	
	public NxtCommand getNxtComand()
	{
		return nc;
	}
	
	public short getNxtId()
	{
		return nxt_id;
	}
}
