package generics.designPatterns.strategy.advanced;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TaxTest {

	@Test
	void test() {
		//the recursive Type Parameters here tell the compiler that the TaxStrategy must compute the Tax
		//for a type which extends TaxPayer
		//If it only knew it was a TaxPayer, one could extend it with new methods the compiler wouldn't know about.
		//This is an example of how generics unrestricts bounds.
		Person normal = new Person(10_000_000, new DefaultTaxStrategy<>());
		Person dodger = new Person(10_000_000, new DodgingTaxStrategy<>());
		
		//Won't work since TrustTaxStrategy requires Type that extends Trust;
		//Person only extends TaxPayer.
		//Person error = new Person(10_000_000, new TrustTaxStrategy<>());
		
		//The recursive type here tells the compiler that the TaxStrategy must compute the tax
		//for a type which is restricted to the type that extends Trust.
		//Now we constrained from using just any taxpayer, but still have access to non/profit trust types.
		//If we didn't use generics, we would have to cast so the compiler knew about the isNonProfit method,
		//But would cause other implications.
		Trust<NonProfitTrust> nonProfit = new NonProfitTrust(10_000_000, new TrustTaxStrategy<>());
		Trust<ProfitTrust> profit = new ProfitTrust(10_000_000, new TrustTaxStrategy<>());
		
		//Since a Trust is still a tax payer and accepts a tax strategy, 
		//aand the strategy only requires taxpayers, can use default and dodging strategies.
		Trust<NonProfitTrust> poorNonProfitTrust = new NonProfitTrust(10_000_000, new DefaultTaxStrategy<>());
		Trust<ProfitTrust> greedyProfitTrust = new ProfitTrust(10_000_000, new DodgingTaxStrategy<>());
		
		assertTrue(normal.computeTax() == 4_000_000);
		assertTrue(dodger.computeTax() == 0);
		assertTrue(nonProfit.computeTax() == 0);
		assertTrue(profit.computeTax() == 4_000_000);
		assertTrue(poorNonProfitTrust.computeTax() == 4_000_000);
		assertTrue(greedyProfitTrust.computeTax() == 0);
	}

}
