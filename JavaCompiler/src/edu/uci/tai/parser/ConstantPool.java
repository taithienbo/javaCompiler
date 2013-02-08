package edu.uci.tai.parser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.uci.tai.structure.ConstantClass;
import edu.uci.tai.structure.ConstantDouble;
import edu.uci.tai.structure.ConstantFieldRef;
import edu.uci.tai.structure.ConstantFloat;
import edu.uci.tai.structure.ConstantInteger;
import edu.uci.tai.structure.ConstantInterfaceMethodRef;
import edu.uci.tai.structure.ConstantLong;
import edu.uci.tai.structure.ConstantMethodref;
import edu.uci.tai.structure.ConstantNameAndType;
import edu.uci.tai.structure.ConstantString;
import edu.uci.tai.structure.ConstantUtf8;
import edu.uci.tai.structure.Structure;

public class ConstantPool 
{
	private FileInputStream fis; 
	private static final int SIZE_OF_CONS_POOL_LENG = 2;
	private int constPoolSize;
	private ArrayList<Structure> structures; 

	public static final int CONSTANT_CLASS = 7;
	public static final int CONSTANT_FIELDREF = 9;
	public static final int CONSTANT_METHODREF = 10;
	public static final int CONSTANT_INTERFACEMETHODREF = 11;
	public static final int CONSTANT_STRING = 8;
	public static final int CONSTANT_INTEGER = 3;
	public static final int CONSTANT_FLOAT = 4;
	public static final int CONSTANT_LONG = 5;
	public static final int CONSTANT_DOUBLE = 6;
	public static final int CONSTANT_NAMEANDTYPE = 12;
	public static final int CONSTANT_UTF8 = 1;

	public ConstantPool(FileInputStream fis) throws IOException
	{
		this.fis = fis;

		constPoolSize = consPoolSize();
		structures = new ArrayList<Structure>();
		parseStructures();
	}	

	public int consPoolSize() throws IOException
	{
		byte[] data = new byte[SIZE_OF_CONS_POOL_LENG];
		fis.read(data);

		ByteArrayInputStream bis = new ByteArrayInputStream(data);

		int result = 0;

		for (int i = 0; i < SIZE_OF_CONS_POOL_LENG; i++)
			result += bis.read();


		// the first structure (0 structure) is used 
		// by JVM internally
		return result - 1; 		
	}

	private void parseStructures() throws IOException
	{
		for (int i = 0; i < constPoolSize; i++)
		{
			int tag = fis.read();
			switch (tag)
			{
			case CONSTANT_CLASS:
				structures.add(new ConstantClass(fis));
				break;
			case CONSTANT_FIELDREF:
				structures.add(new ConstantFieldRef(fis));
				break;
			case CONSTANT_METHODREF:
				structures.add(new ConstantMethodref(fis));
				break;
			case CONSTANT_INTERFACEMETHODREF:
				structures.add(new ConstantInterfaceMethodRef(fis));
				break;
			case CONSTANT_STRING:
				structures.add(new ConstantString(fis));
				break;
			case CONSTANT_INTEGER:
				structures.add(new ConstantInteger(fis));
				break;
			case CONSTANT_FLOAT:
				structures.add(new ConstantFloat(fis));
				break;
			case CONSTANT_LONG:
				structures.add(new ConstantLong(fis));
				break;
			case CONSTANT_DOUBLE:
				structures.add(new ConstantDouble(fis));
				break;
			case CONSTANT_NAMEANDTYPE:
				structures.add(new ConstantNameAndType(fis));
				break;
			case CONSTANT_UTF8:
				structures.add(new ConstantUtf8(fis));
				break;
			default:
				break;

			}
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder("Constant Pool: \n");

		builder.append("size of constant pool (number of structures): "
				+ constPoolSize).append("\n");
		
		builder.append("\n");
	
		for (int i = 1; i <= structures.size(); i++)
			builder.append(String.format("%d.", i))
				.append(structures.get(i-1).toString())
				.append("\n");
		
		builder.append("\n");
		
		return builder.toString();
	}

}
