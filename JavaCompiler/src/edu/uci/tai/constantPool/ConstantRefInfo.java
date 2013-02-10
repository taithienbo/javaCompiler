package edu.uci.tai.constantPool;

import java.io.FileInputStream;
import java.io.IOException;


public abstract class ConstantRefInfo extends Structure
{
	
	private static final int CLASS_INDEX_NUM_BYTES = 2;
	private static final int NAME_AND_TYPE_INDEX_NUM_BYTES = 2;
	
	private byte[] classIndex;
	private byte[] nameAndTypeIndex;
	
	public ConstantRefInfo(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	private void initialize() throws IOException
	{
		classIndex = new byte[CLASS_INDEX_NUM_BYTES];
		fis.read(classIndex);
		nameAndTypeIndex = new byte[NAME_AND_TYPE_INDEX_NUM_BYTES];
		fis.read(nameAndTypeIndex);
	}
	
	public int classIndex()
	{
		return (int) valueFromBytes(classIndex);	
	}
	
	public int nameAndTypeIndex()
	{
		return (int) valueFromBytes(nameAndTypeIndex);
	}
	
	public abstract int getTag();

}
