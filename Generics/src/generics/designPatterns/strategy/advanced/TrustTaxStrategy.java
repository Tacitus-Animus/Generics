package generics.designPatterns.strategy.advanced;

public class TrustTaxStrategy<P extends Trust<P>> extends DefaultTaxStrategy<P> {

	@Override
	public long computeTax(P trust) {
		return trust.isNonProfit() ? 0 : super.computeTax(trust);
	}
}
