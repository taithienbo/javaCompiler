package edu.uci.tai.constantPool;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


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
	public static final int USED_BY_COMPILER = 0;

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

		int result = (int) Structure.valueFromBytes(data);

		return result - 1; // One is used by compiler and does not count;  		
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
			case USED_BY_COMPILER:
				// the first structure (0 structure) is used 
				// by JVM internally
				structures.add(new UsedByCompilerConstant(fis));
				break;
			default:
				System.out.println("Unrecognizable Tag: " + tag + " at iteration: " + i);
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
	
		for (int i = 0; i < structures.size(); i++)
			builder.append(String.format("%d.", i))
				.append(structures.get(i).toString())
				.append("\n");
		
		builder.append("\n");
		
		return builder.toString();
	}

}
