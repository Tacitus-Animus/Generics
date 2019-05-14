package generics.basic;

import java.util.Arrays;
import java.util.List;

public class NoWild {

	public static void main(String[] args) {

		//List<Integer> isn't sub-type of List<Number>
		//thats why you get compile-time error.
		List<Integer> ints = Arrays.asList(1,2);
		
		//List<Number> nums = ints; //compile error; 
				
		//nums.add(3.14); //works since double is sub-type of Number; 
	}

}
