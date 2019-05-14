
package generics.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Collections {

	//Calling copy on ArrayList<Integer> will return a new ArrayList<Integer>.
	//Same with a HashSet of Strings.
	public static <T, C extends Collection<T>> C copy(C collection) {
		C copy = null;
		try {
			copy = GenericReflection.newInstance(collection);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		copy.addAll(collection);
		return collection;
		
	}
	
	//Example of the Principle of Truth in Advertising(Put in what you want out).
	//Replace unchecked cast with Generic Reflection Library.
	public static <T> T[] toArray(Collection<T> collection, T[] array) {
		if(array.length < collection.size()) {
			array = GenericReflection.newArray(array, collection.size());
		}
		int i = 0; for(T object : collection) array[i++] = object;
		if(i < array.length) array[i] = null;
		return array;
	}
	
	@Test
	public void testCopy() {
		var ints = new ArrayList<Integer>(Arrays.asList(1,2,3));
		var intsCopy = copy(ints);
		
		Assertions.assertTrue(ints.equals(intsCopy));

		var set = new HashSet<String>(Arrays.asList("one", "two", "three"));
		var setCopy = Collections.copy(set);
		
		Assertions.assertTrue(set.equals(setCopy));
	}
	
	@Test 
	public void testArray() {
		var collection = Arrays.asList(1,2,3);
		var array = new Integer[0];
		Assertions.assertAll(() -> toArray(collection, array));
	}
	
	@Test
	public void testReflect() {
		System.out.println(GenericReflection.reflect(new HashMap<Integer,String>().getClass()));
	}
	
}
