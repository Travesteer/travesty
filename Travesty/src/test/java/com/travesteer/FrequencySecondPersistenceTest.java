/*
 * Created Jun 1, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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
 * Neither the name of travesteer nor the name of travesteer.com may be used to
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
 * @author travestaeer
 *
 */
class FrequencySecondPersistenceTest
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
	public static FrequencySecondPersistence TEST_FREQ_PERSISTENCE = null;

	/**
	 * The test <code>SecondOrderFrequency</code> instance.
	 */
	public static SecondOrderFrequency TEST_FREQ = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		TEST_EMF = Persistence.createEntityManagerFactory("TravestySecond");
		TEST_EM = TEST_EMF.createEntityManager();
		TEST_FREQ_PERSISTENCE = new FrequencySecondPersistence(TEST_EM);
		TEST_FREQ = SecondOrderFrequencyTest.setTestFreq();
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
	 * {@link com.travesteer.FrequencySecondPersistence#FrequencySecondPersistence(javax.persistence.EntityManager)}.
	 */
	@Test
	final void testFrequencySecondPersistence()
	{
		TEST_FREQ_PERSISTENCE = null;
		assertNull(TEST_FREQ_PERSISTENCE);
		TEST_FREQ_PERSISTENCE = new FrequencySecondPersistence(TEST_EM);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#createFrequency(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testCreateFrequency() throws Exception
	{
		SecondOrderFrequency newFreq = null;
		newFreq = TEST_FREQ_PERSISTENCE.createFrequency(FrequencyIdSecondTest.TEST_FIRST,
				FrequencyIdSecondTest.TEST_SECOND, FrequencyIdSecondTest.TEST_THIRD,
				SecondOrderFrequencyTest.TEST_COUNT);
		assert (newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#findFrequencyByFirstAndSecond()}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFindFrequencyByFirstAndSecond() throws Exception
	{
		SecondOrderFrequency newFreq = null;
		newFreq = TEST_FREQ_PERSISTENCE.createFrequency(FrequencyIdSecondTest.TEST_FIRST,
				FrequencyIdSecondTest.TEST_SECOND, FrequencyIdSecondTest.TEST_THIRD,
				SecondOrderFrequencyTest.TEST_COUNT);
		Collection<SecondOrderFrequency> foundFreqs = TEST_FREQ_PERSISTENCE
				.findFrequenciesByFirstAndSecond("Donald", "John");
		for (SecondOrderFrequency e : foundFreqs)
			System.out.println("Found frequency: " + e);
		foundFreqs = TEST_FREQ_PERSISTENCE.findFrequenciesByFirstAndSecond("Joe", "Blow");
		for (SecondOrderFrequency e : foundFreqs)
			System.out.println("Found frequency: " + e);
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#findAllFrequencies()}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFindAllFrequencies() throws Exception
	{
		SecondOrderFrequency newFreq = null;
		newFreq = TEST_FREQ_PERSISTENCE.createFrequency(FrequencyIdSecondTest.TEST_FIRST,
				FrequencyIdSecondTest.TEST_SECOND, FrequencyIdSecondTest.TEST_THIRD,
				SecondOrderFrequencyTest.TEST_COUNT);
		Collection<SecondOrderFrequency> allFreqs = TEST_FREQ_PERSISTENCE.findAllFrequencies();
		for (SecondOrderFrequency e : allFreqs)
			System.out.println("Found frequency: " + e);
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#findFrequency(com.travesteer.FrequencyIdSecond)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFindFrequencyFrequencyIdSecond() throws Exception
	{
		SecondOrderFrequency newFreq = null;
		assertNull(newFreq);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.getId());
		assertNull(newFreq);
		TEST_FREQ_PERSISTENCE.createFrequency(TEST_FREQ.id.first, TEST_FREQ.id.second,
				TEST_FREQ.id.third, TEST_FREQ.getCount());
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.getId());
		assertNotNull(newFreq);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#findFrequency(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testFindFrequencyStringStringString() throws Exception
	{
		SecondOrderFrequency newFreq = null;
		assertNull(newFreq);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.id.first, TEST_FREQ.id.second,
				TEST_FREQ.id.third);
		assertNull(newFreq);
		TEST_FREQ_PERSISTENCE.createFrequency(TEST_FREQ.id.first, TEST_FREQ.id.second,
				TEST_FREQ.id.third, TEST_FREQ.getCount());
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(TEST_FREQ.id.first, TEST_FREQ.id.second,
				TEST_FREQ.id.third);
		assertNotNull(newFreq);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#incrementFrequency(com.travesteer.SecondOrderFrequency)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testIncrementFrequency() throws Exception
	{
		SecondOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		assertTrue(newFreq.count == 2);
		TEST_FREQ_PERSISTENCE.removeFrequency(TEST_FREQ.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#incrementFrequency(com.travesteer.SecondOrderFrequency)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testIncrementFrequencyById() throws Exception
	{
		SecondOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		newFreq = TEST_FREQ_PERSISTENCE.incrementFrequencyById(TEST_FREQ.id);
		assertTrue(newFreq.count == 2);
		TEST_FREQ_PERSISTENCE.removeFrequency(TEST_FREQ.getId());
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#removeFrequency(com.travesteer.FrequencyIdSecond)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testRemoveFrequencyFrequencyIdSecond() throws Exception
	{
		SecondOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.createFrequency(
				FrequencyIdSecondTest.TEST_FIRST, FrequencyIdSecondTest.TEST_SECOND,
				FrequencyIdSecondTest.TEST_THIRD, SecondOrderFrequencyTest.TEST_COUNT);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(newFreq.id);
		assertNull(newFreq);
	}

	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#removeFrequency(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testRemoveFrequencyStringStringString() throws Exception
	{
		SecondOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.createFrequency(
				FrequencyIdSecondTest.TEST_FIRST, FrequencyIdSecondTest.TEST_SECOND,
				FrequencyIdSecondTest.TEST_THIRD, SecondOrderFrequencyTest.TEST_COUNT);
		assertTrue(newFreq.equals(TEST_FREQ));
		TEST_FREQ_PERSISTENCE.removeFrequency(newFreq.id.first, newFreq.id.second, newFreq.id.third);
		newFreq = TEST_FREQ_PERSISTENCE.findFrequency(newFreq.id);
		assertNull(newFreq);
	}
	
	/**
	 * Test method for
	 * {@link com.travesteer.FrequencySecondPersistence#updateFrequency(com.travesteer.SecondOrderFrequency)}.
	 * @throws Exception 
	 */
	@Test
	final void testUpdateFrequency() throws Exception
	{
		SecondOrderFrequency newFreq = TEST_FREQ_PERSISTENCE.incrementFrequency(TEST_FREQ);
		SecondOrderFrequency updatedFreq = TEST_FREQ_PERSISTENCE.updateFrequency(newFreq);
		assertTrue(newFreq.equals(updatedFreq));
		newFreq.setCount(123);
		assertFalse(newFreq.equals(updatedFreq));
		updatedFreq = TEST_FREQ_PERSISTENCE.updateFrequency(newFreq);
		assertTrue(newFreq.equals(updatedFreq));
		TEST_FREQ_PERSISTENCE.removeFrequency(TEST_FREQ.getId());
	}

}
