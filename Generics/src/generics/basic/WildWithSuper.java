package generics.basic;

import java.util.Arrays;
import java.util.List;

public class WildWithSuper {

	public static void main(String[] args) {

		//List<Object>
		var objs = Arrays.<Object>asList(2, 3.14, "four");
		
		//List<Integer>
		var ints = Arrays.asList(5,6);
		
		//type parameter is Integer; taken to be implicit, since that is the most specific choice that works.
		copy(objs, ints);
		
		
		WildWithSuper.<Object>copy(objs, ints);
		
		//Number type parameter is permitted, since List<Object> is sub-type of List<? super Number>;
		//In other words, Object is a super-type of Number, as required by the wild-card.
		//List<Integer> is a sub-type of List<? extends Number>;
		//Integer is a sub-type Number, as require by the wild-card.
		WildWithSuper.<Number>copy(objs, ints);
		
		
		WildWithSuper.<Integer>copy(objs, ints);
		
	}

	// works for all Object, Number, Integer type parameters when explicitly called. ex: WildWithSuper.<Number>copy(objs, ints);
	public static <T> void copy(List<? super T> des, List<? extends T> src) {
		for(int i = 0; i < src.size(); i++) {
			des.set(i, src.get(i));
		}
	}
	
	//the three here work for their implicit calls.
	//example for the second below: copy(objs, ints) is the same as <Object>copy(objs, ints).
	//but...explicit calls are restricted (see below)
	
	//first
	//too restrictive; only permits both with exactly same type
//	public static <T> void copy(List<T> des, List<T> src) {
//		for(int i = 0; i < src.size(); i++) {
//			des.set(i, src.get(i));
//		}
//	}
	
	//second
	//only works when type parameter is type Object
//	public static <T> void copy(List<T> des, List< ? extendsT> src) {
//		for(int i = 0; i < src.size(); i++) {
//			des.set(i, src.get(i));
//		}
//	}
	
	//third
	//only works when type parameter is type Integer
//	public static <T> void copy(List<? super T> des, List<T> src) {
//		for(int i = 0; i < src.size(); i++) {
//			des.set(i, src.get(i));
//		}
//	}
	
	
}
