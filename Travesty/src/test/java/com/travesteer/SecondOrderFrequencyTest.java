package com.travesteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SecondOrderFrequencyTest
{
	/**
	 * The test <code>FrequencyIdSecond</code>.
	 */
	public static FrequencyIdSecond TEST_ID = FrequencyIdSecondTest.setTestFrequencyIdSecond();

	/**
	 * The test <code>count</code>.
	 */
	public static final int TEST_COUNT = 1;

	/**
	 * The test instance.
	 */
	public static SecondOrderFrequency testFreq = null;

	/**
	 * Initialize the test instance.
	 * 
	 * @return the test instance.
	 * @throws Exception
	 * @throws StandardException
	 */
	public static final SecondOrderFrequency setTestFreq() throws Exception
	{
		TEST_ID = FrequencyIdSecondTest.setTestFrequencyIdSecond();
		return new SecondOrderFrequency(TEST_ID, TEST_COUNT);
	}

	/**
	 * Set up a fresh test instance for each test.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		testFreq = setTestFreq();
	}

	/**
	 * Disposes of the test instance after each test.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		testFreq = null;
	}

	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#equals(java.lang.Object)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testEqualsObject() throws Exception
	{
		SecondOrderFrequency newFreq = new SecondOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new SecondOrderFrequency(testFreq);
		assertTrue(newFreq.equals(testFreq));
		assertTrue(newFreq.id.equals(testFreq.id));
		assertTrue(newFreq.count == testFreq.count);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#SecondOrderFrequency()}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testSecondOrderFrequency() throws Exception
	{
		testFreq = null;
		assertNull(testFreq);
		testFreq = setTestFreq();
		assertNotNull(testFreq);
		assertTrue(testFreq.id.equals(TEST_ID));
		assertTrue(testFreq.count == TEST_COUNT);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#SecondOrderFrequency(com.travesteer.SecondOrderFrequency)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testSecondOrderFrequencySecondOrderFrequency() throws Exception
	{
		SecondOrderFrequency newFreq = new SecondOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new SecondOrderFrequency(testFreq);
		assertTrue(newFreq.equals(testFreq));
	}

	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#SecondOrderFrequency(com.travesteer.FrequencyIdSecond, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testSecondOrderFrequencyFrequencyIdSecondInt() throws Exception
	{
		SecondOrderFrequency newFreq = new SecondOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new SecondOrderFrequency(TEST_ID, TEST_COUNT);
		assertTrue(newFreq.id.first.equals(testFreq.id.first));
		assertTrue(newFreq.id.second.equals(testFreq.id.second));
		assertTrue(newFreq.id.third.equals(testFreq.id.third));
		assertTrue(newFreq.count == testFreq.count);
		assertTrue(newFreq.equals(testFreq));
	}

	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#SecondOrderFrequency(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testSecondOrderFrequencyStringStringStringInt() throws Exception
	{
		SecondOrderFrequency newFreq = new SecondOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new SecondOrderFrequency(FrequencyIdSecondTest.TEST_FIRST,
				FrequencyIdSecondTest.TEST_SECOND, FrequencyIdSecondTest.TEST_THIRD, TEST_COUNT);
		assertTrue(newFreq.equals(testFreq));
	}
	
	/**
	 * Test method for {@link com.travesteer.SecondOrderFrequency#getCount()}.
	 */
	@Test
	final void testGetCount()
	{
		assertTrue(testFreq.getCount() == TEST_COUNT);
	}
	
	/**
	 * Test method for {@link com.travesteer.SecondOrderFrequency#getFirst()}.
	 */
	@Test
	final void testGetFirst()
	{
		assertTrue(testFreq.getId().getFirst().equals(FrequencyIdSecondTest.TEST_FIRST));
	}
	
	/**
	 * Test method for {@link com.travesteer.SecondOrderFrequency#getSecond()}.
	 */
	@Test
	final void testGetSecond()
	{
		assertTrue(testFreq.getId().getSecond().equals(FrequencyIdSecondTest.TEST_SECOND));
	}

	/**
	 * Test method for {@link com.travesteer.SecondOrderFrequency#getThird()}.
	 */
	@Test
	final void testGetThird()
	{
		assertTrue(testFreq.getId().getThird().equals(FrequencyIdSecondTest.TEST_THIRD));
	}
	
	/**
	 * Test method for {@link com.travesteer.SecondOrderFrequency#setCount(int)}.
	 * @throws Exception 
	 */
	@Test
	final void testSetCount() throws Exception
	{
		int newCount = 2;
		assertTrue(testFreq.count == TEST_COUNT);
		testFreq.setCount(newCount);
		assertTrue(testFreq.count == newCount);
	}
	
	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#setFirst(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testSetFirst() throws Exception
	{
		String newFirst = "Hilary";
		assertTrue(testFreq.id.getFirst().equals(FrequencyIdSecondTest.TEST_FIRST));
		testFreq.id.setFirst(newFirst);
		assertTrue(testFreq.id.getFirst().equals(newFirst));
	}
	
	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#setSecond(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testSetSecond() throws Exception
	{
		String testSecond = "Rodham";
		assertTrue(testFreq.id.getSecond().equals(FrequencyIdSecondTest.TEST_SECOND));
		testFreq.id.setSecond(testSecond);
		assertTrue(testFreq.id.getSecond().equals(testSecond));
	}

	/**
	 * Test method for
	 * {@link com.travesteer.SecondOrderFrequency#setThird(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testSetThird() throws Exception
	{
		String testThird = "Clinton";
		assertTrue(testFreq.id.getThird().equals(FrequencyIdSecondTest.TEST_THIRD));
		testFreq.id.setThird(testThird);
		assertTrue(testFreq.id.getThird().equals(testThird));
	}
	
	/**
	 * Test method for {@link com.travesteer.SecondOrderFrequency#toString()}.
	 */
	@Test
	final void testToString()
	{
		String testString = "SecondOrderFrequency [id=FrequencyIdSecond [first=Donald, second=John, third=Trump], count=1]";
		assertTrue(testFreq.toString().equals(testString));
	}

}
