package de.neuwirthinformatik.Alexander.NAC.COM;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class Speaker
{
	private static int sampleRate = 44100;
	private static int channelConfig = AudioFormat.CHANNEL_OUT_MONO;    
	private static int audioFormat = AudioFormat.ENCODING_PCM_16BIT; 
	int minBufSize = AudioTrack.getMinBufferSize(sampleRate, channelConfig, audioFormat)+1024*8;
	byte[] buffer = new byte[minBufSize];
	private AudioTrack at;
	
	public Speaker()
	{
		at = new AudioTrack(AudioManager.STREAM_MUSIC,sampleRate,channelConfig,audioFormat,minBufSize,AudioTrack.MODE_STREAM);
		at.play();
	}
	
	public void speak(byte[] audio) 
	{
		try 
		{
	        at.write(audio, 0, audio.length);
	    } 
		catch (Exception e) 
		{
	        Log.e("SPEAKER","Bug in speakers...");
	        e.printStackTrace();
	    }
	}
	
	public void close()
	{
		at.stop();
		at.release();
	}

}
