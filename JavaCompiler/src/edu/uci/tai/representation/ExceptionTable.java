package edu.uci.tai.representation;

import java.io.FileInputStream;
import java.io.IOException;

import edu.uci.tai.constantPool.Structure;

public class ExceptionTable 
{
	private int startPc;
	private int endPc;
	private int handlerPc;
	private int catchType;
	
	private static final int START_PC_NUMB_BYTES = 2;
	private static final int END_PC_NUMB_BYTES = 2;
	private static final int HANDLE_PC_NUMB_BYTES = 2;
	private static final int CATCH_TYPE_NUMB_BYTES = 2;
	
	private FileInputStream fis;
	
	public ExceptionTable(FileInputStream fis) throws IOException 
	{
		this.fis = fis;
		
		initStartPc();
		initEndPc();
		initHandlerPc();
		initCatchType();
	}
	
	private void initStartPc() throws IOException
	{
		byte[] startPcBytes = new byte[START_PC_NUMB_BYTES];
		fis.read(startPcBytes);
		startPc = (int) Structure.valueFromBytes(startPcBytes);
	}
	
	private void initEndPc() throws IOException
	{
		byte[] endPcBytes = new byte[END_PC_NUMB_BYTES];
		fis.read(endPcBytes);
		startPc = (int) Structure.valueFromBytes(endPcBytes);
	}
	
	private void initHandlerPc() throws IOException
	{
		byte[] handlerPcBytes = new byte[HANDLE_PC_NUMB_BYTES];
		fis.read(handlerPcBytes);
		handlerPc = (int) Structure.valueFromBytes(handlerPcBytes);
	}
	
	private void initCatchType() throws IOException
	{
		byte[] catchTypeBytes = new byte[CATCH_TYPE_NUMB_BYTES];
		fis.read(catchTypeBytes);
		startPc = (int) Structure.valueFromBytes(catchTypeBytes);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("exception_table \n");
		builder.append(String.format("start_pc: %d", startPc)).append("\n");
		builder.append(String.format("end_pc: %d", endPc)).append("\n");
		builder.append(String.format("handler_pc: %d", handlerPc)).append("\n");
		builder.append(String.format("catch_type: %d")).append("\n");
		return builder.toString();
	}

}
