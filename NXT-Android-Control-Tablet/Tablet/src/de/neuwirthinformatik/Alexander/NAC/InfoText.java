package de.neuwirthinformatik.Alexander.NAC;

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
		String s = "Distance: " +MainActivity._this.getSonar() + "\n";
		s += "Speed: " + MainActivity._this.getSpeed()+ "\n";
		s += "Latitude: " + (MainActivity._this.getLatitude()) + "\n";
		s += "Longitude: " + (MainActivity._this.getLongitude()) + "\n";
		double c = calc(MainActivity._this.getLongitude());
		s += "Compass: " + (c>45?c>135?c>225?c>315?"N":"W":"S":"E":"N") + "\n";
		s += "Camera: " + (MainActivity._this.getFrontCamera()?"FRONT":"BACK") + "\n";
		s += "Mute: " + (MainActivity._this.getMute()?"ON":"OFF") + "\n";
		s += "Flash: " + (MainActivity._this.getFlash()?"ON":"OFF") + "\n";
		s += "Language: " + (MainActivity._this.getLang())+ "\n";
		tv.setText(s);
	}
	double calc(double angle)
	{
		return (((angle)%360)+360)%360;
	}
}
