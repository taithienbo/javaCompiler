package edu.uci.tai.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.ConstantPool;
import edu.uci.tai.representation.MagicNumber;
import edu.uci.tai.representation.VersionNumber;


public class Main 
{
	private String fileName;
	private MagicNumber magicNumber;
	private VersionNumber versionNumber;
	private ConstantPool constantPool;
	private edu.uci.tai.representation.Class thisClass;
	private AccessFlag accessFlag;
	

	public Main(String fileName)
	{
		this.fileName = fileName;
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main parser = new Main("aaa.class");
		parser.parse();
	}
	
	public void parse() throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(fileName));


		magicNumber = new MagicNumber(fis);
		versionNumber = new VersionNumber(fis);
		constantPool = new ConstantPool(fis);
		System.out.println(constantPool);
		AccessFlag acessFlag = new AccessFlag(fis);
		
		thisClass = new edu.uci.tai.representation.Class(fis);
		System.out.println(acessFlag);
		System.out.println(thisClass);
		
		// flag
		while (fis.available() > 0)
			System.out.println(fis.read());

	}
	
	public void printOutRaw(FileInputStream fis) throws IOException
	{
		while (fis.available() > 0)
		{
			System.out.println(fis.read());
		}
	}


}
