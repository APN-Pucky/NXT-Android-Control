package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import java.util.Arrays;

import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketMapData extends Packet
{
	public static final short id = 9;
	int motor_b_rotation;
	double longitude;
	double latitude;
	double compass;
	double sonar;
	
	public PacketMapData(int mbr, double longitude, double latitude,double comp, double dist)
	{
		this.motor_b_rotation = mbr;
		this.longitude = longitude;
		this.latitude = latitude;
		this.compass = comp;
		this.sonar = dist;
		len = 4+8+8+8+8;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketMapData(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	@Override
	public void fillValues() 
	{
		len = getLen();
		byte[] values = getValueData();
		byte[] b_mbr = Arrays.copyOfRange(values, 0, 4);
		byte[] b_long = Arrays.copyOfRange(values, 4, 12);
		byte[] b_lat = Arrays.copyOfRange(values, 12, 20);
		byte[] b_comp = Arrays.copyOfRange(values, 20, 28);
		byte[] b_son = Arrays.copyOfRange(values, 28, 36);
		this.motor_b_rotation = BAC.toInt(b_mbr);
		this.longitude = BAC.toDouble(b_long);
		this.latitude = BAC.toDouble(b_lat);
		this.compass = BAC.toDouble(b_comp);
		this.sonar = BAC.toDouble(b_son);
	}

	@Override
	public void fillValueData() 
	{
		byte[] b_mbr = BAC.toByteArray(motor_b_rotation);
		byte[] b_long = BAC.toByteArray(longitude);
		byte[] b_lat = BAC.toByteArray(latitude);
		byte[] b_comp = BAC.toByteArray(compass);
		byte[] b_son = BAC.toByteArray(sonar);
		
		this.data[6] = b_mbr[0];
		this.data[7] = b_mbr[1];
		this.data[8] = b_mbr[2];
		this.data[9] = b_mbr[3];
		
		this.data[10] = b_long[0];
		this.data[11] = b_long[1];
		this.data[12] = b_long[2];
		this.data[13] = b_long[3];
		
		this.data[14] = b_long[4];
		this.data[15] = b_long[5];
		this.data[16] = b_long[6];
		this.data[17] = b_long[7];
		
		this.data[18] = b_lat[0];
		this.data[19] = b_lat[1];
		this.data[20] = b_lat[2];
		this.data[21] = b_lat[3];
		
		this.data[22] = b_lat[4];
		this.data[23] = b_lat[5];
		this.data[24] = b_lat[6];
		this.data[25] = b_lat[7];
		
		this.data[26] = b_comp[0];
		this.data[27] = b_comp[1];
		this.data[28] = b_comp[2];
		this.data[29] = b_comp[3];
		
		this.data[30] = b_comp[4];
		this.data[31] = b_comp[5];
		this.data[32] = b_comp[6];
		this.data[33] = b_comp[7];
		
		this.data[34] = b_son[0];
		this.data[35] = b_son[1];
		this.data[36] = b_son[2];
		this.data[37] = b_son[3];
		
		this.data[38] = b_son[4];
		this.data[39] = b_son[5];
		this.data[40] = b_son[6];
		this.data[41] = b_son[7];
	}
	
	public int getMotor_B_Rotation()
	{
		return motor_b_rotation;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getCompass()
	{
		return compass;
	}
	
	public double getSonar()
	{
		return sonar;
	}
}
