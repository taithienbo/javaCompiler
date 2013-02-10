package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;


public class ConstantFloat extends ConstantIntFloat
{
	public ConstantFloat(FileInputStream fis) throws IOException
	{
		super(fis);
	}
	
	public float value()
	{
		int bits = (int) valueFromBytes(bytes);
		return Float.intBitsToFloat(bits);
	}
	
	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_FLOAT;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_Float_info: \n" );
		builder.append(String.format("%d", value()));

		
		return builder.toString();
	}
}
