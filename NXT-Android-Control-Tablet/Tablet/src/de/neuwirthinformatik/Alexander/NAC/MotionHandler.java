package de.neuwirthinformatik.Alexander.NAC;

import android.graphics.Point;
import android.util.Log;
import android.view.Display;

public class MotionHandler
{
	int height,width;
	
	public MotionHandler()
	{
		Display display = MainActivity._this.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
	}
	
	public void swipe(float x1,float y1,float x2,float y2)
	{
		if(x1<(width/2))
		{
			if(x1<x2)
			{
				Log.e("MOVE","right");
			}
			else
			{
				Log.e("MOVE","left");
			}
			if(y1<y2)
			{
				Log.e("MOVE","down");
			}
			else
			{
				Log.e("MOVE","up");
			}
		}
		else
		{
			if(x1<x2)
			{
				Log.e("ROT","right");
			}
			else
			{
				Log.e("ROT","left");
			}
			if(y1<y2)
			{
				Log.e("ROT","down");
			}
			else
			{
				Log.e("ROT","up");
			}
		}
		
	}
}
