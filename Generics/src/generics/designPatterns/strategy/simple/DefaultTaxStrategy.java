package generics.designPatterns.strategy.simple;

class DefaultTaxStrategy<P extends TaxPayer> implements TaxStrategy<P> {

	private static final double RATE = 0.40;
	
	@Override
	public long computeTax(P payer) {
		return Math.round(payer.getIncome() * RATE);
	}

}
