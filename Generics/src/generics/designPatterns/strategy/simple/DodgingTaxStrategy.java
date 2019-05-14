package generics.designPatterns.strategy.simple;

class DodgingTaxStrategy<P extends TaxPayer> implements TaxStrategy<P> {

	@Override
	public long computeTax(P p) {
		return 0;
	}

}
