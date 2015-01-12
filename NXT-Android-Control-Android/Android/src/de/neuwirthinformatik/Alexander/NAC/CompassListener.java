package de.neuwirthinformatik.Alexander.NAC;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class CompassListener implements SensorEventListener
{
	float[] inR = new float[16];
	float[] I = new float[16];
	float[] gravity = new float[3];
	float[] geomag = new float[3];
	float[] orientVals = new float[3];

	static double azimuth = 0;
	//double pitch = 0;
	//double roll = 0;

	public void onSensorChanged(SensorEvent sensorEvent) 
	{
	    switch (sensorEvent.sensor.getType()) 
	    {  
	        case Sensor.TYPE_ACCELEROMETER:
	            gravity = sensorEvent.values.clone();
	            break;
	        case Sensor.TYPE_MAGNETIC_FIELD:
	            geomag = sensorEvent.values.clone();
	            break;
	    }

	    if (gravity != null && geomag != null) 
	    {

	        boolean success = SensorManager.getRotationMatrix(inR, I, gravity, geomag);
	        if (success) 
	        {
	        	//SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_Z, inR);
	            SensorManager.getOrientation(inR, orientVals);
	            azimuth = Math.toDegrees(orientVals[0]);
	            azimuth += 90;
	            if(azimuth>180)azimuth = (azimuth%180)-180;
	            //pitch = Math.toDegrees(orientVals[1]);
	            //roll = Math.toDegrees(orientVals[2]);
	        }
	    }
	    //Log.e("compas","azi: " + azimuth /*+ " pitch: " + pitch + " roll: " + roll*/);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
		
	}
	
	public static double getAzimuth()
	{
		return azimuth;
	}
}

	
