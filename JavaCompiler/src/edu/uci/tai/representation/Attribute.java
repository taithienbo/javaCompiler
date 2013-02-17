package edu.uci.tai.representation;

import java.io.FileInputStream;

public class Attribute 
{
	private String name;
	private int attributeLength;
	private FileInputStream fis;
	
	public Attribute(FileInputStream fis)
	{
		this.fis = fis;
	}
	
	@Override
	public String toString()
	{
		return String.format("Attribute name: %s \n" ,name);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
