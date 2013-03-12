package edu.tai.SSA.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.tai.SSA.BasicBlock;
import edu.tai.SSA.BasicBlockFactory;

public class BasicBlockFactoryTest 
{
	private static final int[] test1 = new int[]
	      {3, 60, 27, 16, 20, 96, 61, 28, 62, 27, 154, 0, 6, 16,
	    	  53, 62, 29, 184, 0, 4, 177};
	
	private static final int TEST1_EXPECTED_MAP_SIZE = 3;
	private static final int[] TEST2 = 
			new int[]
			{3, 60, 27, 16, 20, 96, 61, 28, 62, 29, 158, 0, 9, 16, 53, 62, 167,
				0, 6, 16, 42, 62, 29, 184, 0, 4, 177};
	private static final int TEST2_EXPECTED_MAP_SIZE = 4;
	
	
	private BasicBlockFactory basicBlockFactory;
	
	@Before
	public void setUp() throws Exception 
	{
		basicBlockFactory = new BasicBlockFactory();
	}

	@After
	public void tearDown() throws Exception 
	{
	}


	@Test
	public void Test1()
	{
		Map<Integer, BasicBlock> basicBlockMap = 
				basicBlockFactory.generateBasicBlocks(test1);
		System.out.println(basicBlockMap);
		assertEquals(TEST1_EXPECTED_MAP_SIZE, basicBlockMap.size());
		
		basicBlockMap = 
				basicBlockFactory.generateBasicBlocks(TEST2);
		System.out.println(basicBlockMap);
		assertEquals(TEST2_EXPECTED_MAP_SIZE, basicBlockMap.size());
		
	}

}
