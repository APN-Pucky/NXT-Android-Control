package de.neuwirthinformatik.Alexander.NAC;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends Activity
{
	public static MainActivity _this;
	public CamView camview;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		_this = this;
		camview = new CamView(this);
		FrameLayout preview = (FrameLayout) findViewById(R.id.frameLayout1);
        preview.addView(camview);
        camview.setImageDrawable(this.getWallpaper());
        //init
	}
}
