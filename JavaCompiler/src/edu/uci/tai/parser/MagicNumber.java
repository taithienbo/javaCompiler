package edu.uci.tai.parser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MagicNumber 
{
	private byte[] magicNumber;
	private static final int LENGTH = 4;
	private FileInputStream fis;
	
	public MagicNumber(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		magicNumber = new byte[LENGTH];
		fis.read(magicNumber);
	}
	
	public MagicNumber(byte[] magicNumber)
	{
		this.magicNumber = magicNumber;
		
	}
	
	@Override
	public String toString()
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(magicNumber);
		
		StringBuilder builder = new StringBuilder("Magic Number: \n");
		
		for (int i = 0; i < LENGTH; i++)
			builder.append(bis.read() + "\n");
		
		try 
		{
			bis.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	
}
