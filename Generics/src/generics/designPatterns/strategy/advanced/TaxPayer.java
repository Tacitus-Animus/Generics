package generics.designPatterns.strategy.advanced;

/**
 * Recursive Type used like Comparable<E extends Comparable<E>>.
 * Why? Just like Comparing types that need to be compared to other comparable types of the same type like String,
 * but can't compare to comparable of Integer.
 * Or Enum<E extends Enum<E>>. Where Enums of the same type of Enum can be compared. Like an enum of Seasons can only compared to other Seasons.
 * Here we might create a TaxPayer of type Person.
 * @author Alex Paul
 *
 * @param <P> The type of TaxPayer 
 */
abstract class TaxPayer<P extends TaxPayer<P>> {
	
	private long income;
	private TaxStrategy<P> strategy; 
	
	public TaxPayer(long income, TaxStrategy<P> strategy) {
		this.income = income;
		this.strategy = strategy;
	}
	
	protected abstract P getThis();
	
		public long computeTax() {
			return strategy.computeTax(getThis());
		}

		public long getIncome() {
			return income;
		}
}
