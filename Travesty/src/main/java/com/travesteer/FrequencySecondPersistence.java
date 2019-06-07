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

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Supports CRUD persistence operations on <code>SecondOrderFrequency</code>
 * instances. Adapted from Mike Keith and Merrick Schincariol, "Pro JPA 2" (2nd
 * ed., SPringer: New York, 2013) code download.
 * 
 * Note: the database must have a case-sensitive collation property.
 * 
 * @author travesteer
 *
 */
public class FrequencySecondPersistence
{
	protected static EntityManager em;

	public FrequencySecondPersistence(EntityManager em)
	{
		FrequencySecondPersistence.em = em;
	}

	/**
	 * Creates a persistent <code>SecondOrderFrequency</code> instance in the
	 * database.
	 * 
	 * @param first
	 *            The first token.
	 * @param second
	 *            The second token.
	 * @param third
	 *            The third token.
	 * @param count
	 *            The count of times the third has followed the first two.
	 * @return Returns the frequency just persisted.
	 * @throws Exception
	 *             Throws a <code>RollbackException</code> on attempt to persist
	 *             a duplicate key.
	 */
	public SecondOrderFrequency createFrequency(String first, String second, String third,
			int count) throws Exception
	{
		SecondOrderFrequency createdFrequency = new SecondOrderFrequency(first, second, third,
				count);
		em.getTransaction().begin();
		em.persist(createdFrequency);
		em.getTransaction().commit();
		return createdFrequency;
	}

