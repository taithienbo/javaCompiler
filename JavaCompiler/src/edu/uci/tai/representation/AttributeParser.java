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
	
	
	public AttributeParser(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		init();
	}
	
	private void init() throws IOException
	{
		byte[] nameIndex = new byte[NAME_INDEX_NUM_BYTES];
		fis.read(nameIndex);
		
		int index = (int) Structure.valueFromBytes(nameIndex);
		Structure structure = Main.constantPool.getStructure(index);
		
		if (structure instanceof ConstantUtf8)
			constantUtf8 = (ConstantUtf8) structure;
		else
			System.out.println("unable to retrieve attribute info with index: " + index);
			/*throw new RuntimeException(String.format("structure at index %d is %s not %s", 
					index, structure.getClass().getSimpleName(), 
					ConstantUtf8.class.getSimpleName()));*/
	}
	
	public Attribute parseAttribute() throws IOException
	{
		if (constantUtf8 == null)
			return new UnRegcognizeAttribute(fis);
		
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
		
		return new UnRegcognizeAttribute(fis);
			
	}
}
