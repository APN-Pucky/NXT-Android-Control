package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;

public class COM 
{
	static AndroClient ac;
	static BTClient btc;
	static TextToSpeech tts;
	
	public static void setup(String host, int port, String mac)
	{
		btc = new BTClient(mac);
		btc.start();
		ac = new AndroClient(host,port);
		ac.start();
		tts = new TextToSpeech(MainActivity._this,null);
		tts.setSpeechRate(0.5F);
		//tts.setPitch(2.0F);
		tts.setLanguage(new Locale("de_DE"));
	}
	
	public static void stop()
	{
		if(ac != null && ac.act != null )
		{
			if(ac.act.s != null)
			{
				try 
				{
					ac.act.s.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if(ac.act.ph != null)
			{
				try 
				{
					ac.act.ph.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		if(btc != null && btc.btct != null )
		{
			if(btc.btct.bs != null)
			{
				try 
				{
					btc.btct.bs.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if(btc.btct.ph != null)
			{
				try 
				{
					btc.btct.ph.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		if(tts != null)
		{
			tts.shutdown();
		}
		ac = null;
		btc = null;
		tts = null;
	}
	
	public static void sendTTS(String text)
	{
		if(tts != null)tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	
	public static void sendPC(Packet p)
	{
		if(ac != null)ac.sendPC(p);
	}
	
	public static void sendNXT(Packet p)
	{
		if(btc != null)btc.sendNXT(p);
	}
}
