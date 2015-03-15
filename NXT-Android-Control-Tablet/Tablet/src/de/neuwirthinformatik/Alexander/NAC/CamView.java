package de.neuwirthinformatik.Alexander.NAC;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CamView extends ImageView
{
	Bitmap bm;
	
	
	public CamView(Context context)
	{
		super(context);
	}

	public void setImage(byte[] data, int len)
	{
		bm = BitmapFactory.decodeByteArray(data, 8,len-2);
		bm = Bitmap.createScaledBitmap(bm, getWidth(), getHeight(), true);
		final CamView _this = this;
		MainActivity._this.runOnUiThread(new Runnable(){public void run(){_this.setImageBitmap(bm);}});
		//b = Bitmap.createScaledBitmap(b, getWidth(), getHeight(), true);
	}
}
