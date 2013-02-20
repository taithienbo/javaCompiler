package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class ExceptionAttribute extends Attribute 
{
	private static final int NUM_OF_EXCEPTIONS_NUM_BYTES = 2;
	private static final int EXCEPTION_ENTRY_NUM_BYTES = 2;
	private int[] exceptionIndexes;
	
	public ExceptionAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("Exceptions");	
		initialize();
	}
	
	private void initialize() throws IOException 
	{
		byte[] numExcetionsBytes = new byte[NUM_OF_EXCEPTIONS_NUM_BYTES];
		fis.read(numExcetionsBytes);
		exceptionIndexes = new int[(int) Structure.valueFromBytes(numExcetionsBytes)];
		for (int i = 0; i < exceptionIndexes.length; i++)
		{
			byte[] index = new byte[EXCEPTION_ENTRY_NUM_BYTES];
			fis.read(index);
			exceptionIndexes[i] = (int) Structure.valueFromBytes(index);
		}
	}
	
	public int[] exceptionIndexes()
	{
		return exceptionIndexes;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append("exception_indexes: ");
		builder.append(exceptionIndexes).append("\n");
		return builder.toString();
	}

}
