package edu.uci.tai.structure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import edu.uci.tai.parser.ConstantPool;

public class ConstantLong extends ConstantLongDouble
{
	public ConstantLong(FileInputStream fis) throws IOException
	{
		super(fis);
	}
	
    public long value()
    {
    	/*StringBuilder longString = new StringBuilder();
    	longString.append(byteArrayToString(highBytes));
    	longString.append(byteArrayToString(lowBytes));*/
    	long value = 0L;
    
    	byte[] combined = new byte[highBytes.length + lowBytes.length];
    	
    	int lowByteIndex = 0;
    	
    	for (int i = 0; i < highBytes.length; i++)
    		combined[i] = highBytes[i];
    	
    	for (int i = highBytes.length; i < combined.length; i++)
    		combined[i] = lowBytes[lowByteIndex++];
    	
    	
    	value += super.valueFromBytes(combined);
    	
    	return value;
    }
    
    private String byteArrayToString(byte[] bytes)
    {
    	StringBuilder builder = new StringBuilder();
    	
    	for (Byte b : bytes)
    	{
    		builder.append(Byte.toString(b));
    		System.out.println("Test: " + builder.toString());
    	}
  
    	return builder.toString();
    }
    
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_LONG;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_Long_Info: \n" );
		builder.append("Value: " + value());
		
		return builder.toString();
	}
	
}
