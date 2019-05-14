package generics.designPatterns.strategy.simple;

class TaxStrategyTest {

	@Test
	void test() {
		Person person = new Person(10_000_000);
		Trust nonProfit = new Trust(10_000_000, true);
		Trust profit = new Trust(10_000_000, false);	
		
		TaxStrategy<Person> defaultStrategy = new DefaultTaxStrategy<>();
		TaxStrategy<Person> dodgingStrategy = new DodgingTaxStrategy<>();
		
		//Generics allows us to specialize a given tax strategy to a given type of tax payer;
		//Allows the compiler to detect when applied to the wrong type.
		TaxStrategy<Trust> trustStrategy = new TrustTaxStrategy();
		
		assertTrue(defaultStrategy.computeTax(person) == 4_000_000);
		assertTrue(dodgingStrategy.computeTax(person) == 0);

		assertTrue(trustStrategy.computeTax(nonProfit) == 0);
		assertTrue(trustStrategy.computeTax(profit) == 4_000_000);

		//compile-time error.
		//Without generics, the computeTax method of TrustStrategy would have to accept a TaxPayer,
		//cast to trust, and deal with run-time errors rather than compile-time.
		//trustStrategy.computeTax(person);
		
	}

}
