
package generics.reification;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * The Principle of Truth in Advertising:The reified type of an array must be a sub-type of the erasure of it's static type.
 * Requires that the run-time type of an array is properly reified.
 * We can use reflection to get information at run-time,
 * granted we give the type via an argument, not as type parameter.
 */ 
public class Right {

	@Test
	public void testStringListToArray() {
		var list = Arrays.asList("one", "two");
		
		String[] arrayA = ListtoArray(list, new String[0]);
		
		Assertions.assertTrue(Arrays.toString(arrayA).equals("[one, two]"));

		String[] arrayB = new String[] { "x", "x", "x", "x"};
		ListtoArray(list, arrayB);
		Assertions.assertTrue(Arrays.toString(arrayB).equals("[one, two, null, x]"));
	}
	
	@Test
	public void testArraytoString() {
		var list = new int[] { 1,2,3};
		//Learned something new today... Arrays's toString method iterates over the elements of an array,
		//while an Array object's toString prints out it's class name plus a hex string of its hash-code :P
		Assertions.assertFalse(Arrays.toString(list).equals(list.toString()));
	}
	
	@Test
	public void testClassytoArray() {
		var list = Arrays.asList("one", "two");
		String[] array = ClassytoArray(list, String.class);
		Assertions.assertTrue(Arrays.toString(array).equals("[one, two]"));
	}
	
	@Test
	public void testBuiltInToArray() {
		//or you could just...
		var other = Arrays.asList("Don't", "Use", "Reflect.");
		//ironically... toArray method will use reflection.
		String[] way = other.toArray(new String[other.size()]);
		Assertions.assertTrue(Arrays.toString(way).equals("[Don't, Use, Reflect.]"));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] ListtoArray(Collection<T> col, T[] array) {
		
		//This won't work since it's not initialized.
		//Since we can't initialize it here, because we don't know what type T will be,
		//We ask the user to create the Array type.
		//We then use reflection to get information at run-time.
		//T[] array;
		
		//java.lang.reflect.Array.newInstance takes a component type of a class. In this case the class is T[] and the component type is T.
		//Array.newInstance uses the component type and size to allocate an create T[].
		//I might create an array, but we still need to cast since the newInstance is an object. 
		//Why? Generally newInstance will return something like int[], which is a sub-type Object, not Object[].
		//But T must stand for a reference type.
		if(array.length < col.size()) array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), col.size());
			int i = 0;
			for(T x : col) array[i++] = x;
			//Marks the end of the array if it's smaller than the array we're putting into.
			if(i < array.length) array[i] = null;
		return array;
	}
	
	//We can use a class token to get the component type.
	//Instead of giving an array as an argument, we give the type that we want to array to have.
	//Example: String.class is of Class<String>.
	@SuppressWarnings("unchecked")
	public static <T> T[] ClassytoArray(Collection<T> coll, Class<T> type) {
		T[] array = (T[]) java.lang.reflect.Array.newInstance(type, coll.size());
		int i = 0;
		for (T x : coll) array[i++] = x;
		return array;
		
	}
	
}
