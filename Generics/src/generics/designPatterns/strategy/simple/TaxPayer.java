package generics.designPatterns.strategy.simple;

abstract class TaxPayer {

	public long income;
	
	public TaxPayer(long income) {
		this.income = income;
	}

	public long getIncome() {
		return income;
	}

}
