package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public abstract class Attribute 
{
	private String name;
	private long attributeLength;
	protected FileInputStream fis;
	private static final int ATTRIBUTE_LENGTH_NUM_BYTES = 4;
	
	public Attribute(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		initialize();
	}
	
	private void initialize() throws IOException
	{
		byte[] attriLength = new byte[ATTRIBUTE_LENGTH_NUM_BYTES];
		fis.read(attriLength);
		
		attributeLength = 0L;
		attributeLength = (long) Structure.valueFromBytes(attriLength);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("attribute_name: %s \n", name));
		builder.append(String.format("attribute_length: %d \n", attributeLength));
		
		return builder.toString();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
