package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class InnerClassesAttribute extends Attribute 
{

	/**
	 * @param fis
	 * @throws IOException 
	 */
	public InnerClassesAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("InnerClasses");
	}

}
