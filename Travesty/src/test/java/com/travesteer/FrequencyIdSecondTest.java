package com.travesteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrequencyIdSecondTest
{
	/**
	 * The test first token.
	 */
	public static final String TEST_FIRST = "Donald";

	/**
	 * The test second token.
	 */
	public static final String TEST_SECOND = "John";

	/**
	 * The third second token.
	 */
	public static final String TEST_THIRD = "Trump";

	/**
	 * The test instance.
	 */
	public static FrequencyIdSecond testId = null;
	
	/**
	 * Initialize the test instance.
	 * 
	 * @return the test instance.
	 * @throws StandardException
	 */
	public static final FrequencyIdSecond setTestFrequencyIdSecond()
	{
		return new FrequencyIdSecond(TEST_FIRST, TEST_SECOND, TEST_THIRD);
	}
	
	/**
	 * Sets up a fresh test instance for every test.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		testId = setTestFrequencyIdSecond();
	}

	/**
	 * Disposes of the test instance after each test.
	 * 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		testId = null;
	}

	/**
	 * Tests the object's <code>equals()</code> function.
	 */
	@Test
	final void testEqualsObject()
	{
		FrequencyIdSecond newId = new FrequencyIdSecond();
		assertFalse(newId.equals(testId));
		newId = new FrequencyIdSecond(testId);
		assertTrue(newId.first.equals(testId.first));
		assertTrue(newId.second.equals(testId.second));
	}

	/**
	 * Tests the default constructor.
	 */
	@Test
	final void testFrequencyIdSecond()
	{
		testId = null;
		assertNull(testId);
		testId = setTestFrequencyIdSecond();
		assertNotNull(testId);
		assertTrue(testId.first.equals(TEST_FIRST));
		assertTrue(testId.second.equals(TEST_SECOND));
		assertTrue(testId.third.equals(TEST_THIRD));
	}

	/**
	 * Tests the copy constructor.
	 */
	@Test
	final void testFrequencyIdSecondFrequencyIdSecond()
	{
		FrequencyIdSecond newId = new FrequencyIdSecond();
		assertFalse(newId.equals(testId));
		newId = new FrequencyIdSecond(testId);
		assertTrue(newId.equals(testId));
	}

	/**
	 * Tests the constructor with arguments.
	 */
	@Test
	final void testFrequencyIdSecondStringStringString()
	{
		testId = null;
		assertNull(testId);
		testId = new FrequencyIdSecond(TEST_FIRST, TEST_SECOND, TEST_THIRD);
		assertNotNull(testId);
		assertTrue(testId.first.equals(TEST_FIRST));
		assertTrue(testId.second.equals(TEST_SECOND));
		assertTrue(testId.third.equals(TEST_THIRD));
	}

	/**
	 * Tests the object's <code>getFirst()</code> function.
	 */
	@Test
	final void testGetFirst()
	{
		assertTrue(testId.getFirst().equals(TEST_FIRST));
	}

	/**
	 * Tests the object's <code>getSecond()</code> function.
	 */
	@Test
	final void testGetSecond()
	{
		assertTrue(testId.getSecond().equals(TEST_SECOND));
	}

	/**
	 * Tests the object's <code>getThird()</code> function.
	 */
	@Test
	final void testGetThird()
	{
		assertTrue(testId.getThird().equals(TEST_THIRD));
	}

	/**
	 * Tests the object's <code>setFirst()</code> function.
	 */
	@Test
	final void testSetFirst()
	{
		String newFirst = "Hilary";
		assertTrue(testId.first.equals(TEST_FIRST));
		testId.setFirst(newFirst);
		assertTrue(testId.first.equals(newFirst));
	}

	@Test
	final void testSetSecond()
	{
		String newSecond = "Rodham";
		assertTrue(testId.second.equals(TEST_SECOND));
		testId.setSecond(newSecond);
		assertTrue(testId.second.equals(newSecond));
	}

	@Test
	final void testSetThird()
	{
		String newThird = "Clinton";
		assertTrue(testId.third.equals(TEST_THIRD));
		testId.setThird(newThird);
		assertTrue(testId.third.equals(newThird));
	}

	/**
	 * Tests the object's <code>toString()</code> function.
	 */
	@Test
	final void testToString()
	{
		String testString = "FrequencyIdSecond [first=Donald, second=John, third=Trump]";
		assertTrue(testId.toString().equals(testString));
	}


}
