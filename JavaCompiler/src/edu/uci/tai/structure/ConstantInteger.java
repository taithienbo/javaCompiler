package edu.uci.tai.structure;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.parser.ConstantPool;

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
		return intFromBytes(bytes);
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
