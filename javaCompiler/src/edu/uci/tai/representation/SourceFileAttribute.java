package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class SourceFileAttribute extends Attribute 
{
	private static final int SOURCE_FILE_INDEX_NUM_BYTES = 2;
	private int sourceFileIndex; 
	
	public SourceFileAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("SourceFile");
		init();
	}
	
	private void init() throws IOException
	{
		byte[] sourceFileIndexBytes = new byte[SOURCE_FILE_INDEX_NUM_BYTES];
		fis.read(sourceFileIndexBytes);
		sourceFileIndex = (int) Structure.valueFromBytes(sourceFileIndexBytes);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(String.format("source_file_index: %d \n", sourceFileIndex));
		
		return builder.toString();
		
	}

}
