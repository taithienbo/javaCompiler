package edu.uci.tai.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import edu.tai.interpreter.ByteCodeInterpreter;
import edu.tai.interpreter.InstructionParserBuilder.ParserException;
import edu.tai.interpreter.InstructionSequence;
import edu.uci.tai.constantPool.ConstantPool;
import edu.uci.tai.constantPool.Structure;
import edu.uci.tai.representation.Attribute;
import edu.uci.tai.representation.AttributeParser;
import edu.uci.tai.representation.Class.SuperClass;
import edu.uci.tai.representation.FieldArray;
import edu.uci.tai.representation.InterfaceArray;
import edu.uci.tai.representation.MagicNumber;
import edu.uci.tai.representation.MethodArray;
import edu.uci.tai.representation.MethodArray.Method;
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
	private static MethodArray methods;
	private Attribute[] attributes;
	
	public Main(String fileName)
	{
		this.fileName = fileName;
		
	}
	
	public static void main(String[] args) throws Exception
	{
		String[] testFiles = new String[]{  "Test5.class",
				};
		
		for (String testFile : testFiles)
		{
			System.out.println("----------------------" + testFile 
					+ "---------------------------");
			Main parser = new Main(testFile);
			parser.parse();
			System.out.println("\n");
		}
		
		ByteCodeInterpreter interpreter = new ByteCodeInterpreter();
		Method main = methods.getMainMethod();
		interpreter.interpretMethod(main, new int[0]);
		
		int i = 200;
		byte b = (byte)200;

		// Will print a negative value but you could *still choose to interpret* this as +200.
		System.out.println(b); 

		// "Upcast" to short in order to easily view / interpret as a positive value.
		// You would typically do this *within* the method that expected an unsigned byte.
		short s = (short) (b & 0xFF);
		System.out.println(s); // Will print a positive value.
		

	}
	
	public void parse() throws Exception
	{
		FileInputStream fis = new FileInputStream(new File(fileName));
		printOutFormatted(fis);
		printOutRaw(fis);
		
	

	}
	

	private void printOutFormatted(FileInputStream fis) throws Exception
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
		methods = new MethodArray(fis);
		System.out.println(methods);
		byte[] attributesCount = new byte[2];
		fis.read(attributesCount);
		attributes = new Attribute
				[(int) Structure.valueFromBytes(attributesCount)];
		System.out.println(String.format("attributes_length: %d", attributes.length));
		for (int i = 0; i < attributes.length; i++)
			attributes[i] = new AttributeParser(fis).parseAttribute();
		for (Attribute a : attributes)
			System.out.println(a);
	}	
	
	public void printOutRaw(FileInputStream fis) throws IOException
	{
		while (fis.available() > 0)
		{
			System.out.println(fis.read());
		}
	}


}
