package de.neuwirthinformatik.Alexander.NAC;

import java.io.IOException;
import java.net.ServerSocket;

import de.neuwirthinformatik.Alexander.NAC.COM.AndroServerThread;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity
{
	public static MainActivity _this;
	public static final int PORT = 3070;
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
        initServer();
        initVol();
        initKA();
	}
	
	private void initServer()
	{
		try
		{
			new AndroServerThread(new ServerSocket(PORT)).start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			exit("Unable to start Server");
		}
	}
	
	private void initVol()
	{
		AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int amStreamMusicMaxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, amStreamMusicMaxVol, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
	}

	private void initKA() 
	{
		Window w = getWindow(); 
		 w.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
		         WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	public void exit(String text)
	{
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		finish();
	}
}
