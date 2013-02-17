package edu.uci.tai.representation;

import java.io.FileInputStream;

public class DepricatedAttribute extends Attribute 
{

	public DepricatedAttribute(FileInputStream fis) 
	{
		super(fis);
		setName("Depricated");
	}

}
