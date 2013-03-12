package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;


public class ConstantInterfaceMethodRef extends ConstantRefInfo
{
	public ConstantInterfaceMethodRef(FileInputStream fis) throws IOException
	{
		super(fis);
	}
	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_INTERFACEMETHODREF;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_InterfaceMethodref_info: \n" );
		builder.append(String.format("ClassIndex: %d", classIndex()) + "\n");
		builder.append(String.format("NameAndTypeIndex: %d", nameAndTypeIndex()));
		
		return builder.toString();
	}
}
