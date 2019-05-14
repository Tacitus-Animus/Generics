package generics.declarations;

import java.util.List;

public class Overloading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean allZero(List<Integer> ints) {
		return ints.stream().allMatch(i -> i == 0);
	}

	//won't work since they both have the same return type and Erasure of List.
//	public static boolean allZero(List<String> ints) {
//		return ints.stream().allMatch(i -> i.isEmpty() || i.equals(""));
//	}
	
	public static int sum(List<Integer> ints) {
		int sum = 0;
		for(int i : ints) {
			sum += i;
		}
		return sum;
	}

	//Since Java 1.5+ This used to work. Since the return types were different, java could differentiate.
	//But now using compiler in compliance with JavaSE-10, see's both as same erasure.
//	public static String sum(List<String> strings) {
//		StringBuffer sum = new StringBuffer();
//		for(String s: strings) {
//			sum.append(s);
//		}
//		return sum.toString();
//	}

	
}
