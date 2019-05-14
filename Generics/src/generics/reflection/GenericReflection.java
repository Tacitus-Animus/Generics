

package generics.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class GenericReflection {

	//Using Constructor newInstance to avoid propagation of exceptions throw by nullary constructor, including checked exception.
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(T object) 
			throws InstantiationException, IllegalAccessException, 
				   InvocationTargetException, NoSuchMethodException 
	{
		return (T) object.getClass().getConstructor().newInstance();
	}
	
	//Guarantees to be well typed; granted Principles are obeyed.
	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getComponentType(T[] array){
		return (Class<? extends T>) array.getClass().getComponentType();
	}
	
	//raises exception for cases like T being int[].
	@SuppressWarnings("unchecked")
	public static <T> T[] newArray(Class<? extends T> clazz, int size) {
		if(clazz.isPrimitive()) throw new IllegalArgumentException("Argument can't be primitive: " + clazz);
		return (T[]) java.lang.reflect.Array.newInstance(clazz, size);
	}
	
	public static <T> T[] newArray(T[] array, int size) {
		return newArray(getComponentType(array), size);
	}
	
	public static String reflect(Class<?> c) {
		var s = new StringBuilder();
		s.append("Name: " + nul(() -> c.getName()));
		s.append("\nCanonical Name: " + nul(()-> c.getCanonicalName()));
		s.append("\nSimple Name: " + nul(() -> c.getSimpleName()));
		s.append("\nType Name: " + nul(() -> c.getTypeName()));
		s.append("\nSuper Class: " + nul(() -> c.getSuperclass().toString()));
		s.append("\nComponent Type: " + nul(() -> c.getComponentType().toString()));
		s.append("\nType Parameters: " + nul(() -> Arrays.toString(c.getTypeParameters())));
		s.append("\nInterfaces:" + nul(() -> Arrays.toString(c.getInterfaces())));
		s.append("\nClasses: " + nul(() -> Arrays.toString(c.getClasses())));
		s.append("\nDeclared Classes: " + nul(() -> Arrays.toString(c.getDeclaredClasses())));
		s.append("\nDeclaring Class: " + nul(() -> c.getDeclaringClass().toString()));
		s.append("\nEnclosing Class: " + nul(() -> c.getEnclosingClass().toString()));
		s.append("\nGeneric Super Class: " + nul(() -> c.getGenericSuperclass().toString())); 
		s.append("\nGeneric Interfaces: " + nul(() -> Arrays.toString(c.getGenericInterfaces())));
		s.append("\nAnnotated Super Class: " + nul(() -> c.getAnnotatedSuperclass().toString()));
		s.append("\nAnnotated Interfaces: " + nul(() -> Arrays.toString(c.getAnnotatedInterfaces())));
		
		return s.toString();
	}
	
	public static String reflect(Class<?>... c) {
		var sb = new StringBuilder();
		for(Class<?> x : c) {
			sb.append(reflect(x));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static String nul(Supplier<String> test) {
		try {
			return test.get();
		}catch(NullPointerException ex) {
			return "NA";
		}
	}
	
}

