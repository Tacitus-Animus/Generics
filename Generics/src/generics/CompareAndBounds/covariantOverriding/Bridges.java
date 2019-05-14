package generics.CompareAndBounds.covariantOverriding;

import java.lang.reflect.Method;

public class Bridges {

	public static void main(String[] args) {

		//This bridge is what supports covariant method overriding.
		//Since 1.4, a method could only override a method with the same name and return/parameter type. Even if the return/parameter type is a sub-type.
		//Since 1.5, now compareTo has a bridge between the Object and its sub-class Integer. 
		//Casting isn't needed anymore.
		for(Method method : Integer.class.getMethods()) {
			if(method.getName().equals("compareTo")) System.out.println(method.toGenericString());
		}
	}

}
