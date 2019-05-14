package generics.designPatterns.subjectObserver;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.stream.IntStream;

public class CModel extends Observable<CModel, CView, Currency> {

	private final ArrayList<CView> observers = new ArrayList<>();
	private final EnumMap<Currency,Double> rates = new EnumMap<>(Currency.class);

	private long value = 0; //cents, euro cents, or pence;
	private Currency currency = Currency.DOLLAR;
	
	/**
	 * Since rates change, it wouldn't make much sense to put them in the Enum class.
	 * Instead, map values with an EnumMap, which might be faster than a HashMap.
	 * @param initialRates The rates to be mapped to Constant Currencies.
	 */
	public void init(double... initialRates) {
		IntStream.range(0, initialRates.length).forEach(i -> setRate(Currency.values()[i], initialRates[i]));
	}

	/**
	 * {@link #notifyObservers(Currency)} must be used. Due to the fragile process of initializing {@link #CModel()#init(double...)},
	 * The RateView and ValueView will only update if the current currency is the same as theirs. Else a <code>NullPointerException</code> will be thrown,
	 * due to the fact the rates will only have the DOLLAR enum set. 
	 * All Views will be added as observers. So, The views with EURO, POUND and so on will try to get values and rates for initialized rates/enums.
	 * @param currency the type of rate.
	 * @param rate to be re-mapped.
	 */
	public void setRate(Currency currency, double rate) {
		rates.put(currency, rate);
		notifyObservers(currency);
	}
	
	public void setValue(Currency currency, long value) {
		this.currency = currency;
		this.value = value;
		notifyObservers();
	}
	
	public double getRate(Currency currency) {
		return rates.get(currency);
	}
	
	public long getValue(Currency currency) {
		if(currency == this.currency) {
			return this.value;
		}else return Math.round(value * getRate(currency) / getRate(this.currency)); 
	}
	
	@Override
	public void notifyObservers() {
		observers.forEach(observer -> observer.update(this, null));
	}
	
	@Override
	public void notifyObservers(Currency metaData) {
		observers.forEach(observer -> observer.update(this, metaData));
	}
	
	@Override
	public void addObserver(CView observer) {
		observers.add(observer);
	}
	
}
