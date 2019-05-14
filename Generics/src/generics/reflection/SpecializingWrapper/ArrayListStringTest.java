package generics.reflection.SpecializingWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArrayListStringTest {

	@Test
	void assert_Wrapped_Contains_Only_ListString_Type() {
		var lists = Arrays.asList(
				ListStrings.wrap(Arrays.asList("one", "two")),
				Arrays.asList(3,4),
				Arrays.asList("five","six"),
				ListStrings.wrap(Arrays.asList("seven", "eight")));
		var strings = new ListString[2];
		int i = 0;
		for(List<?> l : lists) {
			if(l instanceof ListString) {
				strings[i++] = (ListString)l;
			}
		}
		Assertions.assertTrue(Arrays.toString(strings).equals("[[one, two], [seven, eight]]"));
	}

	@Test
	void assert_Inherited_Contains_Only_ListString_Type() {
		var lists = Arrays.asList(
				new ArrayListString(Arrays.asList("one", "two")),
				Arrays.asList(3,4),
				Arrays.asList("five","six"),
				new ArrayListString(Arrays.asList("seven", "eight")));
		var strings = new ListString[2];
		int i = 0;
		for(List<?> l : lists) {
			if(l instanceof ListString) {
				strings[i++] = (ListString)l;
			}
		}
		Assertions.assertTrue(Arrays.toString(strings).equals("[[one, two], [seven, eight]]"));
	}
	
	//delegation and inheritance are not interchangeable.
	//delegation has better security properties and
	//creates a view of the underlying list,
	//while inheritance creates a new list.
	//delegation can take most lists from collections and sub-lists.
	//inheritance can only be applied to public implementations that can besub-classed.
	//plus, security can be improved by declaring specialized signatures similar to delegation.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void assert_Specialization_Differences() {
		var original = new ArrayList<String>();
		var delegated = ListStrings.wrap(original);
		var inherited = new ArrayListString(original);
		
		delegated.add("one");
		inherited.add("two");
		
		Assertions.assertThrows(ClassCastException.class, () -> ((List)delegated).add(1));
		Assertions.assertAll(() -> ((List)inherited).add(4));//unchecked, no class cast error!
		Assertions.assertTrue(original.toString().equals("[one]"));
		Assertions.assertTrue(delegated.toString().equals("[one]"));
		Assertions.assertTrue(inherited.toString().equals("[two, 4]"));
	}
}
