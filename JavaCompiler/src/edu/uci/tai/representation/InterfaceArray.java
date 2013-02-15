package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class InterfaceArray 
{
	
	private FileInputStream fis;
	private static final int INTERFACE_COUNT_NUM_BYTES = 2;
	private Interface[] interfaces;
	
	public InterfaceArray(FileInputStream fis) throws IOException 
	{
		this.fis = fis;
		initialize();
	}
	
	private void initialize() throws IOException
	{
		byte[] interfaceCount = new byte[INTERFACE_COUNT_NUM_BYTES];
		fis.read(interfaceCount);
		interfaces = new Interface[(int) Structure.valueFromBytes(interfaceCount)];
		
		for (int i = 0; i < interfaces.length; i++)
			interfaces[i] = new Interface(fis);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("interfaces_count: %d", interfaces.length));
		builder.append("\n");
		
		for (Interface inter : interfaces)
			builder.append(inter.toString());
		
		return builder.toString();
	}
	
	
}
