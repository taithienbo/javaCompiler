package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class LocalVariableTableAttribute extends Attribute {

	public LocalVariableTableAttribute(FileInputStream fis) throws IOException {
		super(fis);
		setName("LocalVariableTable");
	}

}
