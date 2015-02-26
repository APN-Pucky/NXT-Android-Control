package de.neuwirthinformatik.Alexander.NAC.NXT;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;

public class NxtCommander 
{
	int lastspeed;
	
	public NxtCommander()
	{
		
	}
	
	public void exec(NxtCommand nc)
	{
		COM.sendAndro(new PacketNxtCommand(nc));
	}
	
	public void forward()
	{
		exec(new NxtCommand((short)1,0));
	}
	
	public void backward()
	{
		exec(new NxtCommand((short)2,0));
	}
	
	public void right()
	{
		exec(new NxtCommand((short)3,50));
	}
	
	public void left()
	{
		exec(new NxtCommand((short)3,-50));
	}
	
	public void forwardRight()
	{
		exec(new NxtCommand((short)3,100));
	}
	
	public void forwardLeft()
	{
		exec(new NxtCommand((short)3,-100));
	}
	
	public void backwardRight()
	{
		exec(new NxtCommand((short)4,100));
	}
	
	public void backwardLeft()
	{
		exec(new NxtCommand((short)4,-100));
	}
	
	public void stop()
	{
		exec(new NxtCommand((short)6,0));
	}
	
	public void speed(int number)
	{
		lastspeed = number;
		exec(new NxtCommand((short)5,lastspeed*100));
	}
	
	public void faster()
	{
		if(lastspeed<9)
		{
			lastspeed++;
			exec(new NxtCommand((short)5,lastspeed*100));
		}
	}
	
	public void slower()
	{
		if(lastspeed>1)
		{
			lastspeed--;
			exec(new NxtCommand((short)5,lastspeed*100));
		}
	}
	
	public void rotateR()
	{
		exec(new NxtCommand((short) 7, -45));
	}
	
	public void rotateL()
	{
		exec(new NxtCommand((short) 7, 45));
	}
	
	public void rotateRst()
	{
		exec(new NxtCommand((short)8,0));
	}
}
