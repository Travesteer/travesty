package com.travesteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrequencyIdTest
{
	/**
	 * The test first token.
	 */
	public static final String TEST_FIRST = "Joe";

	/**
	 * The test second token.
	 */
	public static final String TEST_SECOND = "Blow";

	/**
	 * The test instance.
	 */
	public static FrequencyId testId = null;

	/**
	 * Initialize the test instance.
	 * 
	 * @return the test instance.
	 * @throws StandardException
	 */
	public static final FrequencyId setTestFrequencyId()
	{
		return new FrequencyId(TEST_FIRST, TEST_SECOND);
	}

	/**
	 * Sets up a fresh test instance for every test.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		testId = setTestFrequencyId();
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
		FrequencyId newId = new FrequencyId();
		assertFalse(newId.equals(testId));
		newId = new FrequencyId(testId);
		assertTrue(newId.first.equals(testId.first));
		assertTrue(newId.second.equals(testId.second));
	}

	/**
	 * Tests the default constructor.
	 */
	@Test
	final void testFrequencyId()
	{
		testId = null;
		assertNull(testId);
		testId = setTestFrequencyId();
		assertNotNull(testId);
		assertTrue(testId.first.equals(TEST_FIRST));
		assertTrue(testId.second.equals(TEST_SECOND));
	}

	/**
	 * Tests the copy constructor.
	 */
	@Test
	final void testFrequencyIdFrequencyId()
	{
		FrequencyId newId = new FrequencyId();
		assertFalse(newId.equals(testId));
		newId = new FrequencyId(testId);
		assertTrue(newId.equals(testId));
	}

	/**
	 * Tests the constructor with arguments.
	 */
	@Test
	final void testFrequencyIdStringString()
	{
		testId = null;
		assertNull(testId);
		testId = new FrequencyId(TEST_FIRST, TEST_SECOND);
		assertNotNull(testId);
		assertTrue(testId.first.equals(TEST_FIRST));
		assertTrue(testId.second.equals(TEST_SECOND));
	}

	/**
	 * Tests the object's <code>getFirst()</code> function.
	 */
	@Test
	final void testGetFirst()
	{
		assertTrue(testId.first.equals(TEST_FIRST));
	}

	/**
	 * Tests the object's <code>getSecond()</code> function.
	 */
	@Test
	final void testGetSecond()
	{
		assertTrue(testId.second.equals(TEST_SECOND));
	}

	/**
	 * Tests the object's <code>setFirst()</code> function.
	 */
	@Test
	final void testSetFirst()
	{
		String newFirst = "Donald";
		assertTrue(testId.first.equals(TEST_FIRST));
		testId.setFirst(newFirst);
		assertTrue(testId.first.equals(newFirst));
	}

	@Test
	final void testSetSecond()
	{
		String newSecond = "Trump";
		assertTrue(testId.second.equals(TEST_SECOND));
		testId.setSecond(newSecond);
		assertTrue(testId.second.equals(newSecond));
	}

	/**
	 * Tests the object's <code>toString()</code> function.
	 */
	@Test
	final void testToString()
	{
		String testString = "FrequencyId [first=Joe, second=Blow]";
		assertTrue(testId.toString().equals(testString));
	}

}
