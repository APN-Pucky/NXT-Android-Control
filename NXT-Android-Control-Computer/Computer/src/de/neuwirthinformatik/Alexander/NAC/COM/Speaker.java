package de.neuwirthinformatik.Alexander.NAC.COM;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class Speaker 
{
	static AudioFormat format;
	static int sampleRate = 44100;

	static DataLine.Info dataLineInfo;
	static SourceDataLine sourceDataLine;
	
	public Speaker()
	{

	    format = new AudioFormat(sampleRate, 16, 1, true, false);
	    dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
	    try
	    {
	    	sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	    	sourceDataLine.open(format);
	    	sourceDataLine.start();
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Not working in speakers...");
	        e.printStackTrace();
	    }
	    FloatControl volumeControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
	    volumeControl.setValue(1.00f);
	}
	
	public void speak(byte[] audio) 
	{
		try 
		{
	        sourceDataLine.write(audio, 0, audio.length);
	    } 
		catch (Exception e) 
		{
	        System.out.println("Not working in speakers...");
	        e.printStackTrace();
	    }
	}
	
	public void close()
	{
		sourceDataLine.drain();
	    sourceDataLine.close();
	}

}
