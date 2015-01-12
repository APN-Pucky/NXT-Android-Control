package de.neuwirthinformatik.Alexander.NAC.GLOBAL;

public class NxtData 
{
	/*
	 * 1=GPS
	 * 2=ROT
	 * 3=?
	 */
	short type;
	double additional1;
	double additional2;
	
	public NxtData(short i, double add1, double add2)
	{
		this.type = i;
		this.additional1 = add1;
		this.additional2 = add2;
	}
	
	public short getType()
	{
		return type;
	}
	
	public double[] getAdd()
	{
		return new double[]{additional1,additional2};
	}
}
