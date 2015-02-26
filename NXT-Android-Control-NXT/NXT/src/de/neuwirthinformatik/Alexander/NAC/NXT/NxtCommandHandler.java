package de.neuwirthinformatik.Alexander.NAC.NXT;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;

public class NxtCommandHandler 
{
	NXT nxt;
	
	public NxtCommandHandler(NXT nxt)
	{
		this.nxt =nxt;
	}
	
	public void exec(NxtCommand nc)
	{
		switch(nc.getType())
		{
			case(1):nxt.dp.stop();nxt.dp.forward();break;
			case(2):nxt.dp.stop();nxt.dp.backward();break;
			case(3):nxt.dp.stop();nxt.dp.arcForward(nc.getAdd());break;
			case(4):nxt.dp.stop();nxt.dp.arcBackward(nc.getAdd());break;
			case(5):nxt.dp.setRotateSpeed(nc.getAdd());break;
			case(6):nxt.dp.stop();break;
			case(7):Motor.B.rotate(nc.getAdd());break;
			case(8):Motor.B.rotateTo(0);
		}
	}
}
