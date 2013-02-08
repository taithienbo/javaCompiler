package edu.uci.tai.structure;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.parser.ConstantPool;

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
		return intFromBytes(classIndex);	
	}
	
	public int nameAndTypeIndex()
	{
		return intFromBytes(nameAndTypeIndex);
	}
	
	public abstract int getTag();

}
