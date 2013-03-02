package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import edu.uci.tai.constantPool.Structure;
import experiment.BitManipulation;

public class Field 
{
	private FileInputStream fis;
	
	private static final int ACESS_FLAGS_NUM_BYTES = 2;
	private static final int NAME_INDEX_NUM_BYTES = 2;
	private static final int DESCRIPTOR_INDEX_NUM_BYTES = 2;
	private static final int ATTRIBUTE_COUNT_NUM_BYTES = 2;
	
	private Attribute[] attributes;
	private FieldAccessFlag accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	public Field (FileInputStream fis) throws Exception
	{
		this.fis = fis;
		initAccessFlag();
		initNameIndex();
		initDescriptorIndex();
		initAttributes();
	}
	
	private void initAccessFlag() throws IOException
	{
		byte[]  flag = new byte[ACESS_FLAGS_NUM_BYTES];
		fis.read(flag);
		accessFlag = new FieldAccessFlag((int) Structure.valueFromBytes(flag));
	}
	
	private void initNameIndex() throws IOException
	{
		byte[] nIndex = new byte[NAME_INDEX_NUM_BYTES];
		fis.read(nIndex);
		nameIndex = (int) Structure.valueFromBytes(nIndex);
	}
	
	private void initDescriptorIndex() throws IOException
	{
		byte[] dIndex = new byte[DESCRIPTOR_INDEX_NUM_BYTES];
		fis.read(dIndex);
		descriptorIndex = (int) Structure.valueFromBytes(dIndex);
	}
	
	private void initAttributes() throws Exception
	{
		byte[] attributeCount = new byte[ATTRIBUTE_COUNT_NUM_BYTES];
		fis.read(attributeCount);

		attributes = new Attribute[(int) Structure.valueFromBytes(attributeCount)];
	
		for (int i = 0; i < attributes.length; i++)
			attributes[i] = new AttributeParser(fis).parseAttribute();
	}
	
	public int nameIndex()
	{
		return nameIndex;
	}
	
	public int descriptorIndex()
	{
		return descriptorIndex;
	}
	
	public FieldAccessFlag accessFlag()
	{	
		return accessFlag;
	}
	
	public Collection<Attribute> attributes()
	{
		return Arrays.asList(attributes);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("field_info: \n");
		builder.append(accessFlag).append("\n");
		builder.append(String.format("name_index: %d \n", nameIndex()));
		builder.append(String.format("descriptor_index: %d \n", descriptorIndex()));
		builder.append(String.format("attributes_count: %d \n", attributes.length));
		
		for (Attribute attribute : attributes)
			builder.append(attribute).append("\n");
		
		return builder.toString();
	}
	
	public class FieldAccessFlag 
	{
		private static final int ACC_PUBLIC_ON_BIT_POSITION = 0;
		private static final int ACC_PRIVATE_ON_BIT_POSITION = 1;
		private static final int ACC_PROTECTED_ON_BIT_POSITION = 2;
		private static final int ACC_STATIC_ON_BIT_POSITION = 3;
		private static final int ACC_FINAL_ON_BIT_POSITION = 4;
		private static final int ACC_VOLATILE_ON_BIT_POSITION = 5;
		private static final int ACC_TRANSIENT_ON_BIT_POSITION = 7;
		
		private BitManipulation bitMan;
		private int flags;
		
		public FieldAccessFlag(int flags)
		{
			bitMan = new BitManipulation();
			this.flags = flags; 
		}
		
		public boolean isTransient()
		{
			return bitMan.getBit(flags, ACC_TRANSIENT_ON_BIT_POSITION);
		}
		
		public boolean isVolatile()
		{
			return bitMan.getBit(flags, ACC_VOLATILE_ON_BIT_POSITION);
		}
		
		public boolean isFinal()
		{
			return bitMan.getBit(flags, ACC_FINAL_ON_BIT_POSITION);
		}
		
		public boolean isStatic()
		{
			return bitMan.getBit(flags, ACC_STATIC_ON_BIT_POSITION);
		}
		
		public boolean isProtected()
		{
			return bitMan.getBit(flags, ACC_PROTECTED_ON_BIT_POSITION);
		}
		
		public boolean isPublic()
		{
			return bitMan.getBit(flags, ACC_PUBLIC_ON_BIT_POSITION);
		}
		
		public boolean isPrivate()
		{
			return bitMan.getBit(flags, ACC_PRIVATE_ON_BIT_POSITION);
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("access_flags: \t");
			
			if (isPrivate())
				builder.append("ACC_PRIVATE \t");
			if (isPublic())
				builder.append("ACC_PUBLIC \t");
			if (isProtected())
				builder.append("ACC_PROTECTED \t");
			if (isStatic())
				builder.append("ACC_STATIC \t");
			if (isFinal())
				builder.append("ACC_FINAL \t");
			if (isVolatile())
				builder.append("ACC_VOLATILE \t");
			if (isTransient())
				builder.append("ACC_TRANSIENT \t");

			return builder.toString();
		}
	}
}
