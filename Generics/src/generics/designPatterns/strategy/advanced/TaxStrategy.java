package generics.designPatterns.strategy.advanced;

interface TaxStrategy<P extends TaxPayer<P>> {
	public long computeTax(P p);
}
