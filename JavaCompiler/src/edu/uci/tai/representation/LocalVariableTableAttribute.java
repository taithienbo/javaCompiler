package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class LocalVariableTableAttribute extends Attribute 
{

	private LocalVariableTableEntry[] entries;
	private static final int LOCAL_VAR_TABLE_LENG_NUM_BYTES = 2;
	
	public LocalVariableTableAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("LocalVariableTable");
		init();
	}
	
	private void init() throws IOException
	{
		byte[] numOfEntries = new byte[LOCAL_VAR_TABLE_LENG_NUM_BYTES];
		fis.read(numOfEntries);
		entries = new LocalVariableTableEntry[(int) Structure.valueFromBytes(numOfEntries)];
		for (int i = 0; i < entries.length; i++)
			entries[i] = new LocalVariableTableEntry();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(String.format("num_of_local_variable_table_entries: %d \n", entries.length));
		builder.append(entries);
		builder.append("\n");
		return builder.toString();
	}
	
	private class LocalVariableTableEntry
	{
		private static final int START_PC_NUM_BYTES = 2;
		private static final int LENGTH_NUM_BYTES = 2;
		private static final int NAME_INDEX_NUM_BYTES = 2;
		private static final int DESCRIPTOR_INDEX_NUM_BYTES = 2;
		private static final int INDEX_NUM_BYTES = 2;
		
		private int startPc;
		private int length;
		private int nameIndex;
		private int descriptorIndex;
		private int index;

		public LocalVariableTableEntry() throws IOException
		{
			initStartPc();
			initLength();
			initNameIndex();
			initDescriptorIndex();
			initIndex();
		}
		
		private void initStartPc() throws IOException 
		{
			byte[] startPcBytes = new byte[START_PC_NUM_BYTES];
			fis.read(startPcBytes);
			startPc = (int) Structure.valueFromBytes(startPcBytes);
		}
		
		private void initLength() throws IOException
		{
			byte[] lengthBytes = new byte[LENGTH_NUM_BYTES];
			fis.read(lengthBytes);
			length = (int) Structure.valueFromBytes(lengthBytes);
		}
		
		private void initNameIndex() throws IOException 
		{
			byte[] nameIndexBytes = new byte[NAME_INDEX_NUM_BYTES];
			fis.read(nameIndexBytes);
			nameIndex = (int) Structure.valueFromBytes(nameIndexBytes);
		}
		
		private void initDescriptorIndex() throws IOException
		{
			byte[] descriptorIndexBytes = new byte[DESCRIPTOR_INDEX_NUM_BYTES];
			fis.read(descriptorIndexBytes);
			descriptorIndex = (int) Structure.valueFromBytes(descriptorIndexBytes);
		}
		
		private void initIndex() throws IOException
		{
			byte[] indexBytes = new byte[INDEX_NUM_BYTES];
			fis.read(indexBytes);
			nameIndex = (int) Structure.valueFromBytes(indexBytes);
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder("local_variable_table: \n");
			builder.append(String.format("start_pc: %d \n", startPc));
			builder.append(String.format("length: %d \n", length));
			builder.append(String.format("name_index: %d \n", nameIndex));
			builder.append(String.format("descriptor_index: %d \n", descriptorIndex));
			builder.append(String.format("index: %d \n", index));
			return builder.toString();
		}
	}
}
