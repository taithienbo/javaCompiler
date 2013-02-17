package edu.uci.tai.representation;

import java.io.FileInputStream;

public class ExceptionAttribute extends Attribute 
{

	public ExceptionAttribute(FileInputStream fis) 
	{
		super(fis);
		setName("Exceptions");	
	}

}
