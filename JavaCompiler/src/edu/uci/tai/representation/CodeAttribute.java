package edu.uci.tai.representation;

import java.io.FileInputStream;

public class CodeAttribute extends Attribute {

	public CodeAttribute(FileInputStream fis) 
	{
		super(fis);
		
		setName("Code");
	}

}
