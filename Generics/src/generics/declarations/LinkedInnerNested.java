package generics.declarations;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedInnerNested<E> extends AbstractCollection<E> {

	//Non-Static inner class; nested.
	//Since it's not static, Type E is in the scope of Node.
	//Non-Static Inner classes are less preferred to static-nested classes.
	private class Node {
		private E element;
		private Node next = null;
		private Node(E element) {
			
			this.element = element;
		}
	}
	
	private Node first = new Node(null);	
	private Node last = first;
	private int size = 0;
	
	@Override
	public boolean add(E element) {
		last.next = new Node(element);
		last = last.next;
		size++;
		return true;
	}
	
	public LinkedInnerNested() {};
	
	public LinkedInnerNested(Collection<? extends E> c) {
		addAll(c);
	}
	//Anonymous iterator with shared type.
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			Node current = first;
			
			@Override
			public boolean hasNext() {
				return current.next != null;
			}

			@Override
			public E next() {
				if(hasNext()) {
					current = current.next;
					return current.element;
				}else throw new NoSuchElementException();
			}
			
		};
	}

	@Override
	public int size() {
		return size;
	}

}
