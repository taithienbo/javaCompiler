package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class SourceFileAttribute extends Attribute 
{

	public SourceFileAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("SourceFile");
	}

}
