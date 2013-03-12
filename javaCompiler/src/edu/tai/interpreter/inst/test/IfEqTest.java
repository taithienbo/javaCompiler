package edu.tai.interpreter.inst.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;


import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import edu.tai.interpreter.State;
import edu.tai.interpreter.inst.IfEq;
import edu.tai.interpreter.inst.Instruction.InstructionException;

public class IfEqTest 
{
	@Test
	public void test() 
	{
		Injector injector = Guice.createInjector(new IfEqModule());
		IfEq instruction = injector.getInstance(IfEq.class);
		State state = new StateProvider().get();
		try 
		{
			assertEquals(0, state.getIndex());
			instruction.execute(state);
			int index = state.getIndex();
			assertEquals(1, index);
		} 
		catch (InstructionException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private class IfEqModule extends AbstractModule
	{

		@Override
		protected void configure() 
		{
			bindConstant()
			.annotatedWith
			(Names.named("branchByte1"))
			.to(0);
			
			bindConstant()
			.annotatedWith(Names.named("branchByte2"))
			.to(1);
			
		}
		
	}
	
	public class StateProvider implements Provider<State>
	{
		@Override
		public State get() 
		{
			State state = new State(1, 1, new ArrayList<Integer>(), null);
			state.pushToStack(0);

			return state;
		}
		
	}
	


}
