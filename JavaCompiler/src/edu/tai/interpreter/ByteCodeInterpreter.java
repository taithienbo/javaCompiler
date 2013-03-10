package edu.tai.interpreter;

import java.util.ArrayList;
import java.util.List;

import edu.tai.interpreter.InstructionParserBuilder.ParserException;
import edu.tai.interpreter.inst.Instruction;
import edu.tai.interpreter.inst.Instruction.InstructionException;
import edu.tai.interpreter.inst.UnKnownInstruction;
import edu.uci.tai.parser.Main;
import edu.uci.tai.representation.CodeAttribute;
import edu.uci.tai.representation.MethodArray.Method;

public class ByteCodeInterpreter 
{

	public ByteCodeInterpreter() 
	{

	}
	
	public InstructionSequence interpretMethod
				(Method method, int[] args) throws ParserException
	{
		CodeAttribute codeAttri = method.getCodes();
		State state = new State( codeAttri.getMaxStack(), 
				codeAttri.getMaxLocal(), 
				args, Main.constantPool);
		
		List<Integer> codes = codeAttri.getCodes();
		InstructionParserBuilder instPar = new InstructionParserBuilder();

		InstructionSequence instSeq = new InstructionSequence();
		
		for (int code : codes)
			instSeq.addInstruction(new UnKnownInstruction(code));
		
		for (int i = state.getIndex(); i < codes.size(); 
				i = state.incrementIndex())
		{
			instSeq.setInstructionAtIndex
			(i, instPar.getParser(codes.get(i))
					.parseInstruction(instSeq, state));
		}
		
		state.setIndex(0);
		for (int i = state.getIndex(); i < codes.size(); 
				i = state.incrementIndex())
		{
			try 
			{
				System.out.println(String.format("operand: %d", 
						instSeq.getInstructionOperand(i)));
				instSeq.getInstruction(i).execute(state);
			}
			catch (InstructionException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return instSeq;
	}
	

}
