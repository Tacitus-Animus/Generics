package generics.designPatterns.strategy.simple;

class TrustTaxStrategy extends DefaultTaxStrategy<Trust> {

	@Override
	public long computeTax(Trust trust) {
		return trust.isNonProfit() ? 0 : super.computeTax(trust);
	}
	
}
