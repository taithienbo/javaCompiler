package edu.uci.tai.representation;

import java.io.FileInputStream;

public class InnerClassesAttribute extends Attribute 
{

	/**
	 * @param fis
	 */
	public InnerClassesAttribute(FileInputStream fis) 
	{
		super(fis);
		setName("InnerClasses");
	}

}
