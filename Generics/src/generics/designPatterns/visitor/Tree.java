package generics.designPatterns.visitor;

//Visitor is a way to simulate dynamic dispatch;
//A tree node will except a Visitor.
//A visitor will visit -perform action on- the tree node.
//But, the work is not detailed/implemented in the class itself.
//Since the details aren't implemented here, but outside the class;
//class is decoupled from the details, and relies on abstraction.
//It's one way of adhereing to SOLID principle, Dependency Inversion. 
public abstract class Tree<E> {

	//2 Type parameters: E: the type of the tree, and the other R for the Output/Result of the visitor.
	//Leaf: will simply act as a function on the element(E) to return the Result(R).
	//Branch: will recursively call on left and right tree nodes.
	public interface Visitor<E, R> {
		public R visitLeaf(E element);
		public R visitBranch(R left, R right);
	}
	
	//implemented inside the tree class itself.
	public abstract <R> R visitBy(Visitor<E, R> visitor);
	
	//A visitor will always visit a leaf.
	public static <T> Tree<T> leaf(final T element) {
		 return new Tree<T>() {
			@Override
			public <R> R visitBy(Visitor<T, R> visitor) {
				return visitor.visitLeaf(element);
			} 
		 };
	}
	
	//A visitor will always visit a branch.
	public static <T> Tree<T> branch(final Tree<T> left, final Tree<T> right) {
		return new Tree<T>() {
			@Override
			public <R> R visitBy(Visitor<T, R> visitor) {
				return visitor.visitBranch(left.visitBy(visitor), right.visitBy(visitor)); 
			}
		};
	}

}
