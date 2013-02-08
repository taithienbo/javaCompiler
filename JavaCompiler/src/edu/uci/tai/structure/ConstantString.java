package edu.uci.tai.structure;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.parser.ConstantPool;

public class ConstantString extends Structure
{
	
	public static final int STRING_INDEX_NUM_BYTES = 2;
	private byte[] stringIndex;
	
	public ConstantString(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	private void initialize() throws IOException
	{
		stringIndex = new byte[STRING_INDEX_NUM_BYTES];
		fis.read(stringIndex);
	}
	
	private int stringIndex()
	{
		return intFromBytes(stringIndex);
	}
	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_STRING;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_String_info: \n" );
		builder.append(String.format("string_index: %d", stringIndex()));
		
		return builder.toString();
	}
}
