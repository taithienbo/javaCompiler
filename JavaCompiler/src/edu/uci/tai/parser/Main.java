package edu.uci.tai.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.ConstantPool;
import edu.uci.tai.constantPool.Structure;
import edu.uci.tai.representation.Attribute;
import edu.uci.tai.representation.AttributeParser;
import edu.uci.tai.representation.Class.SuperClass;
import edu.uci.tai.representation.FieldArray;
import edu.uci.tai.representation.InterfaceArray;
import edu.uci.tai.representation.MagicNumber;
import edu.uci.tai.representation.MethodArray;
import edu.uci.tai.representation.VersionNumber;


public class Main 
{
	private String fileName;
	private MagicNumber magicNumber;
	private VersionNumber versionNumber;
	public static ConstantPool constantPool;
	private AccessFlag accessFlag;
	private edu.uci.tai.representation.Class thisClass;
	private SuperClass superClass;
	private InterfaceArray interfaces;
	private FieldArray fields;
	private MethodArray methods;

	public Main(String fileName)
	{
		this.fileName = fileName;
		
	}
	
	public static void main(String[] args) throws IOException
	{
		String[] testFiles = new String[]{ "Test1.class", "Test2.class",
				"Test3.class", "Test4.class", "Test5.class"};
		
		for (String testFile : testFiles)
		{
			System.out.println("----------------------" + testFile + "---------------------------");
			Main parser = new Main(testFile);
			parser.parse();
			System.out.println("\n");
			break;
		}
	
	}
	
	public void parse() throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(fileName));
		printOutFormatted(fis);
		printOutRaw(fis);
		
	

	}
	

	private void printOutFormatted(FileInputStream fis) throws IOException
	{
		magicNumber = new MagicNumber(fis);
		versionNumber = new VersionNumber(fis);
		constantPool = new ConstantPool(fis);
		System.out.println(constantPool);
		AccessFlag acessFlag = new AccessFlag(fis);
		System.out.println(acessFlag);
	thisClass = new edu.uci.tai.representation.Class(fis);
		System.out.println(thisClass);
		superClass = thisClass.new SuperClass(fis);
			System.out.println(superClass);
		interfaces = new InterfaceArray(fis);
		System.out.println(interfaces);
		fields = new FieldArray(fis);
		System.out.println(fields);
	}
	public void printOutRaw(FileInputStream fis) throws IOException
	{
		while (fis.available() > 0)
		{
			System.out.println(fis.read());
		}
	}


}
