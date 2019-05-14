package generics.designPatterns.strategy.advanced;
  
final class Person extends TaxPayer<Person> {
	
	public Person(long income, TaxStrategy<Person> strategy) {
		super(income, strategy);
 	}

	@Override
	protected Person getThis() {
		return this;
	}

}