	/**
	 * Returns all <code>SecondOrderFrequency</code> instances in the database
	 * as a <code>Collection</code>.
	 * 
	 * @return The collection of instances.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<SecondOrderFrequency> findAllFrequencies() throws Exception
	{
		Query query = em.createQuery("SELECT e FROM SecondOrder e");
		return query.getResultList();
	}

	/**
	 * Returns all <code>SecondOrderFrequency</code> instances with the given
	 * <code>first</code> and <code>second</code> in the database as a
	 * <code>Collection</code>.
	 * 
	 * @return THe collection of instances.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<SecondOrderFrequency> findFrequenciesByFirstAndSecond(String first,
			String second) throws Exception
	{
		String queryString = "SELECT e FROM SecondOrder e where e.id.first = '" + first
				+ "' and e.id.second = '" + second + "'";
		Query query = em.createQuery(queryString);
		return query.getResultList();
	}

	/**
	 * Finds a persistent <code>SecondOrderFrequency</code> instance in the
	 * database by <code>FrequencyIdSecond</code>. There can be at most one
	 * result returned, because the <code>FrequencyIdSecond</code> is the
	 * database key.
	 * 
	 * @param wantedId
	 *            The <code>FrequencyIdSecond</code> of the instance to find.
	 * @return The instance found or <code>null</code> if none found.
	 * @throws Exception
	 */
	public SecondOrderFrequency findFrequency(FrequencyIdSecond wantedId) throws Exception
	{
		String sqlString = String.format(
				"select r from SecondOrder r where r.id.first = '%s' and r.id.second = '%s' and r.id.third = '%s'",
				wantedId.first, wantedId.second, wantedId.third);
		Query query;
		try
		{
			query = em.createQuery(sqlString);
			return (SecondOrderFrequency) query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	/**
	 * Finds a persistent <code>SecondOrderFrequency</code> instance in the
	 * database by first. second, and third token values. Can be at most only
	 * one result because the first and second token values are the database
	 * composite key.
	 * 
	 * @param first
	 *            The first token value.
	 * @param second
	 *            The second toek value.
	 * @param third
	 *            The third toek value.
	 * @return The <code>SecondOrderFrequency</code> with the the desired three
	 *         tokens, or <code>null</code> if the instance isn't in the
	 *         database.
	 * @throws Exception
	 */
	public SecondOrderFrequency findFrequency(String first, String second, String third)
			throws Exception
	{
		FrequencyIdSecond wantedId = new FrequencyIdSecond(first, second, third);
		return findFrequency(wantedId);
	}

	/**
	 * Increments the count of a persistent <code>SecondOrderFrequency</code>
	 * instance by removing and re-creating. If the instance isn't already in
	 * the database, it's created with a count of 1.
	 * 
	 * @param frequency
	 *            The instance to increment.
	 * @return The instance after update.
	 * @throws Exception
	 */
	public SecondOrderFrequency incrementFrequency(SecondOrderFrequency frequency) throws Exception
	{
		SecondOrderFrequency resultFrequency = em.find(SecondOrderFrequency.class, frequency.id);
		if (resultFrequency == null)
		{
			// Tried to increment a frequency not in the database.
			return createFrequency(frequency.id.first, frequency.id.second, frequency.id.third, 1);
		}
		else
		{
			removeFrequency(frequency.id);
			return createFrequency(frequency.id.first, frequency.id.second, frequency.id.third,
					++frequency.count);
		}
	}

	/**
	 * Increments the count of a persistent <code>SecondOrderFrequency</code>
	 * instance by removing and re-creating. If the instance isn't already in
	 * the database, it's created with a count of 1.
	 * 
	 * @param freqId
	 *            The id of the frequency instance to be incremented.
	 * @return The instance after increment.
	 * @throws Exception
	 */
	public SecondOrderFrequency incrementFrequencyById(FrequencyIdSecond freqId) throws Exception
	{
		SecondOrderFrequency foundFrequency = em.find(SecondOrderFrequency.class, freqId);
		try
		{
			if (foundFrequency == null)
			{
				// Tried to increment a frequency not in the database.
				return createFrequency(freqId.first, freqId.second, freqId.third, 1);
			}
			else
			{
				removeFrequency(freqId);
				return createFrequency(freqId.first, freqId.second, freqId.third,
						++foundFrequency.count);
			}
		}
		catch (Exception e)
		{
			System.out.println("********************* incrementing frequency = " + foundFrequency);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Removes a persistent <code>SecondOrderFrequency</code> instance from the
	 * database by <code>FrequencyIdSecond</code>.
	 * 
	 * @param freqId
	 *            The <code>FrequencyIdSecond</code> of the instance to remove.
	 * @throws Exception
	 */
	public void removeFrequency(FrequencyIdSecond freqId) throws Exception
	{
		SecondOrderFrequency foundFrequency = findFrequency(freqId);
		if (foundFrequency != null)
		{
			em.getTransaction().begin();
			em.remove(foundFrequency);
			em.getTransaction().commit();
		}
	}

	/**
	 * Removes a persistent <code>SecondOrderFrequency</code> instance from the
	 * database by first, second, and third token values.
	 * 
	 * @param first
	 *            The value of the first token of the instance to remove.
	 * @param second
	 *            The value of the second token of the instance to remove.
	 * @param third
	 *            The value of the third token of the instance to remove.
	 * @throws Exception
	 */
	public void removeFrequency(String first, String second, String third) throws Exception
	{
		FrequencyIdSecond newId = new FrequencyIdSecond(first, second, third);
		removeFrequency(newId);
	}

	/**
	 * Updates a persistent <code>SecondOrderFrequency</code> instance by
	 * removing and re-creating. Attempts to update a frequency not already in
	 * the database quietly create the desired instance.
	 * 
	 * @param newFrequency
	 *            The updated instance to replace the old.
	 * @return The updated persistent instance.
	 * @throws Exception
	 */
	public SecondOrderFrequency updateFrequency(SecondOrderFrequency newFrequency) throws Exception
	{
		SecondOrderFrequency foundFrequency = em.find(SecondOrderFrequency.class, newFrequency.id);
		if (foundFrequency == null)
		{
			// Tried to update a frequency not in the database, so create.
			return createFrequency(newFrequency.id.first, newFrequency.id.second,
					newFrequency.id.third, newFrequency.count);
		}
		else
		{
			removeFrequency(newFrequency.id);
			return createFrequency(newFrequency.id.first, newFrequency.id.second,
					newFrequency.id.third, newFrequency.count);
		}
	}
}
