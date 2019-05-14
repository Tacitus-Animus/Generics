package generics.reflection.SpecializingWrapper;

import java.util.ArrayList;
import java.util.Collection;

//Uses Specialization of Inheritance to wrap a non-reifiable type List of Strings in a Reifiable Type ArrayListString.
public class ArrayListString extends ArrayList<String> implements ListString {
	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;

	public ArrayListString() {super();}

	public ArrayListString(int initialCapacity) {super(initialCapacity);}

	public ArrayListString(Collection<? extends String> c) {super(c);}

}
