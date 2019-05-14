package generics.CompareAndBounds.covariantOverriding;

public class Point {
	
	int x, y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//Due to covariant overriding, this Method Overrides Object clone() method.
	//Since Point is a sub-class of Object,
	//a bridge is created to allow this.
	//No more casting.
	//before clone could only be overridden if the Return type was Object.
	@Override
	public Point clone() {
		return new Point(x,y);
	}
	
}
