package generics.CompareAndBounds.covariantOverriding;

import java.lang.reflect.Method;

public class TestCovariantOverriding {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		
		var point = new Point(7,7);
		//no casting needed
		var copy = point.clone();
		
		//covariant overriding using the bridge.
		for(Method m : Point.class.getMethods()) {
			if(m.getName().equals("clone")) System.out.println(m.toGenericString());
		}
		
		
		
	}
	
}
