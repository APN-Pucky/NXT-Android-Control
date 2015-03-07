package de.neuwirthinformatik.Alexander.NAC.COM;

import java.io.IOException;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import de.neuwirthinformatik.Alexander.NAC.MainActivity;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.Packet;

public class COM 
{
	static AndroClient ac;
	static BTClient btc1;
	static BTClient btc2;
	static TextToSpeech tts;
	
	public static void setup(String host, int port, String mac1, String mac2)
	{
		btc1 = new BTClient(mac1);
		btc1.start();
		btc2 = new BTClient(mac2);
		btc2.start();
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
		if(btc1 != null && btc1.btct != null )
		{
			if(btc1.btct.bs != null)
			{
				try 
				{
					btc1.btct.bs.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if(btc1.btct.ph != null)
			{
				try 
				{
					btc1.btct.ph.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		if(btc2 != null && btc2.btct != null )
		{
			if(btc2.btct.bs != null)
			{
				try 
				{
					btc2.btct.bs.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if(btc2.btct.ph != null)
			{
				try 
				{
					btc2.btct.ph.close();
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
		btc1 = null;
		btc2 = null;
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
	
	public static void sendNXT1(Packet p)
	{
		if(btc1 != null)btc1.sendNXT(p);
	}
	
	public static void sendNXT2(Packet p)
	{
		if(btc2 != null)btc2.sendNXT(p);
	}
}
