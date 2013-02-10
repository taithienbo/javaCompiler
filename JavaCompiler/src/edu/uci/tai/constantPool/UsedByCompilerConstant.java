package edu.uci.tai.constantPool;

import java.io.FileInputStream;


public class UsedByCompilerConstant extends Structure
{

	public UsedByCompilerConstant(FileInputStream fis) 
	{
		super(fis);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTag() 
	{
		// TODO Auto-generated method stub
		return ConstantPool.USED_BY_COMPILER;
	}
	
	@Override
	public String toString()
	{
		return "This is used by the compiler";
	}

}
