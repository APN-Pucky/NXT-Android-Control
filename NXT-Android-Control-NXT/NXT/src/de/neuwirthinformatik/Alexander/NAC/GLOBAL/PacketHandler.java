package de.neuwirthinformatik.Alexander.NAC.GLOBAL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketHandler 
{
	OutputStream os;
	InputStream is;
	static final int buffer_size = 1024;
	
	public PacketHandler(OutputStream os, InputStream is)
	{
		this.os = os;
		this.is = is;
	}
	
	public void sendPacket(Packet p)
	{
		try 
		{
			synchronized(os)
			{
				os.write(p.getData());
				os.flush();
			}
		} 
		catch (IOException e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public Packet readPacket()
	{
		Packet p = null;
		try 
		{
			byte[] bytes;
			synchronized(is)
			{
				bytes = readBytePacketArray();
			}
			if(bytes != null)p = PacketFactory.createPacket(bytes);
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
			System.exit(1);
		}
		return p;
	}
	
	public byte[] readBytePacketArray() throws IOException
	{
		byte[] b_short = new byte[2];
		byte[] b_int = new byte[4];
		
		b_short[0] = (byte)is.read();
		b_short[1] = (byte)is.read();
		b_int[0] = (byte)is.read();
		b_int[1] = (byte)is.read();
		b_int[2] = (byte)is.read();
		b_int[3] = (byte)is.read();
		
		//short id = BAC.toShort(b_short);
		int len = BAC.toInt(b_int);
		
		
		byte[] data = new byte[6+len];
		
		byte[] buffer = new byte[buffer_size];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len_read=0;
 		int total_len_read=0;
 		
 		
 		while (total_len_read + buffer_size <= len) 
 		{
 			len_read = is.read(buffer);
 			total_len_read += len_read;
 			bos.write(buffer, 0, len_read);
 		}
 		while(total_len_read < len) 
 		{
 			byte[] tmp_buffer = new byte[len-total_len_read];
 			len_read = is.read(tmp_buffer);
 			bos.write(tmp_buffer, 0, len_read);
 			total_len_read += len_read;
 		}
 		
 		
 		
		data[0] = b_short[0];
		data[1] = b_short[1];
		data[2] = b_int[0];
		data[3] = b_int[1];
		data[4] = b_int[2];
		data[5] = b_int[3];
		byte[] tmp_data = bos.toByteArray();
		for(int i = 0; i < len;i++)
		{
			data[6+i] = tmp_data[i];
		}
		return data;
	}
	
	public void close() throws IOException
	{
		os.close();
		is.close();
	}
}
