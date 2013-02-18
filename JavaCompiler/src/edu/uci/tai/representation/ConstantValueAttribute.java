package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class ConstantValueAttribute extends Attribute {

	public ConstantValueAttribute(FileInputStream fis) throws IOException {
		super(fis);
		
		setName("ConstantValue");
	}

}
