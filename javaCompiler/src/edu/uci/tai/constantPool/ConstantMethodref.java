package edu.uci.tai.constantPool;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class ConstantMethodref extends ConstantRefInfo
{

	
	public ConstantMethodref(FileInputStream fis) throws IOException 
	{
		super(fis);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_Methoref_info: \n" );
		builder.append(String.format("ClassIndex: %d", classIndex()) + "\n");
		builder.append(String.format("NameAndTypeIndex: %d", nameAndTypeIndex()));
		
		return builder.toString();
	}

	@Override
	public int getTag() 
	{
		// TODO Auto-generated method stub
		return ConstantPool.CONSTANT_METHODREF;
	}
	

}
