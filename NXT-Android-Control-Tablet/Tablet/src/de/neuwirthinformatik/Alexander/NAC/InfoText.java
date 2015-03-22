package de.neuwirthinformatik.Alexander.NAC;

import android.util.Log;
import android.widget.TextView;

public class InfoText
{
	TextView tv;
	
	public InfoText(TextView tv)
	{
		this.tv = tv;
	}
	
	public void update()
	{
		double c = calc(MainActivity._this.getCompass());
		Log.e("C",""+c);
		final String s = "Distance: " +MainActivity._this.getSonar() + "\n"
			+ "Speed: " + MainActivity._this.getSpeed()+ "\n"
			+ "Latitude: " + (MainActivity._this.getLatitude()) + "\n"
			+ "Longitude: " + (MainActivity._this.getLongitude()) + "\n"
			+ "Compass: " + (c>45?c>135?c>225?c>315?"N":"W":"S":"E":"N") + "\n"
			+ "Camera: " + (MainActivity._this.getFrontCamera()?"FRONT":"BACK") + "\n"
			+ "Mute: " + (MainActivity._this.getMute()?"ON":"OFF") + "\n"
			+ "Flash: " + (MainActivity._this.getFlash()?"ON":"OFF") + "\n"
			+ "Language: " + (MainActivity._this.getLang())+ "\n";
		MainActivity._this.runOnUiThread(new Runnable(){public void run(){tv.setText(s);}});
	}
	double calc(double angle)
	{
		return (((angle)%360)+360)%360;
	}
}
