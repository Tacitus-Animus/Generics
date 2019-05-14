package generics.reification;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import fp.exceptionTests.TestHelper;

public class Promote {

	@SuppressWarnings("unchecked")
	public static List<String> promote(List<Object> objects){
		for (Object o : objects) {
			if(!(o instanceof String)) throw new ClassCastException();
		}
		//You must cast to List<?> first since its Reifiable.
		//Compiler can guarantee type-safety going from List<?> to List<String>
		//But will send a "unchecked" warning.
		return (List<String>) (List<?>) objects;
	}
	
	//We check if if c is Instance of List<?> since it's reifiable.
	//No warnings are sent and the cast is type-safe.
	public static <T> List<T> asList(Collection<T> c){
		if(c instanceof List<?>) {
			return (List<T>) c;
		}else throw new InvalidParameterException();
	}
	
	@Test
	public void test() {
		
		var objects = Arrays.<Object>asList("one", "two", 3);
		
		//will throw error since, objects has an integer.
		TestHelper.assertThrows(ClassCastException.class, () -> promote(objects));
		
	}
	
}
