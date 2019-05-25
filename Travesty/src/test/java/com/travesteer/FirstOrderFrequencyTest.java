/*
 * Created May 4, 2019 travesteer.  Copyright (c) 2019, Trump Travesy (travesteer@riseup.net).
 *  All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * This code may be used only for Good, not for Evil.
 * 
 * Neither the name of travesteaer nor the name of trumptravesty.com may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT  * NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES  * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS  * INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * To learn more about open source licenses, please visit: http://opensource.org/index.php
 */
package com.travesteer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Exercises all class functionality.
 * 
 * @author Geo
 *
 */
class FirstOrderFrequencyTest
{
	/**
	 * The test <code>FrequencyId</code>.
	 */
	public static FrequencyId TEST_ID = FrequencyIdTest.setTestFrequencyId();

	/**
	 * The test <code>count</code>.
	 */
	public static final int TEST_COUNT = 1;

	/**
	 * The test instance.
	 */
	public static FirstOrderFrequency testFreq = null;

	/**
	 * Initialize the test instance.
	 * 
	 * @return the test instance.
	 * @throws Exception
	 * @throws StandardException
	 */
	public static final FirstOrderFrequency setTestFreq() throws Exception
	{
		TEST_ID = FrequencyIdTest.setTestFrequencyId();
		return new FirstOrderFrequency(TEST_ID, TEST_COUNT);
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
	 * {@link com.travesteer.FirstOrderFrequency#equals(java.lang.Object)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testEqualsObject() throws Exception
	{
		FirstOrderFrequency newFreq = new FirstOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new FirstOrderFrequency(testFreq);
		assertTrue(newFreq.id.equals(testFreq.id));
		assertTrue(newFreq.count == testFreq.count);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FirstOrderFrequency#FirstOrderFrequency()}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFirstOrderFrequency() throws Exception
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
	 * {@link com.travesteer.FirstOrderFrequency#FirstOrderFrequency(com.travesteer.FirstOrderFrequency)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFirstOrderFrequencyFirstOrderFrequency() throws Exception
	{
		FirstOrderFrequency newFreq = new FirstOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new FirstOrderFrequency(testFreq);
		assertTrue(newFreq.equals(testFreq));
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FirstOrderFrequency#FirstOrderFrequency(com.travesteer.FrequencyId, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFirstOrderFrequencyFrequencyIdInt() throws Exception
	{
		FirstOrderFrequency newFreq = new FirstOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new FirstOrderFrequency(TEST_ID, TEST_COUNT);
		assertTrue(newFreq.equals(testFreq));
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FirstOrderFrequency#FirstOrderFrequency(java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFirstOrderFrequencyStringStringInt() throws Exception
	{
		FirstOrderFrequency newFreq = new FirstOrderFrequency();
		assertFalse(newFreq.equals(testFreq));
		newFreq = new FirstOrderFrequency(FrequencyIdTest.TEST_FIRST, FrequencyIdTest.TEST_SECOND,
				TEST_COUNT);
		assertTrue(newFreq.equals(testFreq));
	}

	/**
	 * Test method for {@link com.travesteer.FirstOrderFrequency#getCount()}.
	 */
	@Test
	final void testGetCount()
	{
		assertTrue(testFreq.getCount() == TEST_COUNT);
	}

	/**
	 * Test method for {@link com.travesteer.FirstOrderFrequency#getFirst()}.
	 */
	@Test
	final void testGetFirst()
	{
		assertTrue(testFreq.getId().getFirst().equals(FrequencyIdTest.TEST_FIRST));
	}

	/**
	 * Test method for {@link com.travesteer.FirstOrderFrequency#getSecond()}.
	 */
	@Test
	final void testGetSecond()
	{
		assertTrue(testFreq.getId().getSecond().equals(FrequencyIdTest.TEST_SECOND));
	}

	/**
	 * Test method for {@link com.travesteer.FirstOrderFrequency#setCount(int)}.
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
	 * {@link com.travesteer.FirstOrderFrequency#setFirst(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testSetFirst() throws Exception
	{
		String newFirst = "Donald";
		assertTrue(testFreq.getFirst().equals(FrequencyIdTest.TEST_FIRST));
		testFreq.setFirst(newFirst);
		assertTrue(testFreq.getFirst().equals(newFirst));
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FirstOrderFrequency#setSecond(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testSetSecond() throws Exception
	{
		String testSecond = "Trump";
		assertTrue(testFreq.getSecond().equals(FrequencyIdTest.TEST_SECOND));
		testFreq.setSecond(testSecond);
		assertTrue(testFreq.getSecond().equals(testSecond));
	}

	/**
	 * Test method for {@link com.travesteer.FirstOrderFrequency#toString()}.
	 */
	@Test
	final void testToString()
	{
		String testString = "FirstOrderFrequency [first=Joe, second=Blow, count=1]";
		assertTrue(testFreq.toString().equals(testString));
	}

}
