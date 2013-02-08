package edu.uci.tai.structure;

import java.io.FileInputStream;

import edu.uci.tai.parser.ConstantPool;

public class ConstantDouble extends Structure
{
	public ConstantDouble(FileInputStream fis)
	{
		super(fis);
	}

	@Override
	public int getTag() 
	{
		return ConstantPool.CONSTANT_DOUBLE;
	}
}
