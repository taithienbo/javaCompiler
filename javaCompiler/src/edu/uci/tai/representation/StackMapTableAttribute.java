package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class StackMapTableAttribute extends Attribute 
{

	private  int uOffSetNumBytes;

	private StackMapFrame[] entries;
	private long byteCodeLength;
	
	public StackMapTableAttribute(FileInputStream fis) throws Exception
	{
		super(fis);
		setName("StackMapTable");
		init();
	}
	
	public StackMapTableAttribute(FileInputStream fis, long byteCodeLength) throws Exception
	{
		super(fis);
		setName("StackMapTable");
		this.byteCodeLength = byteCodeLength;
		init();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("number of stack_map_frame " +
				"entries: %d", entries.length)).append("\n");
		for (StackMapFrame e : entries)
			builder.append(e).append("\n");
		return builder.toString();
	}
	
	public void init() throws Exception
	{
		uOffSetNumBytes = byteCodeLength > 65535 ? 4 : 2;
		
		// for the purpose of this project, it shoudl be suffice to skip over this attribute
		byte[] numEntriesBytes = new byte[uOffSetNumBytes];
		fis.read(numEntriesBytes);
		int entriesCount = (int) Structure.valueFromBytes(numEntriesBytes);
		entries = new StackMapFrame[entriesCount];
		for (int i = 0; i < entries.length; i++)
		{
			int tag = fis.read();
			if (tag < 64)
				entries[i] = new SameFrame(tag);
			else if (tag < 128)
				entries[i] = new SameLocal1StackItemFrame(tag);
			else if (tag < 251)
				entries[i] = new ChopFrame(tag);
			else if (tag == 251)
				entries[i] = new SameFrameExtended(tag);
			else if (tag < 255)
				entries[i] = new AppendFrame(tag);
			else if (tag == 255)
				entries[i] = new FullFrame(tag);
			else 
				System.out.println(String.format("StackMapTableAttribute.init()" +
						": Could not identify frame type with tag: %d", tag));
		}
	}
	
	public VerificationInfo verificationInfoFromTag(int tag) throws Exception
	{
	
		if (tag == VeriInfoTag.ITEM_TOP.tag())
			return new TopVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_INTEGER.tag())
			return new IntegerVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_FLOAT.tag())
			return new FloatVariableInfo(tag);
		else if (tag ==VeriInfoTag.ITEM_LONG.tag())
			return new LongVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_DOUBLE.tag())
			return new DoubleVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_NULL.tag())
			return new NullVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_UNINITIALIZED_THIS.tag())
			return new UnitializedThisVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_OBJECT.tag())
			return new ObjectVariableInfo(tag);
		else if (tag == VeriInfoTag.ITEM_UNITIALIZE.tag())
			return new UnitializedVariableInfo(tag);
		else
			throw new Exception(String.format("Unrecognizeable Verificaiton Tag: %d", tag));
	}
	
	public class ChopFrame extends StackMapFrame
	{
		long offsetDelta = 0l;
		
		public ChopFrame(int tag) throws IOException 
		{
			super(tag);
			byte[] offsetDeltaBytes = new byte[uOffSetNumBytes];
			fis.read(offsetDeltaBytes);
			offsetDelta = (long) Structure.valueFromBytes(offsetDeltaBytes);
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(super.toString());
			builder.append("chop_frame").append("\n");
			builder.append(String.format("offset_delta: %d", offsetDelta));
			builder.append("\n");
			return builder.toString();
		}
		
	}
	
	public class FullFrame extends StackMapFrame
	{
		int offsetDelta;
		VerificationInfo[] locals;
		VerificationInfo[] stackItems;
		
		private static final int OFFSET_DELTA_NUM_BYTES = 2;
		private static final int NUM_LOCALS_NUM_BYTES = 2;
		private static final int NUM_STACK_ITEMS_NUM_BYTES = 2;
		
		public FullFrame(int tag) throws Exception 
		{
			super(tag);
			initOffSetDelta();
			initLocals();
			initStack();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(super.toString());
			builder.append("full_frame").append("\n")
				.append(String.format("offset_delta: %d", offsetDelta))
				.append("\n")
				.append(String.format("number_of_locals: %d", locals.length))
				.append("\n");
			
			for (VerificationInfo local: locals)
				builder.append(local).append("\n");
			builder.append("\n");
			builder.append
			(String.format("number_of_stack_items: %d", stackItems.length));
			builder.append("\n");
			for (VerificationInfo stack : stackItems)
				builder.append(stack).append("\n");
			
			return builder.toString();
		}
		
		public void initOffSetDelta() throws IOException
		{
			byte[] offsetDeltaBytes = new byte[OFFSET_DELTA_NUM_BYTES];
			fis.read(offsetDeltaBytes);
			offsetDelta = (int) Structure.valueFromBytes(offsetDeltaBytes);
		}
		
		public void initLocals() throws Exception
		{
			byte[] localsNumBytes = new byte[NUM_LOCALS_NUM_BYTES];
			fis.read(localsNumBytes);
			locals = new VerificationInfo
					[(int) Structure.valueFromBytes(localsNumBytes)];
			for (int i = 0; i < localsNumBytes.length; i++)
				locals[i] = verificationInfoFromTag(fis.read());
		}
		
		public void initStack() throws Exception
		{
			byte[] stackItemsBytes = new byte[NUM_STACK_ITEMS_NUM_BYTES];
			fis.read(stackItemsBytes);
			stackItems = new VerificationInfo
					[(int) Structure.valueFromBytes(stackItemsBytes)];
			for (int i = 0; i < stackItems.length; i++)
				stackItems[i] = verificationInfoFromTag(fis.read());
		}
	}
	
	public class AppendFrame extends StackMapFrame
	{
		int offsetDelta;
		VerificationInfo[] verInfos;
		
		public AppendFrame(int tag) throws Exception 
		{
			super(tag);
			initOffsetDelta();
			initVerificationInfos();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(super.toString());
			builder.append("append_frame").append("\n");
			builder.append(String.format("offset_delta: %d \n", offsetDelta));
			builder.append(String.format
					("number_of_locals: %d", verInfos.length)).append("\n");
			for (VerificationInfo local : verInfos)
				builder.append(local).append("\n");
			return builder.toString();
		}
		
		public void initOffsetDelta() throws IOException
		{
			byte[] offsetDeltaBytes = new byte[uOffSetNumBytes];
			fis.read(offsetDeltaBytes);
			offsetDelta = (int) Structure.valueFromBytes(offsetDeltaBytes);
		}
		
		public void initVerificationInfos() throws IOException, Exception
		{
			int size = super.tag - 251;
			verInfos = new VerificationInfo[size];
			for (int i = 0; i < verInfos.length; i++)
			{
				verInfos[i] = verificationInfoFromTag(fis.read());
			}
		}
	}
	
	public class SameFrameExtended extends StackMapFrame
	{
		int offsetDelta;
		
		public SameFrameExtended(int tag) throws IOException 
		{
			super(tag);
			byte[] offsetDeltaBytes = new byte[uOffSetNumBytes];
			fis.read(offsetDeltaBytes);
			offsetDelta = (int) Structure.valueFromBytes(offsetDeltaBytes);
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(super.toString());
			builder.append("same_frame_extended").append("\n");
			builder.append(String.format("offset_delta: %d", offsetDelta));
			builder.append("\n");
			return builder.toString();
		}
	}
	
	public class SameLocal1StackFrameExtended extends StackMapFrame
	{
		private int offsetDelta;
		private VerificationInfo stack;
		
		private static final int OFF_SET_DELTA_NUM_BYTES = 2;
		
		public SameLocal1StackFrameExtended(int tag) throws Exception 
		{
			super(tag);
			initOffsetDelta();
			initStack();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(super.toString());
			builder.append("same_local_stack_frame_extended").append("\n");
			builder.append
				(String.format("offset_delta: %d", offsetDelta)).append("\n");
			builder.append(stack);
			return builder.toString();
		}
		
		public void initOffsetDelta() throws IOException
		{
			byte[] offsetDeltaBytes = new byte[OFF_SET_DELTA_NUM_BYTES];
			fis.read(offsetDeltaBytes);
			offsetDelta = (int) Structure.valueFromBytes(offsetDeltaBytes);
		}
		
		public void initStack() throws IOException, Exception
		{
			stack = verificationInfoFromTag(fis.read());
		}
	}
	
	public class SameLocal1StackItemFrame extends StackMapFrame
	{
		private VerificationInfo verificationInfo;
		private int offsetDelta;
		
		private static final int OFFSET_DELTA_NUM_BYTES = 2;
		
		public SameLocal1StackItemFrame(int tag) throws Exception 
		{
			super(tag);
			initOffsetDelta();
			initStack();
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(super.toString());
			builder.append("same_local_1_stack_item_frame").append("\n");
			builder.append
				(String.format("offset_delta: %d", offsetDelta)).append("\n");
			builder.append(verificationInfo);
			return builder.toString();
		}
		
		private void initStack() throws Exception
		{
			int verificatinInfoTag = fis.read();
			verificationInfo = verificationInfoFromTag(verificatinInfoTag);
		}
		
		private void initOffsetDelta() throws IOException
		{
			byte[] offsetDeltaBytes = new byte[OFFSET_DELTA_NUM_BYTES];
			fis.read(offsetDeltaBytes);
			offsetDelta = (int) Structure.valueFromBytes(offsetDeltaBytes);
		}
	}
	
	public class StackMapFrame
	{
		private int tag;
		
		public StackMapFrame(int tag) throws IOException
		{
			this.tag = tag;
		}
		
		@Override
		public String toString()
		{
			return String.format("stack_map_frame_tag: %d \n", tag);
		}
	}
	
	public class SameFrame extends StackMapFrame
	{

		public SameFrame(int tag) throws IOException 
		{
			super(tag);
		}	
		
		@Override
		public String toString()
		{
			return String.format("%s \n %s \n", super.toString(), "same_frame");
		}
	}
	

	public enum VeriInfoTag
	{
		ITEM_TOP (0),
		ITEM_INTEGER (1),
		ITEM_FLOAT(2),
		ITEM_LONG(4),
		ITEM_DOUBLE(3),
		ITEM_NULL(5),
		ITEM_UNINITIALIZED_THIS (6),
		ITEM_OBJECT (7),
		ITEM_UNITIALIZE (8);
		
		VeriInfoTag (int tag)
		{
			this.tag = tag;
		}
		
		public int tag()
		{
			return tag;
		}
		
	
		private int tag;
	}
	
	public class VerificationInfo
	{
		private int tag;

		
		public VerificationInfo(int tag)
		{
			this.tag = tag;
		}
		
		@Override
		public String toString()
		{
			return String.format("verification_info_tag: %d \n", tag);
		}
	
	}
	
	public class TopVariableInfo extends VerificationInfo
	{
		public TopVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), "top_variable_info");
		}
	}
	
	public class IntegerVariableInfo extends VerificationInfo
	{
		public IntegerVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), "integer_variable_info");
		}
	}

	public class FloatVariableInfo extends VerificationInfo
	{
		public FloatVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), "float_variable_info");
		}
	}
	
	public class LongVariableInfo extends VerificationInfo
	{
		public LongVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), "long_variable_info");
		}
	}
	
	public class DoubleVariableInfo extends VerificationInfo
	{
		public DoubleVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), "double_variable_info");
		}
	}
	
	public class NullVariableInfo extends VerificationInfo
	{
		public NullVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), "null_variable_info");
		}
	}
	
	public class UnitializedThisVariableInfo extends VerificationInfo
	{
		public UnitializedThisVariableInfo(int tag)
		{
			super(tag);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s", super.toString(), 
							"unitializedThis_variable_info");
		}
	}

	
	public class ObjectVariableInfo extends VerificationInfo
	{
		private int cpoolIndex;
		private static final int C_POOL_INDEX_NUM_BYTES = 2;
		
		public ObjectVariableInfo(int tag) throws IOException
		{
			super(tag);
			
			byte[] cpoolIndexBytes = new byte[C_POOL_INDEX_NUM_BYTES];
			fis.read(cpoolIndexBytes);
			cpoolIndex = (int) Structure.valueFromBytes(cpoolIndexBytes);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s \n cpoolndex: %d", 
					super.toString(), 
					"object_variable_info",
					cpoolIndex);
		}
		
	}
	
	public class UnitializedVariableInfo extends VerificationInfo
	{
		private int uOffSet;
		
		public UnitializedVariableInfo(int tag) throws IOException
		{
			super(tag); 
			byte[] uOffSetBytes = new byte[uOffSetNumBytes];
			fis.read(uOffSetBytes);
			uOffSet = (int) Structure.valueFromBytes(uOffSetBytes);
		}
		
		@Override
		public String toString()
		{
			return String.format
					("%s \n %s \n offset: %d", 
					super.toString(), 
					"unitialized_variable_info",
					uOffSet);
		}
	}
}
