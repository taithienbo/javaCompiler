package edu.uci.tai.structure;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.parser.ConstantPool;

public class ConstantUtf8 extends Structure
{
	
	public static final int LENGTH_NUM_BYTES = 2;
	private byte[] length;
	private byte[] data;
	
	public ConstantUtf8(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	private void initialize() throws IOException
	{
		length = new byte[LENGTH_NUM_BYTES];
		fis.read(length);
		
		data = new byte[length()];
		fis.read(data);
	}
	
	public int length()
	{
		int result = 0;
		
		ByteArrayInputStream bis = new ByteArrayInputStream(length);
		
		while (bis.available() > 0)
			result += bis.read();
			
		return result;
	}
	
	public String data() throws IOException
	{
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		
		StringBuilder builder = new StringBuilder();
		builder.append(new String(data));
		
		return builder.toString();
	}
	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_UTF8;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Constant_Utf8_Info: \n");
		builder.append(String.format("Length: %d", length()));
		builder.append("\n");
		try 
		{
			builder.append(String.format("%s", data()));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();
	}
}