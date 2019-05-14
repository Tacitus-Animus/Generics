package generics.declarations;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStaticNested<E> extends AbstractCollection<E> {

	//static inner class Node; nested
	//Since it's static, type E is not in the scope of Node, so I has it's own type parameter.
	//Static-Nested classes are more preferred, because they're simpler and more efficient.
	public static class Node<T>{
		private T element;
		private Node<T> next = null;
		private Node(T element) {
			this.element = element;
		}
	}
	
	private Node<E> first = new Node<>(null);
	private Node<E> last = first;
	private int size = 0;
	
	public LinkedStaticNested() {};
	
	public LinkedStaticNested(Collection<? extends E> c) {
		addAll(c);
	};
	
	@Override
	public boolean add(E element) {
		last.next = new Node<E>(element);
		last = last.next;
		size++;
		return true;
	}
	
	//static Iterator with it's own Type Parameter.
	private static class LinkedIterator<T> implements Iterator<T>{

		private Node<T> current;
		
		public LinkedIterator(Node<T> first) {
			current = first;
		}
		
		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				current = current.next;
				return current.element;
			}else throw new NoSuchElementException();
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator<>(first);
	}

	@Override
	public int size() {
		return size;
	}

}
