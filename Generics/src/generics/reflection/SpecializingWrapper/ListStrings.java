package generics.reflection.SpecializingWrapper;

import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

//Uses Specialization of Delegation to wrap a non-reifiable type List of Strings in a Reifiable Type ListString.
//Design balances power against brevity;
//could have used a less complete verion only implementing Random Access.
//or a more efficient version that delegates all 25 methods with the toString Method. 
//See Collections.CheckedList of a model.
public class ListStrings {

	public static ListString wrap(final List<String> list) {
		
		//Specialized class for A list of strings that are randomly accessed.
		//An AbstractList is funny: It is an Abstract Collection, but also has List behavior;
		//Both of which are in fact, Collections...
		//Meaning, They are both Iterable... why...???
		//The use of random access if ArrayList is being wrapped.
		class Random extends AbstractList<String> implements ListString, RandomAccess
		{
			@Override
			public String get(int index) {return list.get(index);}
			@Override
			public int size() {return list.size();}
			@Override
			public String set(int index, String element) {return super.set(index, element);}
			@Override
			public String remove(int index) {return list.remove(index);}
			@Override
			public void add(int index, String element) {list.add(index, element);}
		}
		
		//Specialized Class of a List of Strings that are sequentially accessed(Linked List).
		//An Anonymous List Iterator is used to get a view of an underlying iterator and delegates for better security
		//and ensures casts are inserted.
		//It works like checkedList, but casts are done by compiler, while checkedlist is done by reflection.
		//generics usually render these redundant, but useful for legacy code or unchecked warnings.
		class Sequential extends AbstractSequentialList<String> implements ListString
		{
			@Override
			public ListIterator<String> listIterator(int index) {
				final ListIterator<String> it = list.listIterator(index);
				return new ListIterator<String>() 
				{
					@Override
					public void add(String s) {it.add(s);}
					@Override
					public boolean hasNext() {return it.hasNext();}
					@Override
					public String next() {return it.next();}
					@Override
					public boolean hasPrevious() {return  it.hasPrevious();}
					@Override
					public String previous() {return it.previous();}
					@Override
					public int nextIndex() {return it.nextIndex();}
					@Override
					public int previousIndex() {return it.previousIndex();}
					@Override
					public void remove() {it.remove();}
					@Override
					public void set(String s) {it.set(s);}
				};
			}
			@Override
			public int size() {return list.size();}
		}
		//wraps depending on list type.
		return list instanceof RandomAccess ? new Random() : new Sequential();
	}	
}
