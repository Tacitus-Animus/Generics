package generics.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildWithExtends {

	public static void main(String[] args) {
		
		//List<Number> is a sub-type of Collection<Number>
		//List<Integer> is a sub-type of Collection<? extends Number>
		//using addAll TO GET Integer (which extends Number) to put in List<Number
		//if a structure contains elements with type of the form ? extends E, we can get elements out of the structure.
		List<Integer> ints = Arrays.asList(1,2);
		List<Double> dbls = Arrays.asList(3.0, 4.0);
		List<Number> nums= new ArrayList<>();
		nums.addAll(ints);
		nums.addAll(dbls);
	}

}
