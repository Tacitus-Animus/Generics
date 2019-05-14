package generics.designPatterns.interpreter;
/**
 * Used in conjunction with Exp
 * @author Alex Paul
 *
 * @param <A> left type	
 * @param <B> right type
 * @see Exp
 */
public class Pair<A, B> {

	private final A left;
	private final B right;
	
	public Pair(A left, B right) {
		this.left = left;
		this.right = right;
	}

	public A getLeft() {
		return left;
	}

	public B getRight() {
		return right;
	}
	
}
