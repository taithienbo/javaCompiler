package experiment;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConstantLongTest {

	@Test
	public void test() 
	{
		int value = 144;
		int radix = 2;
		String valueAsByteInString = "1001";
	
		Byte valueAsByte = new Byte("1010");
		System.out.println("valuAsByte: " + valueAsByte.intValue());
	//	assertEquals(value, valueAsByte.intValue());
		assertTrue(value == valueAsByte.intValue());
	}

}
