package generics.reification;

import java.util.Arrays;
import java.util.List;

import fp.exceptionTests.TestHelper;

/**
 * 
 * Principle of Indecent Exposure: A library should never publicly expose an array with a non-reifiable type.
 * Requires that the compile-time type of an array must be reifiable.
 * Returning an array with Generic type parameters is a signal of bad code. 
 * Erasure will turn the List<Integer> into List. 
 */
public class DeceptiveLibrary {
	
	//The reifiable type is List, not List<Integer>
	public static List<Integer>[] intLists(int size) {
		@SuppressWarnings("unchecked")
		var ints = new List[size];
		for (int i = 0; i < ints.length; i++) ints[i] = Arrays.asList(i + 1);
		return ints;
	}
	
	@Test
	void testArrayStoreError() {
		TestHelper.assertThrows(ArrayStoreException.class, () -> {
			Integer[] ints = new Integer[] {1};
			Number[] nums = ints;
			nums[0] = 1.01; //can't store a double into an ints array.
			int n = ints[0];
		});
	}
}
