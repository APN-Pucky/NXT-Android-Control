package de.neuwirthinformatik.Alexander.NAC.NXT;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketNxtCommand;

public class NxtCommander 
{
	short id;
	int b_rot = 0;
	int lastspeed = 3;
	
	public NxtCommander(short id)
	{
		this.id = id;
	}
	
	public void exec(NxtCommand nc)
	{
		COM.sendAndro(new PacketNxtCommand(nc,id));
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
	
	public int getSpeed()
	{
		return lastspeed;
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
	
	public void rotateL()
	{
		exec(new NxtCommand((short) 7, -45*7));//-45°
	}
	
	public void rotateR()
	{
		exec(new NxtCommand((short) 7, 45*7));//45°
	}
	
	public void rotateD()
	{
		if(b_rot<54)
		{
			exec(new NxtCommand((short) 7, 18*20));//18°
			b_rot+=18;
		}
	}
	
	public void rotateH()
	{
		if(b_rot>-90)
		{
			exec(new NxtCommand((short) 7, -18*20));//-18°
			b_rot-=18;
		}
	}
	
	public void rotateRst()
	{
		b_rot = 0;
		exec(new NxtCommand((short)8,0));
	}
}
