package de.neuwirthinformatik.Alexander.NAC;

import de.neuwirthinformatik.Alexander.NAC.NXT.NxtCommander;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;

public class MotionHandler
{
	int height,width;
	NxtCommander nc1,nc2;
	
	public MotionHandler()
	{
		Display display = MainActivity._this.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		nc1 = new NxtCommander((short) 1);
		nc2 = new NxtCommander((short) 2);
	}
	
	public void reset()
	{
		nc1.rotateRst();
		nc2.rotateRst();
	}
	
	public void stop()
	{
		nc1.stop();
	}
	
	public void faster()
	{
		nc1.faster();
	}
	
	public void slower()
	{
		nc1.slower();
	}
	
	public int getSpeed()
	{
		return nc1.getSpeed();
	}
	
	public void swipe(float x1,float y1,float x2,float y2)
	{
		float dx=x2-x1;
		float dy=y2-y1;
		boolean middle = false;
		if(dx!=0)
		{
			float angle=(float)Math.toDegrees(Math.atan(Math.abs(dy)/Math.abs(dx)));
			middle = 67.5>angle && angle>22.5;
		}
		
		
		if(x1<(width/2))
		{
			if(middle)
			{
				if(dx>0&&dy<0)
				{
					nc1.forwardRight();
					Log.e("","Right up");
				}
				else if(dx>0&&dy>0)
				{
					nc1.backwardRight();
					Log.e("","Right down");
				}
				if(dx<0&&dy<0)
				{
					nc1.forwardLeft();
					Log.e("","LEFT up");
				}
				else if(dx<0&&dy>0)
				{
					nc1.backwardLeft();
					Log.e("","LEFt down	");
				}
			}
			else
			{
				if(Math.abs(dx)>Math.abs(dy))
				{
					if(dx>0)
					{
						nc1.right();
						Log.e("","RIGHT");
					}
					else
					{
						nc1.left();
						Log.e("","LEFT");
					}
				}
				else
				{
					if(dy<0)
					{
						nc1.forward();
						Log.e("","UP");
					}
					else
					{
						nc1.backward();
						Log.e("","DOWN");
					}
				}
			}
		}
		else
		{
			if(Math.abs(dx)>Math.abs(dy))
			{
				if(dx>0)
				{
					nc1.rotateR();
					Log.e("","RIGHT ROT");
				}
				else
				{
					nc1.rotateL();
					Log.e("","LEFT ROT");
				}
			}
			else
			{
				if(dy<0)
				{
					nc2.rotateH();
					Log.e("","UP ROT");
				}
				else
				{
					nc2.rotateD();
					Log.e("","DOWN ROT");
				}
			}
		}
		
	}
}
