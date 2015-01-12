package de.neuwirthinformatik.Alexander.NAC.PORT;

import java.io.ByteArrayOutputStream;

import android.graphics.Rect;
import android.graphics.YuvImage;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.UTIL.BAC;

public class PacketAndroImage extends PacketImage
{
	
	
	public PacketAndroImage(short cam, YuvImage img)
	{
		super();
		ByteArrayOutputStream tmp = new ByteArrayOutputStream();
		img.compressToJpeg(new Rect(0, 0, img.getWidth(), img.getHeight()), 90, tmp);
		len = tmp.size()+2;
		this.data=new byte[2+4+len];
		fillIdLenData(id,len);
		System.arraycopy(BAC.toByteArray(cam), 0, data, 6, 2);
		System.arraycopy(tmp.toByteArray(), 0, data, 8, len-2);
	}
}
