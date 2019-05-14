package generics.CompareAndBounds.comparing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MaxOfCollection {
	
	public static void main(String[] args) {
				
		var a1 = new Apple(5);
		var a2 = new Apple(7);
		var o1 = new Orange(5);
		var o2 = new Orange(7);
		
		var apples = Arrays.asList(a1,a2);
		var oranges = Arrays.asList(o1,o2);
		var mix = Arrays.asList(a2,o1);
		
		//Type: The T is Apple; Apple extends Comparable<? super T>;
		//Comparator: The ? is Apple and T is also Apple; Apple is super of itself.
		// Collection: The ? is Apple and T is also Apple; Apple extends itself.
		System.out.println(max(apples));
		
		//Type: The T is Orange; Orange extends Comparable<? super T>;
		//Comparator: The ? is Orange and T is also Orange; Orange is super of itself.
		// Collection: The ? is Orange and T is also Orange; Orange extends itself.
		System.out.println(max(oranges));
		
		//Type: The T is Fruit; Fruit extends Comparable<? super T>;
		//Comparator: The ? is Fruit and T is Fruit; Fruit is super of itself.
		// Collection: The ? is Orange/Apple and T is Fruit; Orange/Apple extends Fruit.
		System.out.println(max(mix));
	
	}

	//In the book: Type-Parameters for Comparator.<T>naturalOrder must be explicit for generic methods;
	//Algorithm that infers would fail other-wise.
	//Now it works without it.
	static <T extends Comparable<? super T>> T max(Collection<? extends T> c) {
		return max(c, Comparators.naturalOrder());
	}

	static <T extends Comparable<? super T>> T min(Collection<? extends T> c) {
		return max(c, Comparators.reverseOrder());
	}
	
	//Iterator is used for efficiency; 
	//A for-each loop was used before for clarity, 
	//but iterator was called twice since a Collection doesn't have a get method.
	//We use extends for Collection; we get values of type T from it.
	//We use super for Comparable since we put values of type T into the compareTo method.
	//the wild-card with super is needed to compare oranges/Apples. Else you could only compare Fruit.
	//if we take T to be Orange; then Orange extends Comparable<? super Orange>.
	//Orange extends comparable<Fruit> and Fruit is super of Orange.
	//The Signature: T bounded by Comparable.
	//The Bound is recursive; The Bound on T depends on itself.
	//Unlike wild-cards, Type variables must always use extends, never use super.
	static <T>  T max(Collection<? extends T> coll, Comparator<? super T> com){
		Iterator<? extends T> iter = coll.iterator();
		T candid = iter.next();
		while(iter.hasNext()) {
			T next = iter.next();
			if(com.compare(candid, next) < 0) candid = next;
		}
		return candid;
	}
	
	static <T>  T min(Collection<? extends T> coll, Comparator<? super T> com){
		return max(coll, Comparators.reverseOrder(com));
	}
	
}
