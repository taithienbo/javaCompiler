package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;


public class ConstantDouble extends ConstantLongDouble
{
	public ConstantDouble(FileInputStream fis) throws IOException
	{
		super(fis);
	}

	@Override
	public int getTag() 
	{
		return ConstantPool.CONSTANT_DOUBLE;
	}
	
	public double value()
	{
		throw new RuntimeException("Not yet known how to parse doiubel from bytes");
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_Double_info: \n");
		builder.append(String.format("not yet implemented parsing double from bytes"));
	
		return builder.toString();
	}
}
