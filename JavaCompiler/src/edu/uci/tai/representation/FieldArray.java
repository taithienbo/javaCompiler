package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class FieldArray 
{
	private FileInputStream fis;
	private static final int FIELD_COUNT_NUM_BYTES = 2;
	private Field[] fields;
	
	public FieldArray(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		initialize();
	}
	
	private void initialize() throws IOException
	{
		byte[] numFields = new byte[FIELD_COUNT_NUM_BYTES];
		fis.read(numFields);
		
		fields = new Field[(int) Structure.valueFromBytes(numFields)];
		
		System.out.println("In fieldArray, numFields = " + fields.length);
		
		for (int i = 0; i < fields.length; i++)
			fields[i] = new Field(fis);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("fields_info[]: \n");
		builder.append(String.format("length: %d \n", fields.length));
		
		for (Field field : fields)
			builder.append(field).append("\n");
		
		return builder.toString();
	}
	
}
