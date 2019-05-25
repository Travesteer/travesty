/*
 * Created May 3, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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
 * Supports CRUD persistence operations on <code>FirstOrderFrequency</code>
 * instances. Adapted from Mike Keith and Merrick Schincariol, "Pro JPA 2" (2nd
 * ed., SPringer: New York, 2013) code download.
 * 
 * Note: the database must have a case-sensitive collation property.
 * 
 * @author travesteer
 *
 */
public class FrequencyPersistence
{
	protected static EntityManager em;

	public FrequencyPersistence(EntityManager em)
	{
		FrequencyPersistence.em = em;
	}

	/**
	 * Creates a persistent <code>FirstOrderFrequency</code> instance in the
	 * database.
	 * 
	 * @param first
	 *            The preceeding token.
	 * @param second
	 *            The following token.
	 * @param count
	 *            The count of times the second has followed the first.
	 * @return Returns the frequency just persisted.
	 * @throws Exception
	 *             Throws a <code>RollbackException</code> on attempt to persist
	 *             a duplicate key.
	 */
	public FirstOrderFrequency createFrequency(String first, String second, int count)
			throws Exception
	{
		FirstOrderFrequency createdFrequency = new FirstOrderFrequency(first, second, count);
		em.getTransaction().begin();
		em.persist(createdFrequency);
		em.getTransaction().commit();
		return createdFrequency;
	}

	/**
	 * Returns all <code>FirstOrderFrequency</code> instances in the database as
	 * a <code>Collection</code>.
	 * 
	 * @return The collection of instances.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<FirstOrderFrequency> findAllFrequencies() throws Exception
	{
		Query query = em.createQuery("SELECT e FROM FirstOrder e");
		return query.getResultList();
	}

	/**
	 * Returns all <code>FirstOrderFrequency</code> instances with the given
	 * <code>first</code> in the database as a <code>Collection</code>.
	 * 
	 * @return THe collection of instances.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<FirstOrderFrequency> findFrequenciesByFirst(String first) throws Exception
	{
		String queryString = "SELECT e FROM FirstOrder e where e.id.first = '" + first + "'";
		Query query = em.createQuery(queryString);
		return query.getResultList();
	}

	/**
	 * Finds a persistent <code>FirstOrderFrequency</code> instance in the
	 * database by <code>FrequencyId</code>. There can be at most one result
	 * returned, because the <code>FrequencyId</code> is the database key.
	 * 
	 * @param wantedId
	 *            The <code>FrequencyId</code> of the instance to find.
	 * @return The instance found or <code>null</code> if none found.
	 * @throws Exception
	 */
	public FirstOrderFrequency findFrequency(FrequencyId wantedId) throws Exception
	{
		String sqlString = String.format(
				"select r from FirstOrder r where r.id.first = '%s' and r.id.second = '%s'",
				wantedId.first, wantedId.second);
		Query query;
		try
		{
			query = em.createQuery(sqlString);
			return (FirstOrderFrequency) query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}

	/**
	 * Finds a persistent <code>FirstOrderFrequency</code> instance in the
	 * database by first and second token values. Can be at most only one result
	 * because the first and second token values are the database composite key.
	 * 
	 * @param first
	 *            The first token value.
	 * @param second
	 *            The second toek value.
	 * @return The <code>FirstOrderFrequency</code> with the the desired first
	 *         and second tokens, or <code>null</code> if the instance isn't in
	 *         the database.
	 * @throws Exception
	 */
	public FirstOrderFrequency findFrequency(String first, String second) throws Exception
	{
		FrequencyId wantedId = new FrequencyId(first, second);
		return findFrequency(wantedId);
	}

	/**
	 * Increments the count of a persistent <code>FirstOrderFrequency</code>
	 * instance by removing and re-creating. If the instance isn't already in
	 * the database, it's created with a count of 1.
	 * 
	 * @param frequency
	 *            The instance to increment.
	 * @return The instance after update.
	 * @throws Exception
	 */
	public FirstOrderFrequency incrementFrequency(FirstOrderFrequency frequency) throws Exception
	{
		FirstOrderFrequency resultFrequency = em.find(FirstOrderFrequency.class, frequency.id);
		if (resultFrequency == null)
		{
			// Tried to increment a frequency not in the database.
			return createFrequency(frequency.id.first, frequency.id.second, 1);
		}
		else
		{
			removeFrequency(frequency.id);
			return createFrequency(frequency.id.first, frequency.id.second, ++frequency.count);
		}
	}

	/**
	 * Increments the count of a persistent <code>FirstOrderFrequency</code>
	 * instance by removing and re-creating. If the instance isn't already in
	 * the database, it's created with a count of 1.
	 * 
	 * @param freqId
	 *            The id of the frequency to be updated instance to be
	 *            incremented.
	 * @return The instance after increment.
	 * @throws Exception
	 */
	public FirstOrderFrequency incrementFrequencyById(FrequencyId freqId) throws Exception
	{
		FirstOrderFrequency foundFrequency = em.find(FirstOrderFrequency.class, freqId);
		try
		{
			if (foundFrequency == null)
			{
				// Tried to increment a frequency not in the database.
				return createFrequency(freqId.first, freqId.second, 1);
			}
			else
			{
				removeFrequency(freqId);
				return createFrequency(freqId.first, freqId.second, ++foundFrequency.count);
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
	 * Removes a persistent <code>FirstOrderFrequency</code> instance from the
	 * database by <code>FrequencyId</code>.
	 * 
	 * @param freqId
	 *            The <code>FrequencyId</code> of the instance to remove.
	 * @throws Exception
	 */
	public void removeFrequency(FrequencyId freqId) throws Exception
	{
		FirstOrderFrequency foundFrequency = findFrequency(freqId);
		if (foundFrequency != null)
		{
			em.getTransaction().begin();
			em.remove(foundFrequency);
			em.getTransaction().commit();
		}
	}

	/**
	 * Removes a persistent <code>FirstOrderFrequency</code> instance from the
	 * database by first and second token values.
	 * 
	 * @param first
	 *            The value of the first token of the instance to remove.
	 * @param second
	 *            The value of the second token of the instance to remove.
	 * @throws Exception
	 */
	public void removeFrequency(String first, String second) throws Exception
	{
		FrequencyId newId = new FrequencyId(first, second);
		removeFrequency(newId);
	}

	/**
	 * Updates a persistent <code>FirstOrderFrequency</code> instance by
	 * removing and re-creating. Attempts to update a frequency not already in
	 * the database quietly create the desired instance.
	 * 
	 * @param newFrequency
	 *            The updated instance to replace the old.
	 * @return The updated persistent instance.
	 * @throws Exception
	 */
	public FirstOrderFrequency updateFrequency(FirstOrderFrequency newFrequency) throws Exception
	{
		FirstOrderFrequency foundFrequency = em.find(FirstOrderFrequency.class, newFrequency.id);
		if (foundFrequency == null)
		{
			// Tried to update a frequency not in the database, so create.
			return createFrequency(newFrequency.id.first, newFrequency.id.second,
					newFrequency.count);
		}
		else
		{
			removeFrequency(newFrequency.id);
			return createFrequency(newFrequency.id.first, newFrequency.id.second,
					newFrequency.count);
		}
	}
}
