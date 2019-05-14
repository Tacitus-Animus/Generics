package generics.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Helper class to merge 2 collections together.
 * <p>Elements must extends <b>comparable</b>,
 * since the {@code merge} method takes advantage of <b>natural ordering</b> of elements.
 * @author Alexander Paul
 * 
 */
public class Merger {

	/**
	 * Merges two collections.
	 * <p> Elements must extend comparable,
	 * since merge implements natural ordering.
	 * @param c1 collection to be merged with the other collection.
	 * @param c2 collection to be merged with the other collection.
	 * @return a merged list of both collections.
	 */
	static <T extends Comparable<? super T>> List<T> merge(Collection<? extends T> c1, Collection<? extends T> c2) {
		
		List<T> mergedList = new ArrayList<>();
		
		Iterator<? extends T> it1 = c1.iterator();
		Iterator<? extends T> it2 = c2.iterator();
		
		T el1 = getNextElement(it1);
		T el2 = getNextElement(it2);
		
		//keep merging while there are still elements left in iterators.
		while(el1 != null || el2 != null) {
			//may possibly keep adding from the first iterator until it's empty;
			//then go through the second.
			boolean use1 = el2 == null || el1 != null && el1.compareTo(el2) < 0;
			if(use1) {
				mergedList.add(el1);
				el1 = getNextElement(it1);
			}else {
				mergedList.add(el2);
				el2 = getNextElement(it2);
			}
		}
		return mergedList;
	}

	/**
	 * helper method that extracts the next element from given iterator.
	 * @param it iterator to get next element, else throws {@code NullPointerException}.
	 * @returns next element from iterator.
	 */
	private static <E> E getNextElement(Iterator<? extends E> it) {
		if(it.hasNext()) {
			E element = it.next();
			if(element == null) throw new NullPointerException();
			else return element;
		}else return null;
	}
	
}
