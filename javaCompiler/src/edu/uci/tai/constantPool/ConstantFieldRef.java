package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;


public class ConstantFieldRef extends ConstantRefInfo
{
	
	public ConstantFieldRef(FileInputStream fis) throws IOException
	{
		super(fis);
	}
	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Constant_Fieldref_info: \n" );
		builder.append(String.format("ClassIndex: %d", classIndex()) + "\n");
		builder.append(String.format("NameAndTypeIndex: %d", nameAndTypeIndex()));
		
		return builder.toString();
	}


	@Override
	public int getTag() 
	{
		return ConstantPool.CONSTANT_FIELDREF;
	}
}
