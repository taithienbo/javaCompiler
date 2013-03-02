package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class ConstantValueAttribute extends Attribute 
{
	private static final int CONSTANT_VALUE_INDEX_NUM_BYTES = 2;
	private int constantValueIndex;
	
	public ConstantValueAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("ConstantValue");
		initialize();
	}
	
	private void initialize() throws IOException
	{
		byte[] consValueIndexBytes = new byte[CONSTANT_VALUE_INDEX_NUM_BYTES];
		fis.read(consValueIndexBytes);
		
		constantValueIndex = (int) Structure.valueFromBytes(consValueIndexBytes);
	}
	
	public int consValueIndex()
	{
		return constantValueIndex;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(String.format("constant_value_index %d", consValueIndex())).append("\n");
		
		return builder.toString();
	}

}
