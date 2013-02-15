package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class Interface 
{
	private FileInputStream fis; 
	private static final int INTERFACE_NUMB_BYTES = 2;
	private int index;
	
	public Interface(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		initialize();
	}
	
	private void initialize() throws IOException
	{
		byte[] data = new byte[INTERFACE_NUMB_BYTES];
		fis.read(data);
		index = (int) Structure.valueFromBytes(data);
	}
	
	public int index()
	{
		return index;
	}
	
	@Override
	public String toString()
	{
		return String.format("index: %d \n", index());
	}
	
}
