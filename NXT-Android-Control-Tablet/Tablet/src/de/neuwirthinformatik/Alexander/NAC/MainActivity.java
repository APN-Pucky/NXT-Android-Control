package de.neuwirthinformatik.Alexander.NAC;

import java.io.IOException;
import java.net.ServerSocket;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import de.neuwirthinformatik.Alexander.NAC.COM.AndroServerThread;

public class MainActivity extends Activity
{
	public static MainActivity _this;
	public static final int PORT = 3070;
	public static final long millirepaint = 33;
	public CamView camview;
	public MotionHandler motionhandler;
	private SparseArray<float[]> pointers = new SparseArray<float[]>();
	
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
        motionhandler = new MotionHandler();
        //init
        initServer();
        initVol();
        initKA();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		switch(e.getAction() & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			for(int i = 0; i < e.getPointerCount();i++)
			{
				pointers.put(e.getPointerId(i), new float[]{e.getX(i),e.getY(i)});
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			for(int i = 0; i < e.getPointerCount();i++)
			{
				float[] fa = pointers.get(e.getPointerId(i));
				motionhandler.swipe(fa[0], fa[1], e.getX(i), e.getY(i));
			}
			break;
		}
		return false;
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
