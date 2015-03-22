package de.neuwirthinformatik.Alexander.NAC.NXT;

import lejos.nxt.Motor;

public class DP
{
	public DP(int speed)
	{
		Motor.A.setSpeed(speed);
		Motor.C.setSpeed(speed);
	}

	public void stop()
	{
		//test
		int tmp = Motor.C.getTachoCount();
		Motor.A.stop();
		Motor.C.stop();
		Motor.C.rotateTo(tmp);
	}

	public void arcForward(int add)
	{
		if(add>0)
		{
			if(Math.abs(add)<75)
			{
				Motor.A.forward();
				Motor.C.backward();
			}
			else
			{
				Motor.C.backward();
			}
		}
		else
		{
			if(Math.abs(add)<75)
			{
				Motor.C.forward();
				Motor.A.backward();
			}
			else
			{
				Motor.A.backward();
			}
		}
	}

	public void arcBackward(int add)
	{
		if(add>0)
		{
			if(Math.abs(add)<75)
			{
				Motor.A.forward();
				Motor.C.backward();
			}
			else
			{
				Motor.A.forward();
			}
		}
		else
		{
			if(Math.abs(add)<75)
			{
				Motor.C.forward();
				Motor.A.backward();
			}
			else
			{
				Motor.C.forward();
			}
		}
	}

	public void backward()
	{
		Motor.A.forward();
		Motor.C.forward();
	}

	public void forward()
	{
		Motor.A.backward();
		Motor.C.backward();
	}

	public void setRotateSpeed(int add)
	{
		Motor.A.setSpeed(add);
		Motor.C.setSpeed(add);
	}
}
