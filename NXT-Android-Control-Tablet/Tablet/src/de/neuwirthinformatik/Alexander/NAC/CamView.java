package de.neuwirthinformatik.Alexander.NAC;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CamView
{
	Bitmap bm;
	ImageView iv;

	public CamView(ImageView iv)
	{
		this.iv = iv;
	}

	public void setImage(byte[] data, int len)
	{
		bm = BitmapFactory.decodeByteArray(data, 8,len-2);
		bm = Bitmap.createScaledBitmap(bm, iv.getWidth(), iv.getHeight(), true);
		MainActivity._this.runOnUiThread(new Runnable(){public void run(){iv.setImageBitmap(bm);}});
		//b = Bitmap.createScaledBitmap(b, getWidth(), getHeight(), true);
	}

	public void setImageDrawable(Drawable wallpaper)
	{
		iv.setImageDrawable(wallpaper);
		
	}
}
