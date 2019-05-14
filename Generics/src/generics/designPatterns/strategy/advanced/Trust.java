package generics.designPatterns.strategy.advanced;

abstract class Trust<T extends Trust<T>> extends TaxPayer<T> {

	private boolean nonProfit;
	
	Trust(long income, TaxStrategy<T> strategy, boolean nonProfit) {
		super(income, strategy);
		this.nonProfit = nonProfit;
	}

	public boolean isNonProfit() {
		return nonProfit;
	}

	
}
