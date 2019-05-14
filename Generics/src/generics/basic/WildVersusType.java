package generics.basic;

import java.util.Arrays;

public class WildVersusType {

	public static void main(String[] args) {

		Object one = "one";
		
		var objs = Arrays.asList("one", 2, 3.14, 4);
		var ints = Arrays.asList(2, 4);
		
		//There wouldn't be use for checking if a list of integers contains an object,
		//but is used only for backwards compatibility;
		//since there was issues with non-generic code.
		objs.contains(one);
		objs.containsAll(ints);
		assert !ints.contains(one);
		assert !ints.containsAll(objs);
		
	}

	//Wild-Card Parameter...
	//how the utility class Collections looks like; for backwards-compatibility.
	private interface Collection<E>{
		//no generics
		boolean contains(Object o);
		//Collection<?> stands for Collection<? extends Object>; most common use of wild-cards.
		boolean containsAll(java.util.Collection<?> c);
	}
	
	//Type Parameters: alternative design in which you can only test containment for sub-type of the element-type.
	//Now assert !ints.contains(one) or assert !ints.containsAll(objs) won't work;
	//we can only test whether a list contains an element of a sub-type of that list.
	//IOW(in other words)... we can check whether a list of objects contains Integers, but not the other way around.
	//This one catches more errors at compile-time.
	//This is better for modern, generic collections.
	interface MyCollection<E>{
		boolean contains(E o);
		boolean containsAll(java.util.Collection<? extends E> c);
	}
	
	
	
}
