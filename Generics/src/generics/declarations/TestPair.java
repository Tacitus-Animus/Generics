package generics.declarations;

public class TestPair {

	public static void main(String[] args) {

		//works; types are inferred with Diamond syntax <>
		var pair = new Pair<>("one", 2);
		
		//throws a warning for raw type Pair without Diamond syntax <>
		@SuppressWarnings({ "rawtypes", "unchecked" })
		var other = new Pair("one", 2);

	}

}
