package de.neuwirthinformatik.Alexander.NAC.GLOBAL;

public class NxtCommand 
{
	/*
	 * 1=Forward
	 * 2=Backward
	 * 3=ForwardArc
	 * 4=BackwardArc
	 * 5=Speed
	 * 6=Stop
	 * 7=Rot
	 * 8=REset Rot
	 */
	short type;
	int additional;
	
	public NxtCommand(short i, int add)
	{
		this.type = i;
		this.additional = add;
	}
	
	public short getType()
	{
		return type;
	}
	
	public int getAdd()
	{
		return additional;
	}
}
