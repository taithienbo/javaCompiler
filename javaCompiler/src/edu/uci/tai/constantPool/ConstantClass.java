package edu.uci.tai.constantPool;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class ConstantClass extends Structure
{
	private byte[] nameIndexData;
	public static final int NAME_INDEX_NUM_BYTES = 2;
	
	public ConstantClass(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	private void initialize() throws IOException 
	{
		nameIndexData = new byte[NAME_INDEX_NUM_BYTES];
		fis.read(nameIndexData);		
	}
	
	public int nameIndex()
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(nameIndexData);
		
		int result = 0;
		
		while (bis.available() > 0)
			result += bis.read();
		
		return result;
	}

	@Override
	public int getTag() 
	{
		return ConstantPool.CONSTANT_CLASS;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder("Constant_Class_Info:");
		builder.append("\n");
		builder.append(String.format("Name Index: %d", nameIndex()));
		
		return builder.toString();
	}
}
