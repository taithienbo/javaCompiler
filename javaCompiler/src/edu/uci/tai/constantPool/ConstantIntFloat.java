package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class ConstantIntFloat extends Structure 
{
	public static final int BYTES_NUM_BYTES = 4;
	
	protected byte[] bytes;
	
	public ConstantIntFloat(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	public void initialize() throws IOException
	{
		bytes = new byte[BYTES_NUM_BYTES];
		fis.read(bytes);
	}
	
	
	
	
}
