package generics.declarations;

import java.util.Arrays;

public class ExampleErasure {

	public static void main(String[] args) {

		//Generics are compiled by erasure at runtime.
		//Both ints and string are implemented by a single class, List.
		//Issue arise when there are static members of a generic class.
		var ints = Arrays.asList(1,2);
		var strings = Arrays.asList("one", "two");
		System.out.println(ints.getClass() == strings.getClass());
		
		var integer = new Cell<Integer>(1);
		var string = new Cell<String>("One"); 
		//Even if the Types are different, the static members are still shared within the same generic class.
		System.out.println(Cell.getCount() == 2);
		
		//Non-Static call to a static method.
		System.out.println(Cell.getCount() == 2);
		new Cell<Integer>(1);
		System.out.println(Cell.getCount());
		
		//Compile-time error:
		//System.out.println(Cell<String>.getCount());
		
		var superString = new SuperCell<String>("one");
		var superInt = new SuperCell<Integer>(1);
		//values are returned as Objects, not type T. 
		System.out.println(SuperCell.getValues().toString().equals("[one,1]"));
		//interesting fact: Generic sub-class SuperCell still invokes like a Cell, increasing the count.
		System.out.println(Cell.getCount());
	}

}
