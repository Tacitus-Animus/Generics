package generics.declarations;

public class Cell<T> {

	private final int id;
	private final T value;
	private static int count = 0;
	
	private static synchronized int nextId() { 
		return count++; 
		}
	
	public Cell(T value){
		this.value = value;
		id = nextId();
	}
	
	public T getValue() {
		return value;
	}
	
	public int getId() {
		return id;
	}
	
	public static synchronized int getCount() {
		return count;
	}
}
