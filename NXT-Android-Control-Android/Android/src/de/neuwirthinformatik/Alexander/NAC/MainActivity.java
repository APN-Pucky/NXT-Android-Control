package de.neuwirthinformatik.Alexander.NAC;


import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXJCache;

public class MainActivity extends Activity 
{
	public static MainActivity _this;
	public CameraPreview mPreview;
	public AudioThread mAudio;
	public PingThread mPing;
	LocationManager locationManager;
	SensorManager sensorManager;
	CompassListener cl;
	GPSListener gpsl;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		_this = this;
		NXJCache.setupNXTCache();
		if(!supportsCamera())exit("No Front&Back Camera Support");
		
		
		SurfaceView camView = new SurfaceView(this);
		SurfaceHolder camHolder = camView.getHolder();
        FrameLayout preview = (FrameLayout) findViewById(R.id.frameLayout1);
        mPreview = new CameraPreview();
		camHolder.addCallback(mPreview);
        preview.addView(camView);
        preview.setVisibility(View.INVISIBLE);
        
        mAudio = new AudioThread();
        mAudio.start();
        
        mPing = new PingThread();
        mPing.start();
        
        initKA();
        initVol();
        initBluetooth();
        initGPS();
        initCompass();
		hostInput();
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

	private void initCompass() 
	{
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        cl = new CompassListener();
        sensorManager.registerListener(cl, mSensor,SensorManager.SENSOR_DELAY_UI);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(cl, mSensor,SensorManager.SENSOR_DELAY_UI);
	}

	private void initGPS() 
	{
		/*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER ))
		{
			Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
		}
		gpsl = new GPSListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, gpsl);*/
		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        sendBroadcast(poke);
	    }
	}

	private void initBluetooth() 
	{
		BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		if (!mBtAdapter.isEnabled()) 
		{
			mBtAdapter.enable();
			//Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			//MainActivity._this.startActivityForResult(enableBtIntent, 1);
			if (!mBtAdapter.isEnabled()) 
			{
				MainActivity._this.exit("Please enable Bluetooth");
			}
		}
	}

	private void hostInput() 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Host");
		final EditText inhost = new EditText(this);	
		inhost.setText("192.168.43.204:3070&00:16:53:13:04:18");
		builder.setView(inhost);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        String input = inhost.getText().toString();
		        if(!input.contains(":") || !input.contains("&"))exit("Wrong Format");
		        String pc = input.split("&")[0];
		        String mac = input.split("&")[1];
		        
		        String host = pc.split(":")[0];
		        String s_port = pc.split(":")[1];
		        int port = Integer.parseInt(s_port);
		        
		        start(host,port,mac);
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		        exit("Canceled");
		    }
		});
		builder.show();
	}
	
	public void start(String host, int port, String mac)
	{
		COM.setup(host, port, mac);
		//COM.sendNXT(new PacketNxtComand(new NxtComand((short)5,3)));
		//COM.sendNXT(new PacketNxtComand(new NxtComand((short)1,-1)));
	}

	private boolean supportsCamera()
	{
		return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA) && this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) && this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS);
	}
	
	public void exit(String text)
	{
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		finish();
	}
	
	@Override
	public void onStop()
	{
		COM.sendPC(new PacketStop((short)0));
		COM.sendNXT(new PacketStop((short)0));
		COM.stop();
		sensorManager.unregisterListener(cl);
		locationManager.removeUpdates(gpsl);
		super.onStop();
	}

}
