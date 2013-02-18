package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceptionAttribute extends Attribute 
{

	public ExceptionAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("Exceptions");	
	}

}
