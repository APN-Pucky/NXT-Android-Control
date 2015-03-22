package de.neuwirthinformatik.Alexander.NAC;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

public class GPSListener implements LocationListener
{
	//predef hard code
	/*
	 * 
	 */
	//Leverkusen
	static double longitude = 6.98353;
	static double latitude = 51.0144795;
	//Hiltrup
	//static double longitude = 7.64007;
	//static double latitude = 51.901332;
	
	
	public static double getLongitude()
	{
		return longitude;
	}
	
	public static double getLatitude()
	{
		return latitude;
	}
	
	@Override
	public void onLocationChanged(Location location) 
	{
		longitude = location.getLongitude();
		latitude = location.getLatitude();
		Log.e("GPS","GPS location changed");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		if(provider == LocationManager.GPS_PROVIDER && (status == LocationProvider.OUT_OF_SERVICE || status == LocationProvider.TEMPORARILY_UNAVAILABLE))
		{
			Log.e("GPS","GPS disconnect");
		}
	}

	@Override
	public void onProviderEnabled(String provider) 
	{
		
	}

	@Override
	public void onProviderDisabled(String provider) 
	{
		Log.e("GPS","GPS disconnect");
	}
	
}
