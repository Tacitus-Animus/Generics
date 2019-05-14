package generics.designPatterns.subjectObserver;

/**
 * Marker for Model View Converter.
 * Unfortunately Subject-Observer has been depreciated since Java 9
 * @see java.util.Observable
 * @see java.util.Observer
 * @author Alexander Paul
 */
public interface CView extends Observer<CModel, CView, Currency> {}
