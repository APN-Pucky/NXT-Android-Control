package de.neuwirthinformatik.Alexander.NAC;

import java.awt.Image;

import android.content.Context;
import android.widget.ImageView;

public class CamView extends ImageView
{

	public CamView(Context context)
	{
		super(context);
	}
	
	public void setImage(Image img)
	{
		Image i = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST);
	}
}
