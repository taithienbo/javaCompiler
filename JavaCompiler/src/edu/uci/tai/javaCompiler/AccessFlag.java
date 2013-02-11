package edu.uci.tai.javaCompiler;

import java.io.FileInputStream;
import java.io.IOException;

import experiment.BitManipulation;

public class AccessFlag 
{
	private FileInputStream fis;
	
	
	public static final int ACC_PUBLIC_BIT_POS = 0;
	public static final int ACC_FINAL_BIT_POS = 4;
	public static final int ACC_SUPER_BIT_POS = 5;
	public static final int ACC_INTERFACE_BIT_POS = 1;
	public static final int ACC_ABSTRACT_BIT_POS = 2;
	
	private int[] flagsInInts;	// store the bytes representation of flags as decimal values
	private static final int ACCESS_FLAGS_NUM_BYTES = 2;
	
	private BitManipulation bitMan; 
	public AccessFlag(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		iniitalize();
	}
	
	public void iniitalize() throws IOException
	{
		flagsInInts = new int[ACCESS_FLAGS_NUM_BYTES];
		
		flagsInInts[0] = fis.read();
		flagsInInts[1] = fis.read();
		
		bitMan = new BitManipulation();
	}
	
	public boolean isPublic()
	{
		return bitMan.getBit(flagsInInts[1], ACC_PUBLIC_BIT_POS);
	}
	
	public boolean isFinal()
	{
		return bitMan.getBit(flagsInInts[1], ACC_FINAL_BIT_POS);
	}
	
	public boolean isSuper()
	{
		return bitMan.getBit(flagsInInts[1], ACC_SUPER_BIT_POS);
	}
	
	public boolean isInterface()
	{
		return bitMan.getBit(flagsInInts[0], ACC_INTERFACE_BIT_POS);
	}
	
	public boolean isAbstract()
	{
		return bitMan.getBit(flagsInInts[0], ACC_ABSTRACT_BIT_POS);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ACCESS_FLAGS: \n");
	
		builder.append(String.format("high byte (%d): ", flagsInInts[0]));
		builder.append(byteStringRep(flagsInInts[0]));
		builder.append("\n");
		builder.append(String.format("low byte (%d): ", flagsInInts[1]));
		builder.append(byteStringRep(flagsInInts[1]));
		builder.append("\n");
		builder.append("On Flags: ");
		if (isPublic())
			builder.append("ACC_PUBLIC \t");
		if (isFinal())
			builder.append("ACC_FINAL \t");
		if (isSuper())
			builder.append("ACC_SUPER \t");
		if (isInterface())
			builder.append("ACC_INTERFACE \t");
		if (isAbstract())
			builder.append("ACC_ABSTRACT \t");
		
		return builder.toString();
	}
	
	private String byteStringRep(int number)
	{
		int[] digits = new int[8];
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < digits.length; i++)
			digits[i] = bitMan.getBit(number, i) ? 1 : 0;
		
		for (int i = digits.length - 1; i >=0; i--)
			result.append(digits[i]);
		
		return result.toString();
	}
}
