package generics.designPatterns.strategy.advanced;

final public class NonProfitTrust extends Trust<NonProfitTrust> {

	public NonProfitTrust(long income, TaxStrategy<NonProfitTrust> strategy) {
		super(income, strategy, true);
	}

	@Override
	protected NonProfitTrust getThis() {
		return this;
	}

}
