package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class ConstantLongDouble extends Structure
{
	
	public static final int U4_HIGH_BYTES = 4;
	public static final int U4_LOW_BYTES = 4;
	protected byte[] highBytes;
	protected byte[] lowBytes;

	
	public ConstantLongDouble(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	public void initialize() throws IOException
	{
		highBytes = new byte[U4_HIGH_BYTES];
		lowBytes = new byte[U4_LOW_BYTES];
		
		fis.read(highBytes);
		fis.read(lowBytes);
	}


}
