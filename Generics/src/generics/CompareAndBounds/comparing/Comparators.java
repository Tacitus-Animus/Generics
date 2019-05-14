package generics.CompareAndBounds.comparing;

import java.util.Comparator;
import java.util.List;

public class Comparators {
	
	static <T extends Comparable<? super T>> Comparator<T> naturalOrder(){
		return (o1, o2) -> o1.compareTo(o2);
	}
	
	static <T extends Comparable<? super T>> Comparator<T> reverseOrder(){
		return (o1, o2) -> o2.compareTo(o1);
	}
	
	static <T> Comparator<T> reverseOrder(final Comparator<T> com){
		return (o1, o2) -> com.compare(o2, o1);
	}

	static <T> Comparator<List<T>> listComparator(final Comparator<? super T> cmp){
		return (l1, l2) -> {
			int n1 = l1.size();
			int n2 = l2.size();
			for(int i = 0; i < Math.min(n1, n2); i++) {
				int k = cmp.compare(l1.get(i), l2.get(i));
				if(k != 0) return k;
			}
			return (n1 < n2) ? -1 
				 : (n1 == n2) ? 0
			                  : 1;
		};
	}
}
