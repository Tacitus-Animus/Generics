package generics.reification;

import java.util.Arrays;
import java.util.Collection;

import fp.exceptionTests.TestHelper;
import generics.reflection.GenericReflection;

/**
 * 
 * The Principle of Truth in Advertising:The reified type of an array must be a sub-type of the erasure of it's static type.
 * Requires that the run-time type of an array is properly reified.
 * We can use reflection to get information at run,
 * granted we give the type via an argument, not as type parameter.
 */ 
public class Wrong {

	//Erasure drops type parameters on Collection and List. 
	//Type variable T is replaced with Object.
	//Casting Object[] on reified type Object[].
	//Principle is obeyed within this method, where erasure of T is Object,
	//but not within the test method, where T has been bound to String...
	public static <T> T[] toArray(Collection<T> c) {
		@SuppressWarnings("unchecked") //unchecked casts are DANGEROUS.
		T[] a = (T[])/*Casts by erasure can fail here, but won't see it explicitly*/ new Object[c.size()];
		int i = 0; for(T x : c) a[i++] = x;
		return a;
	}
	
	//Principle is not obeyed since T is bound to String,
	//but the reified type  of the array is still Object.
	//Basically, it was okay within the method to cast, since it knows it's reified type within the method.
	//But now it's reified type coming out of the method is Object[], So casting to String[] from Object[] won't work.
	@Test
	public void testClassCastError() {
		var strings = Arrays.asList("one", "two");
		
		TestHelper.assertThrows(ClassCastException.class, () -> {
			//java.base/[Ljava.lang.Object; cannot be cast to java.base/[Ljava.lang.String;
			//In english: array of Object can't be cast to array of strings.
			@SuppressWarnings("unused")
			String[] a = toArray(strings);
		})/*.printStackTrace()*/;
		
		//Just to Clarify!
		//String[] CANNOT be cast to Object[].
		//Stated in the principle String[] isn't sub-type of Object[].
		//String[] is sub-type Object!
		TestHelper.assertThrows(ClassCastException.class, () -> {
			Object[] o = new Object[] {"one"};
			String[] s = (String[]) o;
		});
		
		System.out.println(GenericReflection.reflect(new String[1].getClass(), new Object[1].getClass()));
	}
}
