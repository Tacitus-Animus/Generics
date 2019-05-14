package generics.designPatterns.subjectObserver;

/**
 * {@link java.beans.}
 * @author Alexander Paul
 *
 * @param <S> The Subject used to pass data to Observer.
 * @param <O> The Observer
 * @param <A> The metaData used for stateful/conditional updates.
 */
public interface Observer<S extends Observable<S, O, A>, 
						  O extends Observer<S, O, A>, 
						  A> {
	public void update(S subject, A metaData); 
}
