package edu.tai.interpreter;

import edu.tai.interpreter.inst.parser.BiPushParser;
import edu.tai.interpreter.inst.parser.GetStaticParser;
import edu.tai.interpreter.inst.parser.GoToParser;
import edu.tai.interpreter.inst.parser.IAddParser;
import edu.tai.interpreter.inst.parser.IIncParser;
import edu.tai.interpreter.inst.parser.ILoad0Parser;
import edu.tai.interpreter.inst.parser.ILoad1Parser;
import edu.tai.interpreter.inst.parser.ILoad2Parser;
import edu.tai.interpreter.inst.parser.ILoad3Parser;
import edu.tai.interpreter.inst.parser.ILoadParser;
import edu.tai.interpreter.inst.parser.IStore0Parser;
import edu.tai.interpreter.inst.parser.IStore1Parser;
import edu.tai.interpreter.inst.parser.IStore2Parser;
import edu.tai.interpreter.inst.parser.IStore3Parser;
import edu.tai.interpreter.inst.parser.Iconst0Parser;
import edu.tai.interpreter.inst.parser.Iconst1Parser;
import edu.tai.interpreter.inst.parser.Iconst2Parser;
import edu.tai.interpreter.inst.parser.Iconst3Parser;
import edu.tai.interpreter.inst.parser.Iconst4Parser;
import edu.tai.interpreter.inst.parser.Iconst5Parser;
import edu.tai.interpreter.inst.parser.IfCmpEqParser;
import edu.tai.interpreter.inst.parser.IfCmpGeParser;
import edu.tai.interpreter.inst.parser.IfCmpGtParser;
import edu.tai.interpreter.inst.parser.IfCmpLtParser;
import edu.tai.interpreter.inst.parser.IfCmpNeqParser;
import edu.tai.interpreter.inst.parser.IfEqParser;
import edu.tai.interpreter.inst.parser.IfGtParser;
import edu.tai.interpreter.inst.parser.IfLeParser;
import edu.tai.interpreter.inst.parser.IfLtParser;
import edu.tai.interpreter.inst.parser.IfNeParser;
import edu.tai.interpreter.inst.parser.InstructionParser;
import edu.tai.interpreter.inst.parser.InvokeStaticParser;
import edu.tai.interpreter.inst.parser.IstoreParser;
import edu.tai.interpreter.inst.parser.UnknownInstructionParser;

public class InstructionParserBuilder 
{
	
	public InstructionParser getParser(int opCode) throws ParserException
	{
		switch (opCode)
		{
		case OpCodes.ICONST_0:
			return new Iconst0Parser();
		case OpCodes.ICONST_1:
			return new Iconst1Parser();
		case OpCodes.ICONST_2:
			return new Iconst2Parser();
		case OpCodes.ICONST_3:
			return new Iconst3Parser();
		case OpCodes.ICONST_4:
			return new Iconst4Parser();
		case OpCodes.ICONST_5:
			return new Iconst5Parser();
		case OpCodes.BIPUSH:
			return new BiPushParser();
		case OpCodes.ILOAD:
			return new ILoadParser();
		case OpCodes.ILOAD_0:
			return new ILoad0Parser();
		case OpCodes.ILOAD_1:
			return new ILoad1Parser();
		case OpCodes.ILOAD_2:
			return new ILoad2Parser();
		case OpCodes.ILOAD_3:
			return new ILoad3Parser();
		case OpCodes.ISTORE:
			return new IstoreParser();
		case OpCodes.ISTORE_0:
			return new IStore0Parser();
		case OpCodes.ISTORE_1:
			return new IStore1Parser();
		case OpCodes.ISTORE_2:
			return new IStore2Parser();
		case OpCodes.ISTORE_3:
			return new IStore3Parser();
		case OpCodes.IADD:
			return new IAddParser();
		case OpCodes.IINC:
			return new IIncParser();
		case OpCodes.IFEQ:
			return new IfEqParser();
		case OpCodes.IFNE:
			return new IfNeParser();
		case OpCodes.IFLT:
			return new IfLtParser();
		case OpCodes.IFGT:
			return new IfGtParser();
		case OpCodes.IFLE:
			return new IfLeParser();
		case OpCodes.IF_ICMPNE:
			return new IfCmpNeqParser();
		case OpCodes.IF_ICMPEQ:
			return new IfCmpEqParser();
		case OpCodes.IF_ICMPGT:
			return new IfCmpGtParser();
		case OpCodes.IF_ICMPGE:
			return new IfCmpGeParser();
		case OpCodes.IF_ICMPLT:
			return new IfCmpLtParser();
		case OpCodes.GOTO:
			return new GoToParser();
		case OpCodes.GETSTATIC:
			return new GetStaticParser();
		case OpCodes.INVOKESTATIC:
			return new InvokeStaticParser();
		case OpCodes.RET:
		default:
			return new UnknownInstructionParser(opCode);
			
		}
	}
	
	public class ParserException extends Exception
	{
		public ParserException(String errorMessage)
		{
			super(errorMessage);
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}
