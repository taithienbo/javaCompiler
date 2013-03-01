package edu.uci.tai.representation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import edu.uci.tai.constantPool.Structure;

public class Class 
{
	private ArrayList<Method> methods;
	private ArrayList<Field> fields;
	private ArrayList<Attribute> attributes;
	private ArrayList<edu.uci.tai.representation.Class> superClasses;
	
	private static final int NUM_BYTES = 2;
	
	private FileInputStream fis;
	private byte[] data = new byte[NUM_BYTES];
	private String name;
	
	private String tag;
	
	public Class(FileInputStream fis) throws IOException
	{
		this.fis = fis;
		initialize();
	}
	
	private void initialize() throws IOException
	{
		fis.read(data);
	
	}
	
	public int nameIndex()
	{
		return (int) Structure.valueFromBytes(data);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("this_class: \n");
		builder.append(String.format("Name index: %d", nameIndex()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	public class SuperClass extends Class
	{
		private int nameIndex;
		
		public SuperClass(FileInputStream fis) throws IOException 
		{
			super(fis);
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("super_class: \n");
			builder.append(String.format("Name index: %d", nameIndex()));
			builder.append("\n");
			return builder.toString();
		}
		
	}
	
}
