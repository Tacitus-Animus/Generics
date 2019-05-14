package generics.designPatterns.interpreter;

/**
 * 
 * @author Alex Paul
 *
 * @param <T> The Type of Expression
 */
@FunctionalInterface
public interface Exp<T> {
	
	public T eval();
	
	//unary-operator; simply wraps a literal integer value into an Exp.
	static Exp<Integer> lit(final int i){
		return () -> i;
	}
	
	//binary-operator; reduce by addition of two Exps. into one Exp. 
	static Exp<Integer> plus(final Exp<Integer> e1, final Exp<Integer> e2){
		return () -> e1.eval() + e2.eval();
	}
	
	//bifunction; takes 2 Exp and returns an Exp of a Pair.
	static <A,B> Exp<Pair<A,B>> pair(final Exp<A> e1, final Exp<B> e2) {
		return () -> new Pair<A,B>(e1.eval(), e2.eval());
	}
	
	static <A,B> Exp<A> left(final Exp<Pair<A,B>> e) {
		return () -> e.eval().getLeft();
	}
	
	static <A,B> Exp<B> right(final Exp<Pair<A,B>> e){
		return () -> e.eval().getRight();
	}
}
