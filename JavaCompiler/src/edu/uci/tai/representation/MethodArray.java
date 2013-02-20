
package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.uci.tai.constantPool.Structure;
import experiment.BitManipulation;

public class MethodArray 
{
	private FileInputStream fis;
	private static final int METHOD_COUNT_NUM_BYTEs = 2;
	private Method[] methods;
	
	public MethodArray(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		init();
	}
	
	private void init() throws IOException
	{
		byte[] methodCount = new byte[METHOD_COUNT_NUM_BYTEs];
		fis.read(methodCount);
		
		methods = new Method[(int) Structure.valueFromBytes(methodCount)];
		
		for (int i = 0; i < methods.length; i++)
			methods[i] = new Method();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("method_count: %d", methods.length));
		for (Method m : methods)
			builder.append(m);
		
		return builder.toString();
	}
	
	private class Method
	{
		private AccessFlag accessFlag;
		private int nameIndex;
		private int descriptorIndex;
		private Attribute[] attributes;
		
		
		private static final int NAME_INDEX_NUM_BYTES = 2;
		private static final int DESCRIPTOR_INDEX_NUM_BYTES = 2;
		private static final int ATTRIBUTE_COUNT_NUM_BYTES = 2;
		
		public Method() throws IOException
		{
			initAcessFlag();
			initNameIndex();
			initDescriptorIndex();
			initAttributes();
		}
		
		private void initAcessFlag() throws IOException
		{
			accessFlag = new AccessFlag();
		}
		
		private void initNameIndex() throws IOException
		{
			byte[] nameIndexBytes = new byte[NAME_INDEX_NUM_BYTES];
			fis.read(nameIndexBytes);
			nameIndex = (int) Structure.valueFromBytes(nameIndexBytes);
		}
		
		private void initDescriptorIndex() throws IOException 
		{
			byte[] indexBytes = new byte[DESCRIPTOR_INDEX_NUM_BYTES];
			fis.read(indexBytes);
			descriptorIndex = (int) Structure.valueFromBytes(indexBytes);
		}
		
		private void initAttributes() throws IOException
		{
			byte[] length = new byte[ATTRIBUTE_COUNT_NUM_BYTES];
			fis.read(length);
			attributes = new Attribute[(int) Structure.valueFromBytes(length)];
			
			for (int i = 0; i < attributes.length; i++)
				attributes[i] = new AttributeParser(fis).parseAttribute();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("method_info: \n");
			builder.append(accessFlag);
			builder.append(String.format("name_index: %d \n", nameIndex));
			builder.append(String.format("descriptor_index: %d \n", descriptorIndex));
			builder.append(attributes);
			
			return builder.toString();
		}
		
		private class AccessFlag
		{
			private static final int ACC_PUBLIC_POS = 0;
			private static final int ACC_PRIVATE_POS = 1;
			private static final int ACC_PROTECTED_POS = 2;
			private static final int ACC_STATIC_POS = 3;
			private static final int ACC_FINAL_POS = 4;
			private static final int ACC_SYNCHRONIZED_POS = 5;
			private static final int ACC_NATIVE_POS = 0;		// 8 when align two bytes horizontally
			private static final int ACC_ABSTRACT_POS = 2; 		// 10 when align two bytes horizontally
			private static final int ACC_STRICT_POS = 3;		// 11 when align two bytes horizontally

			private BitManipulation bitMan;
			private int accessFlagLowByte;
			private int accessFlagHighByte;
			
			private static final int ACCESS_FLAGS_NUM_BYTES = 2;
			
			public AccessFlag() throws IOException
			{
				bitMan = new BitManipulation();
				init();
			}
			
			private void init() throws IOException
			{
				byte[] accessFlags = new byte[ACCESS_FLAGS_NUM_BYTES];
				fis.read(accessFlags);
				accessFlagLowByte = (int) Structure.valueFromBytes(new byte[]{accessFlags[1]});
				accessFlagHighByte = (int) Structure.valueFromBytes(new byte[]{accessFlags[0]});
			}
			
			public boolean isPublic()
			{
				return bitMan.getBit(accessFlagLowByte, ACC_PUBLIC_POS);
			}
			
			public boolean isPrivate()
			{
				return bitMan.getBit(accessFlagLowByte, ACC_PRIVATE_POS);
			}
			
			public boolean isProtected()
			{
				return bitMan.getBit(accessFlagLowByte, ACC_PROTECTED_POS);
			}
			
			public boolean isStatic()
			{
				return bitMan.getBit(accessFlagLowByte, ACC_STATIC_POS);
			}
			
			public boolean isFinal()
			{
				return bitMan.getBit(accessFlagLowByte, ACC_FINAL_POS);
			}
			
			public boolean isSynchronized()
			{
				return bitMan.getBit(accessFlagLowByte, ACC_SYNCHRONIZED_POS);
			}
			
			public boolean isNative()
			{
				return bitMan.getBit(accessFlagHighByte, ACC_NATIVE_POS);
			}
			
			public boolean isAbstract()
			{
				return bitMan.getBit(accessFlagHighByte, ACC_ABSTRACT_POS);
			}
			
			public boolean isStrict()
			{
				return bitMan.getBit(accessFlagHighByte, ACC_STRICT_POS);
			}
			
			@Override
			public String toString()
			{
				StringBuilder builder = new StringBuilder();
				builder.append("method_access_flags: ");
				if (isPublic())
					builder.append("ACC_PUBLIC \t");
				if (isPrivate())
					builder.append("ACC_PRIVATE \t");
				if (isProtected())
					builder.append("ACC_PROCTECTED \t");
				if (isStatic())
					builder.append("ACC_STATIC \t");
				if (isFinal())
					builder.append("ACC_FINAL \t");
				if (isSynchronized())
					builder.append("ACC_SYNCHRONIZED \t");
				if (isNative())
					builder.append("ACC_NATIVE \t");
				if (isAbstract())
					builder.append("ACC_ABSTRACT \t");
				if (isStrict())
					builder.append("ACC_STRICT \t");
				builder.append("\n");
				
				return builder.toString();
			}
		}
	}


}
