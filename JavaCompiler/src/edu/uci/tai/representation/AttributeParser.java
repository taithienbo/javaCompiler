package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.ConstantUtf8;
import edu.uci.tai.constantPool.Structure;
import edu.uci.tai.parser.Main;

public class AttributeParser 
{
	private FileInputStream fis;
	private static final int NAME_INDEX_NUM_BYTES = 2;
	private ConstantUtf8 constantUtf8;
	private long byteCodeLength;
	
	public AttributeParser(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		init();
	}
	
	public AttributeParser(FileInputStream fis, long byteCodeLength) throws IOException
	{
		this (fis);
		this.byteCodeLength = byteCodeLength;
	}
	
	private void init() throws IOException
	{
		byte[] nameIndex = new byte[NAME_INDEX_NUM_BYTES];
		fis.read(nameIndex);

		int index = (int) Structure.valueFromBytes(nameIndex);
		Structure structure = Main.constantPool.getStructure(index);
		
		if (structure instanceof ConstantUtf8)
			constantUtf8 = (ConstantUtf8) structure;
	}
	
	public Attribute parseAttribute() throws Exception
	{
	
		String attributeName = constantUtf8.data();
		
		if (attributeName.equals("SourceFile"))
			return new SourceFileAttribute(fis);
		else if (attributeName.equals("ConstantValue"))
			return new ConstantValueAttribute(fis);
		else if (attributeName.equals("Code"))
			return new CodeAttribute(fis);
		else if (attributeName.equals("Exceptions"))
			return new ExceptionAttribute(fis);
		else if (attributeName.equals("InnerClasses"))
			return new InnerClassesAttribute(fis);
		else if (attributeName.equals("Synthetic"))
			return new SyntheticAttribute(fis);
		else if (attributeName.equals("LineNumberTable"))
			return new LineNumberTableAttribute(fis);
		else if (attributeName.equals("LocalVariableTable"))
			return new LocalVariableTableAttribute(fis);
		else if (attributeName.equals("Deprecated"))
			return new DepricatedAttribute(fis);
		else if (attributeName.equals("StackMapTable"))
			return new StackMapTableAttribute(fis, byteCodeLength);
		return new UnRegcognizeAttribute(fis);
			
	}
}
