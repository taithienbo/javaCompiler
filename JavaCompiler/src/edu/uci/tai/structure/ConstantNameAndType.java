package edu.uci.tai.structure;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.parser.ConstantPool;

/**
 * The CONSTANT_NameAndType_info structure is used to represent a field or 
 * method, without indicating which class or interface it belongs to
 * @author tbo
 *
 */
public class ConstantNameAndType extends Structure
{
	public static final int NAME_INDEX_NUM_BYTES = 2;
	public static final int DESCRIPTOR_INDEX_NUM_BYTES = 2;
	
	private byte[] nameIndex;
	private byte[] descriptorIndex;
	
	public ConstantNameAndType(FileInputStream fis) throws IOException
	{
		super(fis);
		initialize();
	}
	
	private void initialize() throws IOException
	{
		nameIndex = new byte[NAME_INDEX_NUM_BYTES];
		fis.read(nameIndex);
		descriptorIndex = new byte[DESCRIPTOR_INDEX_NUM_BYTES];
		fis.read(descriptorIndex);
	}
	
	public int nameIndex()
	{
		return intFromBytes(nameIndex);
	}
	
	public int descriptorIndex()
	{
		return intFromBytes(descriptorIndex);
	}
	

	
	@Override
	public int getTag()
	{
		return ConstantPool.CONSTANT_NAMEANDTYPE;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CONSTANT_NameAndType_info: \n");
		builder.append(String.format("name_index: %d", nameIndex()));
		builder.append("\n");
		builder.append(String.format("descriptor_index: %d", descriptorIndex()));
	
		return builder.toString();
	}
}
