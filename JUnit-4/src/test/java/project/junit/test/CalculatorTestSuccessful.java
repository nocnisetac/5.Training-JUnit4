package project.junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author nocnisetac
 * In versions prior to version 4, we needed to extend the junit.framework.TestCase class. 
 * JUnit 4 added annotations to the framework and eliminated the need to extend TestCase. 
 * We can direct both the lifecycle events and other aspects of the test execution with 
 * the provided annotations.
 * Let’s explain the annotations used in the example.
 *  @BeforeClass – Sometimes several tests need to share computationally expensive setup 
 *     (like logging into a database). While this can compromise the independence of tests, 
 *     sometimes it is a necessary optimization. Annotating a public static void no-arg 
 *     method with @BeforeClass causes it to be run ONCE before any of the test methods in 
 *     the class. The @BeforeClass methods of superclasses will be run before those the 
 *     current class.
 *	@Before – When writing tests, it is common to find that several tests need similar objects
 *	   created before they can run. Annotating a public void method with @Before causes that 
 *     method to be run before the Test method. The @Before methods of superclasses will be run 
 *     before those of the current class.
 *	@After – If you allocate external resources in a Before method you need to release them 
 *	   after the test runs. Annotating a public void method with @After causes that method to 
 *     be run after the Test method. All @After methods are guaranteed to run even if a Before
 *     or Test method throws an exception. The @After methods declared in superclasses will be 
 *     run after those of the current class.
 *	@Test – The Test annotation tells JUnit that the public void method to which it is attached 
 *     can be run as a test case. To run the method, JUnit first constructs a fresh instance of 
 *     the class then invokes the annotated method. Any exceptions thrown by the test will be 
 *     reported by JUnit as a failure. If no exceptions are thrown, the test is assumed to have 
 *     succeeded.
 *	@Ignore – Sometimes you want to temporarily disable a test or a group of tests. Methods 
 *     annotated with Test that are also annotated with @Ignore will not be executed as tests. 
 *     Also, you can annotate a class containing test methods with @Ignore and none of the 
 *     containing tests will be executed. Native JUnit 4 test runners should report the number 
 *     of ignored tests along with the number of tests that ran and the number of tests that failed.
 * To execute our tests, we can right click on the name of our class (CalculatorTestSuccessful.java), 
 * then click on Run As and finally click on JUnit Test.
 */
public class CalculatorTestSuccessful {
	private static ICalculator calculator;

	@BeforeClass
	public static void initCalculator() {
		calculator = new Calculator();
	}

	@Before
	public void beforeEachTest() {
		System.out.println("This is executed before each Test");
	}

	@After
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}

	@Test
	public void testSum() {
		int result = calculator.sum(3, 4);

		assertEquals(7, result);
	}

	@Test
	public void testDivison() {
		try {
			int result = calculator.divison(10, 2);

			assertEquals(5, result);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test(expected = Exception.class)
	public void testDivisionException() throws Exception {
		calculator.divison(10, 0);
	}

	@Ignore
	@Test
	public void testEqual() {
		boolean result = calculator.equalIntegers(20, 20);

		assertFalse(result);
	}

	@Ignore
	@Test
	public void testSubstraction() {
		int result = 10 - 3;

		assertTrue(result == 9);
	}
}