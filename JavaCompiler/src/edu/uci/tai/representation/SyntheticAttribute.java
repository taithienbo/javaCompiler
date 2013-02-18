package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class SyntheticAttribute extends Attribute 
{

	public SyntheticAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("Synthetic");
	}

}
