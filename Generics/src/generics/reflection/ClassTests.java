package generics.reflection;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassTests {

	//The same type is always represented by the same class token.
	@Test
	public void compareTokenToMethod() {
		var byToken = Integer.class;
		Number number = Integer.valueOf(42);
		var byMethod = number.getClass();
				
		//comparing class tokens by the "==" operator,
		//but is more appropriate to use the "equals" method.
		Assertions.assertTrue(byToken == byMethod);
	}
	
	//If you try to reflect a parameterized type,
	// you get the reified info of the corresponding raw type
	@Test
	public void reflectedVsReifiable() {
		var ints = new ArrayList<Integer>();
		var strings = new ArrayList<String>();
		
		Assertions.assertTrue(ints.getClass() == strings.getClass());
		Assertions.assertTrue(ints.getClass() == ArrayList.class);
	}
	
	//Because the class always represent a reifiable type,
	// there is no point in parameterizing the class Class with a type that is not reifiable.
	//So, using "getClass" and class literals -Arraylist.class for example- are the main ways 
	//to yield a reifiable type for the type parameter.
	//The corresponding raw type is list and Class<ArrayList> is sup-type of Class<? extends List>.
	@Test
	public void erasure() {
		List<Integer> ints = new ArrayList<>();
		Class<? extends List> clazz = ints.getClass();
	
		Assertions.assertTrue(clazz == ArrayList.class);
	}
	
}
