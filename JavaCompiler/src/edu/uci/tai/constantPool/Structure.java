package edu.uci.tai.constantPool;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import experiment.BitManipulation;

public abstract class Structure 
{
	protected FileInputStream fis;
	
	public abstract int getTag();
	
	public Structure(FileInputStream fis)
	{
		this.fis = fis;
	}
	
	/**
	 * this method assumes a full representation of a byte (8 bits)
	 * @param data
	 * @return
	 */
	public static double valueFromBytes(byte[] data)
	{
		double result = 0;
		int startPos = data.length * 8 -1;
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		
		BitManipulation bitMan = new BitManipulation();
		
		// start from most significant byte;
		while(bis.available() > 0)
		{
			int number = bis.read();
			result += bitMan.valueFromBits(number, startPos);
			startPos = startPos - 8;
		}
		
		return result;
	}
	
	
}
