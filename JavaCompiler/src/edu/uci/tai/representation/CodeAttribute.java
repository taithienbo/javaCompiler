package edu.uci.tai.representation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.uci.tai.constantPool.Structure;

public class CodeAttribute extends Attribute 
{
	private static final int MAX_STACK_NUMB_BYTES = 2;
	private static final int MAX_LOCAL_NUMB_BYTES = 2;
	private static final int CODE_LENGTH_NUMB_BYTES = 4;
	private static final int EXCEPTION_TABLE_LENGTH_NUM_BYTES = 2;
	private static final int ATTRIBUTE_COUNT_NUMB_BYTES = 2;
	
	private int maxStack;
	private int maxLocal;
	private long codeLength;
	private ArrayList<Integer> codes;
	private ExceptionTable[] exceptionTables;
	private Attribute[] attributes;
	
	public CodeAttribute(FileInputStream fis) throws Exception 
	{
		super(fis);
		setName("Code");
		initMaxStack();
		initMaxLocal();
		initCodeLength();
		initCodes();
		initExceptionTable();
		initAttributes();
	}
	
	
	private void initMaxStack() throws IOException
	{
		byte[] maxStackBytes = new byte[MAX_STACK_NUMB_BYTES];
		fis.read(maxStackBytes);
		maxStack =  (int) Structure.valueFromBytes(maxStackBytes);
	}
	
	private void initMaxLocal() throws IOException
	{
		byte[] maxLocalBytes = new byte[MAX_LOCAL_NUMB_BYTES];
		fis.read(maxLocalBytes);
		maxLocal = (int) Structure.valueFromBytes(maxLocalBytes);
	}
	
	private void initCodeLength() throws IOException
	{
		byte[] codeLengthBytes = new byte[CODE_LENGTH_NUMB_BYTES];
		fis.read(codeLengthBytes);
		codeLength = 0L;
		
		codeLength += (long) Structure.valueFromBytes(codeLengthBytes);
	}
	
	public int getMaxStack()
	{
		return maxStack;
	}
	
	public int getMaxLocal()
	{
		return maxLocal;
	}
	
	private void initCodes() throws IOException
	{
		codes = new ArrayList<Integer>();
		
		long available = codeLength;
		
			byte[] data = new byte[(int)available];
			fis.read(data);
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			while (bis.available() > 0)
				codes.add(bis.read());
			bis.close();
	}
		
	public List<Integer> getCodes()
	{
		return codes;
	}
	
	private void initExceptionTable() throws IOException
	{
		byte[] numOfEntries = new byte[EXCEPTION_TABLE_LENGTH_NUM_BYTES];
		fis.read(numOfEntries);
		
		exceptionTables = new ExceptionTable[ (int) Structure.valueFromBytes(numOfEntries)];
		
		for (int i = 0; i < exceptionTables.length; i++)
			exceptionTables[i] = new ExceptionTable(fis);
	}
	
	private void initAttributes() throws Exception
	{
		byte[] lengthByte = new byte[ATTRIBUTE_COUNT_NUMB_BYTES];
		fis.read(lengthByte);
		
		attributes = new Attribute[(int) Structure.valueFromBytes(lengthByte)];
		
		for (int i = 0; i < attributes.length; i++)
			attributes[i] = new AttributeParser(fis, codeLength).parseAttribute();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(String.format("max_stack: %d \n", maxStack));
		builder.append(String.format("max_locals: %d \n", maxLocal));
		builder.append("Codes: \n");
		builder.append(codes.toString()).append("\n");
		builder.append(String.format("exception_table_length: %d", exceptionTables.length));
		builder.append("\n");
		for (ExceptionTable table : exceptionTables)
			builder.append(table).append("\n");
		builder.append(String.format("attribute_count: %d", attributes.length)).append("\n");
		for (Attribute a : attributes)
			builder.append(a).append("\n");
		
		return builder.toString();
	}
}
