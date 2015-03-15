package de.neuwirthinformatik.Alexander.NAC;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketAudio;

public class AudioThread extends Thread
{
	private int sampleRate = 44100;
	private int channelConfig = AudioFormat.CHANNEL_IN_MONO;    
	private int audioFormat = AudioFormat.ENCODING_PCM_16BIT; 
	int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)+1024*8;
	byte[] buffer = new byte[minBufSize];
	boolean running = true;
	boolean mute = false;
	
	public void run()
	{
		AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRate,channelConfig,audioFormat,minBufSize); 
		recorder.startRecording();
		while(running) 
		{
            minBufSize = recorder.read(buffer, 0, buffer.length);
            if(!mute)COM.sendPC(new PacketAudio(-1,buffer));
        }
		recorder.stop();
		recorder.release();
	}
	
	public void toggleMute()
	{
		mute = !mute;
	}
	
	public void close()
	{
		this.stop();
		running = false;
	}
}
