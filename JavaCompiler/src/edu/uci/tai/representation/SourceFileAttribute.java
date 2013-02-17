package edu.uci.tai.representation;

import java.io.FileInputStream;

public class SourceFileAttribute extends Attribute {

	public SourceFileAttribute(FileInputStream fis) 
	{
		super(fis);
		setName("SourceFile");
	}

}
