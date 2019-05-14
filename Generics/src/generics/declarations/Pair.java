package generics.declarations;
//Type parameters are declared at the beginning of  class.
public class Pair<T, U> {

	private final T first;
	private final U second;
	
	//Type Parameters are not declared in constructor.
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return first;
	}

	public U getSecond() {
		return second;
	}
	
}
