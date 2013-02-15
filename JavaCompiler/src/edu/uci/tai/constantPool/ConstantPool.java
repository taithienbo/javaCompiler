package edu.uci.tai.constantPool;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class ConstantPool 
{
	private FileInputStream fis; 
	private static final int SIZE_OF_CONS_POOL_LENG = 2;
	private int constPoolSize;
	private HashMap<Integer, Structure> structureMap; 

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
	public static final int USED_BY_COMPILER = -1;

	public ConstantPool(FileInputStream fis) throws IOException
	{
		this.fis = fis;

		constPoolSize = consPoolSize();
		structureMap = new LinkedHashMap<Integer, Structure>();
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
		int index = 1;
		for (int i = 0; i < constPoolSize; i++)
		{
			int tag = fis.read();
			switch (tag)
			{
			case CONSTANT_CLASS:
				structureMap.put(index++, new ConstantClass(fis));
				break;
			case CONSTANT_FIELDREF:
				structureMap.put(index++, new ConstantFieldRef(fis));
				break;
			case CONSTANT_METHODREF:
				structureMap.put(index++, new ConstantMethodref(fis));
				break;
			case CONSTANT_INTERFACEMETHODREF:
				structureMap.put(index++, new ConstantInterfaceMethodRef(fis));
				break;
			case CONSTANT_STRING:
				structureMap.put(index++, new ConstantString(fis));
				break;
			case CONSTANT_INTEGER:
				structureMap.put(index++, new ConstantInteger(fis));
				break;
			case CONSTANT_FLOAT:
				structureMap.put(index++, new ConstantFloat(fis));
				break;
			case CONSTANT_LONG:
				structureMap.put(index++, new ConstantLong(fis));
				break;
			case CONSTANT_DOUBLE:
				structureMap.put(index++, new ConstantDouble(fis));
				break;
			case CONSTANT_NAMEANDTYPE:
				structureMap.put(index++, new ConstantNameAndType(fis));
				break;
			case CONSTANT_UTF8:
				structureMap.put(index++, new ConstantUtf8(fis));
				break;
			case USED_BY_COMPILER:
				// the first structure (0 structure) is used 
				// by JVM internally
				structureMap.put(index++, new UsedByCompilerConstant(fis));
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

		for (int index : structureMap.keySet())
		{
			builder.append(String.format("%d.", index))
			.append(structureMap.get(index).toString())
			.append("\n");

			builder.append("\n");
		}
		return builder.toString();
	}

}
