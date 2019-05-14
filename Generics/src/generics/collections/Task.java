package generics.collections;

public abstract class Task implements Comparable<Task> {

	protected Task() {}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Task) {
			return toString().equals(o.toString());
		}else return false;
	}
	
	@Override
	public int compareTo(Task t) {
		return toString().compareTo(t.toString());
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public abstract String toString();
}
