package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;


public class PacketAudio extends Packet
{
	public static final short id = 13;
	byte[] audio;
	
	public PacketAudio(int v, byte[] audio) 
	{
		this.audio = audio;
		len = audio.length;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketAudio(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		audio = getValueData();
	}
	
	public void fillValueData()
	{
		for(int i = 0; i< audio.length;i++)
		{
			data[6+i] = audio[i];
		}
	}
	
	public byte[] getAudio()
	{
		return audio;
	}

}
