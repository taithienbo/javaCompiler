package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;


public class ConstantInteger extends ConstantIntFloat 
{
	public ConstantInteger(FileInputStream fis) throws IOException
	{
		super (fis);
	}
	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_INTEGER;
	}
	
	public int value()
	{
		return (int) valueFromBytes(bytes);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_Integer_info: \n" );
		builder.append(String.format("value: %d", value()));
		
		return builder.toString();
	}
}
