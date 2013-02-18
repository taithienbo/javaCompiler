package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class LineNumberTableAttribute extends Attribute 
{
	private LineNumberTable[] lineNumberTables;
	private static final int LINE_NUMBER_TABLE_LENGTH_NUM_BYTES = 2;
	
	public LineNumberTableAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("LineNumberTable");
		initLineNumberTables();
	}
	
	private void initLineNumberTables() throws IOException
	{
		byte[] tableLength = new byte[LINE_NUMBER_TABLE_LENGTH_NUM_BYTES];
		fis.read(tableLength);
		
		lineNumberTables = new LineNumberTable[(int) Structure.valueFromBytes(tableLength)];
		
		for (int i = 0; i < lineNumberTables.length; i++)
			lineNumberTables[i] = new LineNumberTable(fis);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(String.format("line_number_table_length: %d \n", lineNumberTables.length));
		
		for (LineNumberTable table : lineNumberTables)
			builder.append(table);
		
		return builder.toString();
	}
	
	private class LineNumberTable
	{
		private FileInputStream fis;
		
		private static final int START_PC_NUM_BYTES = 2;
		private static final int LINE_NUMBER_NUM_BYTES = 2;
		
		private int startPc;
		private int lineNumber;
		
		public LineNumberTable(FileInputStream fis) throws IOException
		{
			this.fis = fis;
			initStartPc();
			initLineNumber();
		}
		
		private void initStartPc() throws IOException
		{
			byte[] startPcByte = new byte[START_PC_NUM_BYTES];
			fis.read(startPcByte);
			
			startPc = (int) Structure.valueFromBytes(startPcByte);
		}
		
		private void initLineNumber() throws IOException
		{
			byte[] lineNumberBytes = new byte[LINE_NUMBER_NUM_BYTES];
			fis.read(lineNumberBytes);
			lineNumber = (int) Structure.valueFromBytes(lineNumberBytes);
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("\t").append(String.format("start_pc: %d", startPc)).append("\n");
			builder.append("\t").append(String.format("line_number: %d", lineNumber)).append("\n");
			
			return builder.toString();
		}
	}
}
