package generics.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GetAndPutPrinciple {

	public static void main(String[] args) {

		//use extend when you only get values out of a structure, use super to only put values into a structure, and don't use neither when you do both.
		
		//The first 2 wouldn't work without the extends; getting.
		var ints = Arrays.asList(1,2,3);
		assert sum(ints) == 6.0;
		
		var doubles = Arrays.asList(2.78, 3.14);
		assert sum(doubles) == 5.92;
		
		var numbers = Arrays.asList(1, 2.3, 4.56, 7);
		sum(numbers); 
		
		//the last 2 calls wouldn't be legal without super; putting/setting/adding.
		var ints1 = new ArrayList<Integer>();
		count(ints1, 3);
		
		var numbers1 = new ArrayList<Number>();
		count(numbers1, 3);

		var objects = new ArrayList<Object>();
		count(objects, 3); objects.add("four");
		
		//Don't use wild-cards when putting and getting.
		var numbers2 = new ArrayList<Number>();
		double sum = sumCount(numbers2, 5);
		assert sum == 10;
		
		//extends makes it where you can ONLY GET, not add.
		List<? extends Number> numbers3 = ints;
		double dbl = sum(numbers3);
		//numbers3.add(3.14); compile-error; can't add a double to a list of integers.
		
		
		//super makes it where you can ONLY SET/ADD/Put, not get.
		List<? super Integer> ints2 = objects;
		ints2.add(3);
		//double dbl1 = sum(ints2); compile-error; can't get a sum from a list of strings.
	}
	
	//method gets values out of the src, so uses extends.
	//method puts values in des, so uses super.
	public static <T> void copy(List<? super T> des, List<? extends T> src) {
		for(int i = 0; i < src.size(); i++) {
			des.set(i, src.get(i));
		}
		
	}
	
	//when using an iterator, you're getting values out of a structure; so use extends.
	public static double sum(Collection<? extends Number> nums) {
		double s = 0.0;
		for(Number num : nums) s += num.doubleValue();
		return s;
	}
	
	//since super is used, you can add Integer, Number, and Object;
	public static void count(Collection<? super Integer> ints, int n) {
		for(int i = 0; i < n; i++) ints.add(i);
	}
	
	//adding in count, getting in sum; no wild-card.
	public static double sumCount(Collection<Number> nums, int n) {
		count(nums, 5);
		return sum(nums);
	}
	
}
