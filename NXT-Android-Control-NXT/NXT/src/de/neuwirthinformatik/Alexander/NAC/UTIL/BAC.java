package de.neuwirthinformatik.Alexander.NAC.UTIL;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.neuwirthinformatik.Alexander.NAC.PORT.BufferedImage;
import de.neuwirthinformatik.Alexander.NAC.PORT.ImageIO;

public class BAC
{
    
    public static short toShort(byte[] bytes)
    {
        return (short)((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF) <<0);
    }
    
    public static int toInt(byte[] bytes) 
    {
        return (bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF) <<0;  
    }
    
    public static double toDouble(byte[] bytes)
    {
        long l = (bytes[0] & 0xFF) << 56 | (bytes[1] & 0xFF) << 48 | (bytes[2] & 0xFF) << 40 | (bytes[3] & 0xFF) << 32 | (bytes[4] & 0xFF) << 24 | (bytes[5] & 0xFF) << 16 | (bytes[6] & 0xFF) << 8 | (bytes[7] & 0xFF) <<0;
        return Double.longBitsToDouble(l);
    }
    
    public static String toString(byte[] data)
    {
        return new String(data);
    }
    
    public static BufferedImage toImage(byte[] data)
    {
        try {
			return ImageIO.read(new ByteArrayInputStream(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    
    
    
    public static byte[] toByteArray(short value) 
    {
        return new byte[] {
            (byte) ((value >> 8) & 0xFF),
            (byte) ((value >> 0) & 0xFF)};
    }
    
    public static byte[] toByteArray(int value) 
    {
        return new byte[] {
            (byte) ((value >> 24) & 0xFF),
            (byte) ((value >> 16) & 0xFF),
            (byte) ((value >> 8) & 0xFF),
            (byte) ((value >> 0) & 0xFF)};
    }

    public static byte[] toByteArray(double d) 
    {
        long l = Double.doubleToRawLongBits(d);
        return new byte[] {
            (byte)((l >> 56) & 0xFF),
            (byte)((l >> 48) & 0xFF),
            (byte)((l >> 40) & 0xFF),
            (byte)((l >> 32) & 0xFF),
            (byte)((l >> 24) & 0xFF),
            (byte)((l >> 16) & 0xFF),
            (byte)((l >> 8) & 0xFF),
            (byte)((l >> 0) & 0xFF)};
    }

    public static byte[] toByteArray(String s)
    {
        return s.getBytes();
    }
    
    public static byte[] toByteArray(BufferedImage i)
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		byte[] data = null;
 		try 
 		{
			ImageIO.write(i, "jpg", baos );
			baos.flush();
			data = baos.toByteArray();
	 		baos.close();
		} 
 		catch (IOException e) 
		{
 			System.err.println(e);
			e.printStackTrace();
		}
 		return data;
    }
}
