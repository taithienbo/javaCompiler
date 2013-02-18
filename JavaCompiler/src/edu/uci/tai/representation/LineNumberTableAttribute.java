package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class LineNumberTableAttribute extends Attribute 
{

	public LineNumberTableAttribute(FileInputStream fis) throws IOException 
	{
		super(fis);
		setName("LineNumberTable");
	}

}
