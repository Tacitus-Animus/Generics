package generics.designPatterns.strategy.advanced;

public class DodgingTaxStrategy<P extends TaxPayer<P>> implements TaxStrategy<P> {

	@Override
	public long computeTax(P p) {
		return 0;
	}

}
