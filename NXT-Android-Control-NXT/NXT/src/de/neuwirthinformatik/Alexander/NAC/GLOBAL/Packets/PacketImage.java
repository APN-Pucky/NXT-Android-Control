package de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import de.neuwirthinformatik.Alexander.NAC.PORT.BufferedImage;
import de.neuwirthinformatik.Alexander.NAC.PORT.ImageIO;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketImage extends Packet
{
	public static final short id = 5;
	short cam;
	BufferedImage image;
	
	public PacketImage()
	{
		
	}
	
	public PacketImage(short cam, BufferedImage img) 
	{
		this.cam = cam;
		this.image =img;
		ByteArrayOutputStream tmp = new ByteArrayOutputStream();
	    try
	    {
	    	ImageIO.write(img, "jpg", tmp);
	    	len = tmp.size()+2;
	 	    tmp.close();
	    }
	    catch(IOException e)
	    {
	    	System.err.println(e);
			e.printStackTrace();
	    }
	    
	    this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		fillValueData();
	}
	
	public PacketImage(byte[] data)
	{
		this.data = data;
		fillValues();
	}
	
	public void fillValues()
	{
		len = getLen();
		byte[] values = getValueData();
		cam = BAC.toShort(Arrays.copyOfRange(values, 0, 2));
		image = BAC.toImage(Arrays.copyOfRange(values, 2, values.length));
	}
	
	public void fillValueData()
	{
		byte[] b_cam = BAC.toByteArray(cam);
		byte[] b_img = BAC.toByteArray(image);
		
		data[6+0] = b_cam[0];
		data[6+1] = b_cam[1];
		
		for(int i = 0; i< b_img.length;i++)
		{
			data[8+i] = b_img[i];
		}
	}
	
	public short getCam()
	{
		return cam;
	}

	public BufferedImage getImage() 
	{
		return image;
	}
}
