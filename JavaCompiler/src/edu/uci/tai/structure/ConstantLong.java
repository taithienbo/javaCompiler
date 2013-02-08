package edu.uci.tai.structure;

import java.io.FileInputStream;

import edu.uci.tai.parser.ConstantPool;

public class ConstantLong extends Structure
{
	public ConstantLong(FileInputStream fis)
	{
		super(fis);
	}
	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_LONG;
	}
	
	
}
