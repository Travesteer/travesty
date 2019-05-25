/*
 * Created May 5, 2019 travesteer.  Copyright (c) 2019, Trump Travesy (travesteer@riseup.net).
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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Exercises all object functionality.
 * 
 * @author Geo
 *
 */
class FrequencyPersistenceTest
{
	/**
	 * The <code>EntityManager</code> for the test routines.
	 */
	public static EntityManager TEST_EM = null;

	/**
	 * The <code>EntityManagerFactory</code> for the test routines.
	 */
	public static EntityManagerFactory TEST_EMF = null;

	/**
	 * The <code>ResidentPersistence</code> instance for the test routines.
	 */
	public static FrequencyPersistence TEST_FREQ_PERSISTENCE = null;

	/**
	 * The test <code>FirstOrderFrequency</code> instance.
	 */
	public static FirstOrderFrequency TEST_FREQ = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		TEST_EMF = Persistence.createEntityManagerFactory("Travesty");
		TEST_EM = TEST_EMF.createEntityManager();
		TEST_FREQ_PERSISTENCE = new FrequencyPersistence(TEST_EM);
		TEST_FREQ = FirstOrderFrequencyTest.setTestFreq();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		TEST_FREQ = null;
		TEST_FREQ_PERSISTENCE = null;
		TEST_EM.close();
		TEST_EMF.close();
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#FrequencyPersistence(javax.persistence.EntityManager)}.
	 */
	@Test
	final void testFrequencyPersistence()
	{
		TEST_FREQ_PERSISTENCE = null;
		assertNull(TEST_FREQ_PERSISTENCE);
		TEST_FREQ_PERSISTENCE = new FrequencyPersistence(TEST_EM);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#createFrequency(java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testCreateFrequency() throws Exception
	{
		FirstOrderFrequency newFreq = null;
		newFreq = TEST_FREQ_PERSISTENCE.createFrequency(FrequencyIdTest.TEST_FIRST,
				FrequencyIdTest.TEST_SECOND, FirstOrderFrequencyTest.TEST_COUNT);
		assert (newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#findFrequencyByFirst()}.
	 * @throws Exception 
	 */
	@Test
	final void testFindFrequencyByFirst() throws Exception
	{
		Collection<FirstOrderFrequency> foundFreqs = TEST_FREQ_PERSISTENCE.findFrequenciesByFirst(
				"Donald");
		for (FirstOrderFrequency e : foundFreqs)
			System.out.println("Found frequency: " + e);
		foundFreqs = TEST_FREQ_PERSISTENCE.findFrequenciesByFirst("Joe");
		for (FirstOrderFrequency e : foundFreqs)
			System.out.println("Found frequency: " + e);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#findAllFrequencies()}.
	 * @throws Exception 
	 */
	@Test
	final void testFindAllFrequencies() throws Exception
	{
		Collection<FirstOrderFrequency> allFreqs = TEST_FREQ_PERSISTENCE.findAllFrequencies();
		for (FirstOrderFrequency e : allFreqs)
			System.out.println("Found frequency: " + e);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#findFrequency(com.travesteer.FrequencyId)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFindFrequencyFrequencyId() throws Exception
	{
		FirstOrderFrequency newFreq = null;
		assertNull(newFreq);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.getId());
		assertNull(newFreq);
		TEST_FREQ_PERSISTENCE.createFrequency(TEST_FREQ.getFirst(), TEST_FREQ.getSecond(), TEST_FREQ
				.getCount());
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.getId());
		assertNotNull(newFreq);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#findFrequency(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testFindFrequencyStringString() throws Exception
	{
		FirstOrderFrequency newFreq = null;
		assertNull(newFreq);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.getFirst(), TEST_FREQ.getSecond());
		assertNull(newFreq);
		TEST_FREQ_PERSISTENCE.createFrequency(TEST_FREQ.getFirst(), TEST_FREQ.getSecond(), TEST_FREQ
				.getCount());
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.getFirst(), TEST_FREQ.getSecond());
		assertNotNull(newFreq);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#incrementFrequency(com.travesteer.FirstOrderFrequency)}.
	 * @throws Exception 
	 */
	@Test
	final void testIncrementFrequency() throws Exception
	{
		FirstOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		assertTrue(newFreq.count == 2);
		TEST_FREQ_PERSISTENCE.removeFrequency(TEST_FREQ.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#incrementFrequency(com.travesteer.FirstOrderFrequency)}.
	 * @throws Exception 
	 */
	@Test
	final void testIncrementFrequencyById() throws Exception
	{
		FirstOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		newFreq = TEST_FREQ_PERSISTENCE.incrementFrequencyById(TEST_FREQ.id);
		assertTrue(newFreq.count == 2);
		TEST_FREQ_PERSISTENCE.removeFrequency(TEST_FREQ.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#removeFrequency(com.travesteer.FrequencyId)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testRemoveFrequencyFrequencyId() throws Exception
	{
		FirstOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.createFrequency(
				FrequencyIdTest.TEST_FIRST, FrequencyIdTest.TEST_SECOND,
				FirstOrderFrequencyTest.TEST_COUNT);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(newFreq.id);
		assertNull(newFreq);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#removeFrequency(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	final void testRemoveFrequencyStringString() throws Exception
	{
		FirstOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.createFrequency(
				FrequencyIdTest.TEST_FIRST, FrequencyIdTest.TEST_SECOND,
				FirstOrderFrequencyTest.TEST_COUNT);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id.first, newFreq.id.second);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(newFreq.id);
		assertNull(newFreq);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencyPersistence#updateFrequency(com.travesteer.FirstOrderFrequency)}.
	 * @throws Exception 
	 */
	@Test
	final void testUpdateFrequency() throws Exception
	{
		FirstOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		FirstOrderFrequency updatedFreq = TEST_FREQ_PERSISTENCE.updateFrequency(newFreq);
		assertTrue(newFreq.equals(updatedFreq));
		newFreq.setCount(123);
		assertFalse(newFreq.equals(updatedFreq));
		updatedFreq = TEST_FREQ_PERSISTENCE.updateFrequency(newFreq);
		assertTrue(newFreq.equals(updatedFreq));
		TEST_FREQ_PERSISTENCE.removeFrequency(TEST_FREQ.getId());
	}

}
