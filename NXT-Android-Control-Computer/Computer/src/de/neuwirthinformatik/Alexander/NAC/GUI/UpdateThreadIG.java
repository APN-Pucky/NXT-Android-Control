package de.neuwirthinformatik.Alexander.NAC.GUI;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;

public class UpdateThreadIG extends Thread
{
	private long time;
	private ImageGUI ig;
	
	public UpdateThreadIG(ImageGUI ig)
	{
		this.ig = ig;
	}
	
	
	public void run() 
	{
		
		time = System.currentTimeMillis();
		
		while(ig.active)
		{
			if(ig.changed)
			{
				//float dif = System.currentTimeMillis() - time;
				//dif /= 1000;
				//double fps = 1/dif;
				//ig.setTitle(String.valueOf(fps));
				//time = System.currentTimeMillis();
				ig.changed = false;
				ig.repaint();
			}
			try 
			{
				Thread.sleep((long)ig.millirepaint);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		COM.sendAndro(new PacketStop((short)0));
		System.exit(0);
	}
}
