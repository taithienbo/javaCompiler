package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;
import experiment.BitManipulation;

public class InnerClassesAttribute extends Attribute 
{
	
	private ClassEntry[] classes;
	private static final int NUMBER_OF_CLASSES_NUM_BYTES = 2;
	
	/**
	 * @param fis
	 * @throws IOException 
	 */
	public InnerClassesAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("InnerClasses");
		init();
	}
	
	private void init() throws IOException
	{
		byte[] numOfEntries = new byte[NUMBER_OF_CLASSES_NUM_BYTES];
		fis.read(numOfEntries);
		classes = new ClassEntry[(int) Structure.valueFromBytes(numOfEntries)];
		for (int i = 0; i < classes.length; i++)
			classes[i] = new ClassEntry();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(String.format("number_of_classes: %d \n", classes.length));
		for (ClassEntry entry : classes)
			builder.append(entry).append("\n");
		return builder.toString();
	}

	private class ClassEntry
	{
		private static final int INNER_CLASS_INFO_INDEX_NUM_BYTES = 2;
		private static final int OUTER_CLASS_INFO_INDEX_NUM_BYTES = 2;
		private static final int INNER_NAME_INDEX_NUM_BYTES = 2;
		
		private int innerClassInfoIndex;
		private int outerClassInfoIndex; 
		private int innerNameIndex;
		private InnerClassAccessFlag accessFlag;
		
		public ClassEntry() throws IOException
		{
			initInnerClassInfoIndex();
			initOuterClassInfoIndex();
			initInnerNameIndex();
			initInerClassAccessFlag();
			
		}
		
		public int innerClassInfoIndex()
		{
			return innerClassInfoIndex; 
		}
		
		public int outerClassInfoIndex()
		{
			return outerClassInfoIndex;
		}
		
		public int innerClassNameIndex()
		{
			return innerNameIndex;
		}
		
		public InnerClassAccessFlag innerClassAcessFlag()
		{
			return accessFlag;
		}
		
		private void initInnerClassInfoIndex() throws IOException
		{
			byte[] innerClassInfoIndexBytes = new byte[INNER_CLASS_INFO_INDEX_NUM_BYTES];
			fis.read(innerClassInfoIndexBytes);
			innerClassInfoIndex = (int) Structure.valueFromBytes(innerClassInfoIndexBytes);
		}
		
		private void initOuterClassInfoIndex() throws IOException
		{
			byte[] outerClassInfoIndexBytes = new byte[OUTER_CLASS_INFO_INDEX_NUM_BYTES];
			fis.read(outerClassInfoIndexBytes);
			outerClassInfoIndex = (int) Structure.valueFromBytes(outerClassInfoIndexBytes);
		}
		
		private void initInnerNameIndex() throws IOException
		{
			byte[] innerNameIndexBytes = new byte[INNER_NAME_INDEX_NUM_BYTES];
			fis.read(innerNameIndexBytes);
			innerNameIndex = (int) Structure.valueFromBytes(innerNameIndexBytes);
		}
		
		private void initInerClassAccessFlag() throws IOException
		{
			accessFlag = new InnerClassAccessFlag();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder("Class entry: \n");
			builder.append(String.format("inner_class_info_index: %d \n", innerClassInfoIndex));
			builder.append(String.format("outer_class_info_index: %d \n", outerClassInfoIndex));
			builder.append(String.format("inner_name_index: %d \n", innerNameIndex));
			builder.append(accessFlag);
			return builder.toString();
		}
		
		private class InnerClassAccessFlag
		{

			private static final int ACC_PUBLIC_POS = 0;
			private static final int ACC_PRIVATE_POS = 1;
			private static final int ACC_PROTECTED_POS = 2;
			private static final int ACC_STATIC_POS = 3;
			private static final int ACC_FINAL_POS = 4;
			private static final int ACC_INTERFACE_POS = 1;		// 9 in high byte
			private static final int ACC_ABSTRACT_POS = 2;		// 10 in high byte

			private BitManipulation bitMan;
			private int accessFlagLowByte;
			private int accessFlagHighByte;

			private static final int ACCESS_FLAGS_NUM_BYTES = 2;


			public InnerClassAccessFlag() throws IOException
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


			public boolean isAbstract()
			{
				return bitMan.getBit(accessFlagHighByte, ACC_ABSTRACT_POS);
			}

			public boolean isInterface()
			{
				return bitMan.getBit(accessFlagHighByte, ACC_INTERFACE_POS);
			}

			@Override
			public String toString()
			{
				StringBuilder builder = new StringBuilder();
				builder.append("inner_class_access_flags: ");
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
				if (isAbstract())
					builder.append("ACC_ABSTRACT \t");
				if (isInterface())
					builder.append("ACC_INTERFACE \t");
				builder.append("\n");

				return builder.toString();
			}

		}
	}
}
