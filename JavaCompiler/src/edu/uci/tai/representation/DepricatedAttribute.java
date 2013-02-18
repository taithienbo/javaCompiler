package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class DepricatedAttribute extends Attribute 
{

	public DepricatedAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("Depricated");
	}

}
