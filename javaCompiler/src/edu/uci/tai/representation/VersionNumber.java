package edu.uci.tai.representation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class VersionNumber
{
	private FileInputStream fis;
	private static final int LENGTH = 4;
	private byte[] data;

	
	public VersionNumber(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		data = new byte[LENGTH];
		fis.read(data);
	
	}
	
	@Override
	public String toString()
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
	
		StringBuilder result = new StringBuilder("Version number: \n");
		
		for (int i = 0; i < bis.available(); i++)
			result.append(bis.read() + "\n");
		
		
		return result.toString();
	}
}