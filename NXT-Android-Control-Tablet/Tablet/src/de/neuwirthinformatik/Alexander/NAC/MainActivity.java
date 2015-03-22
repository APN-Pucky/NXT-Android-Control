package de.neuwirthinformatik.Alexander.NAC;

import java.io.IOException;
import java.net.ServerSocket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.neuwirthinformatik.Alexander.NAC.COM.AndroServerThread;
import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketLang;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMute;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSpeakString;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSwapCamera;

public class MainActivity extends Activity
{
	public static MainActivity _this;
	public static final int PORT = 3070;
	public static final long millirepaint = 33;
	public CamView camview;
	public InfoText infotext;
	public MotionHandler motionhandler;
	
	boolean camera_f = true;
	boolean mute = false;
	boolean flash = false;
	String lang = "de_DE";
	double sonar = 255;
	double lat = 51.901332;
	double lon = 7.64007;
	double com = 0;
	double rot = 0;
	
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
		camview = new CamView((ImageView) findViewById(R.id.image_view));
		infotext = new InfoText((TextView)findViewById(R.id.text_info));
        //camview.setImageDrawable(this.getWallpaper());
        motionhandler = new MotionHandler();
        
        infotext.update();
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
	
	public void toggleMute()
	{
		mute = !mute;
		COM.sendAndro(new PacketMute());
		infotext.update();
	}

	public void toggleFlash() 
	{
		flash = !flash;
		COM.sendAndro(new PacketFlash());
		infotext.update();
	}

	public void toggleCamera() 
	{
		camera_f = !camera_f;
		COM.sendAndro(new PacketSwapCamera());
		infotext.update();
	}
	
	public void updatePosition(double lat, double lon, double compass, double rot,double sonar)
	{
		this.lat = lat;
		this.lon = lon;
		this.com = compass;
		this.rot = rot;
		this.sonar = sonar;
	}
	
	
	public void changeLang()
	{
		switch(lang)
		{
			case("de_DE"):lang = "en_GB";COM.sendAndro(new PacketLang(lang));break;
			case("en_GB"):lang = "es_ES";COM.sendAndro(new PacketLang(lang));break;
			case("es_ES"):lang = "de_DE";COM.sendAndro(new PacketLang(lang));break;
		}
		infotext.update();
	}
	
	public boolean getFrontCamera()
	{
		return camera_f;
	}
	
	public boolean getFlash()
	{
		return flash;
	}
	
	public boolean getMute()
	{
		return mute;
	}
	
	public String getLang()
	{
		return lang;
	}
	
	public double getSonar()
	{
		return sonar;
	}
	
	public double getLatitude()
	{
		return lat;
	}
	
	public double getLongitude()
	{
		return lon;
	}
	
	public double getCompass()
	{
		return com;
	}
	
	public int getSpeed()
	{
		return motionhandler.getSpeed();
	}
	
	public void STOP(View view)
	{
		motionhandler.stop();
	}
	
	public void RESET(View view)
	{
		motionhandler.reset();
	}
	
	public void FLASH(View view)
	{
		toggleFlash();
	}
	
	public void CAMERA(View view)
	{
		toggleCamera();
	}
	
	public void AUDIO(View view)
	{
		toggleMute();
	}
	
	public void LANG(View view)
	{
		changeLang();
	}
	
	public void FASTER(View view)
	{
		motionhandler.faster();
		infotext.update();
	}
	
	public void SLOWER(View view)
	{
		motionhandler.slower();
		infotext.update();
	}
	
	public void TTS(View view)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Text to Speak:");
		final EditText inhost = new EditText(this);
		builder.setView(inhost);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        String input = inhost.getText().toString();
		        COM.sendAndro(new PacketSpeakString(input));
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});
		builder.show();
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
	
	@Override
	public void onStop()
	{
		COM.sendAndro(new PacketStop((short)0));
		COM.stop();
		super.onStop();
	}
	
	
}
