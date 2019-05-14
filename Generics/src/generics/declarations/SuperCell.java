package generics.declarations;

import java.util.ArrayList;
import java.util.List;

public class SuperCell<T> extends Cell<T>{

	//illegal; List is static, but Type T isn't.
	//Only works since Type is Object, not T;
	private static List<Object> values = new ArrayList<>();

	public SuperCell(T value) {
		super(value);
		add(value);
	}

	private void add(Object value) {
		values.add(value);
	}

	public static List<Object> getValues() {
		return values;
	}
	
}
