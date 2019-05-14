 package generics.reification;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import fp.exceptionTests.TestHelper;

/**
 * 
 * Principle of Indecent Exposure: A library should never publicly expose an array with a non-reifiable type.
 * Requires that the compile-time type of an array must be reifiable.
 * Returning an array with Generic type parameters is a signal of bad code. 
 * Erasure will turn the List<Integer> into List. 
 */
public class InnocentClient {

	@Test
	public static void main(String[] args) {

		var intLists = DeceptiveLibrary.intLists(1);
		
		List<? extends Number>[] numLists = intLists;
		//putting a double into a list of integers!!!
		//It should fail to store a list of doubles,
		//But erasure only sees the reifiable type as list.
		numLists[0] = Arrays.asList(1.01);
		
		TestHelper.assertThrows(ClassCastException.class, () -> {
		int i = intLists[0].get(0); //tries to cast Double to Integer
		})/*.printStackTrace()*/;
		
	}

}


