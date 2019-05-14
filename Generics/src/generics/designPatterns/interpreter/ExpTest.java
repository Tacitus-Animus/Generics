package generics.designPatterns.interpreter;

import static generics.designPatterns.interpreter.Exp.*;

class ExpTest {

	@Test
	void test() {
		//only getting sum value of the left evaluation of the pair of literal values of 3 and 4.
		//The pair element consists of a left plus expression(3+4) and a right literal expression(5). 
		Exp<Integer> e = left(pair(plus(lit(3), 
										lit(4)),
								   lit(5)));
		assertTrue(e.eval() == 7);
		
	}

}
