package de.neuwirthinformatik.Alexander.NAC.NXT;

import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.NxtCommand;

public class NXT 
{
	DifferentialPilot dp;
	NXTMotor nm;
	NxtCommandHandler nch;
	ColorSensor cs;
	UltrasonicSensor us;
	
	public NXT(double leftWheelDiameter, double rightWheelDiameter, double trackWidth, boolean reverse)
	{
		dp = new DifferentialPilot(leftWheelDiameter, rightWheelDiameter, trackWidth, Motor.A ,Motor.C, reverse);
		dp.setRotateSpeed(300);
		nch = new NxtCommandHandler(this);
		cs = new ColorSensor(SensorPort.S2);
		us = new UltrasonicSensor(SensorPort.S1);
		us.reset();
		us.continuous();
		us.setMode(UltrasonicSensor.MODE_CONTINUOUS);
		cs.setFloodlight(Color.NONE);
	}
	
	public void toggleFlash()
	{
		cs.setFloodlight(cs.isFloodlightOn() ? Color.NONE : Color.RED);
	}
	
	public double getDistance()
	{
		LCD.drawString(""+us.getRange()+ "     ", 0, 4);
		LCD.drawString(""+us.getDistance() + "      ", 0, 5);
		return (double)us.getRange();
	}
	
	public void execComand(NxtCommand nc)
	{
		nch.exec(nc);
	}
}
