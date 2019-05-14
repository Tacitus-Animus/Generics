package generics.designPatterns.strategy.advanced;

final public class ProfitTrust extends Trust<ProfitTrust> {

	public ProfitTrust(long income, TaxStrategy<ProfitTrust> strategy) {
		super(income, strategy, false);
	}

	@Override
	protected ProfitTrust getThis() {
		return this;
	}

}
