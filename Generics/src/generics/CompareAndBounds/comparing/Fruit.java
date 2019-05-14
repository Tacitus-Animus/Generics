package generics.CompareAndBounds.comparing;

//permits comparison of Apples and Oranges.
public abstract class Fruit implements Comparable<Fruit> {

	String name;
	int size;
	
	protected Fruit(String name, int size){
		this.name = name;
		this.size = size;
	}
	
	@Override
	public int compareTo(Fruit that) {
		return this.size < that.size ? -1 
			 : this.size == that.size ? 0 
									  : 1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Fruit) {
			Fruit that = (Fruit) o;
			return this.name.equals(that.name) && this.size == that.size;
		}else return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode() * 29 + size;
	}
	
	@Override
	public String toString() {
		return name + " Size: " + size;
	}

}
