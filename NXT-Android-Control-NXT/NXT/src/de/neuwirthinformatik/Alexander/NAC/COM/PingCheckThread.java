package de.neuwirthinformatik.Alexander.NAC.COM;

import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;
import de.neuwirthinformatik.Alexander.NAC.NXT.NXT;

public class PingCheckThread  extends Thread
{
	private long last_nanotime;
	boolean running = true;
	NXT nxt;
	
	public PingCheckThread(NXT nxt)
	{
		updateLastNano();
		this.nxt = nxt;
	}
	
	public void run()
	{
		while(running)
		{
			try
			{
				Thread.sleep(2500);//testvalue
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			long tmp = last_nanotime;
			updateLastNano();
			if((last_nanotime-tmp)>=2_000_000_000L)//testvalue
			{
				nxt.execComand(new NxtCommand((short)6,-1));//stop
			}
		}
	}
	
	public void updateLastNano()
	{
		last_nanotime = System.nanoTime();
	}
}
