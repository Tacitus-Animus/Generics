package generics.collections;

public class PhoneTask extends Task {

	private final String name;
	private final String number;
	
	public PhoneTask(String name, String number) {
		this.name = name;
		this.number = number;
	}
	@Override
	public String toString() {
		return "phone " + name;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}

}
