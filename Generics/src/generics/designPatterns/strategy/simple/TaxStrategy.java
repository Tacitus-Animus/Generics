package generics.designPatterns.strategy.simple;

interface TaxStrategy<P extends TaxPayer> {
	public long computeTax(P p);
}
