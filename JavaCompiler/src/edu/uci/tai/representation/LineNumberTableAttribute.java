package edu.uci.tai.representation;

import java.io.FileInputStream;

public class LineNumberTableAttribute extends Attribute {

	public LineNumberTableAttribute(FileInputStream fis) {
		super(fis);
		setName("LineNumberTable");
	}

}
