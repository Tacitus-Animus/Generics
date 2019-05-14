package generics.designPatterns.subjectObserver;

/**
 * 
 * @author Alexander Paul
 *
 * @param <S> The Subject to be observed by the Observer.
 * @param <O> The Observer to be notified when the Subject has changed.
 * @param <A> I don no... meta-
 * data? Cake? Creme brule? Chateau de la mente? whom's'v'd'st cares...
 */
public class Observable<S extends Observable<S, O, A>, 
						O extends Observer<S, O, A>, 
						A> 
{
	public void addObserver(O observer) { throw new StubException(); }
	public int countObservers() { throw new StubException(); }
	public void deleteObserver(O observer) { throw new StubException(); }
	public boolean hasChanged() { throw new StubException(); }
	public void notifyObservers() { throw new StubException(); }
	public void notifyObservers(A metaData) { throw new StubException(); }
}
