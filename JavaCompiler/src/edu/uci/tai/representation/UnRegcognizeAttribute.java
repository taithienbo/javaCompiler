package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

public class UnRegcognizeAttribute extends Attribute {

	public UnRegcognizeAttribute(FileInputStream fis) throws IOException {
		super(fis);
		setName("Unrecognizable name");
	}

}
