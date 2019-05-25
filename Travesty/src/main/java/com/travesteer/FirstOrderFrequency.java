/*
 * Created May 2, 2019 by travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The frequency with which a first token is followed by a second token. The
 * <code>FirstOrderFrequency</code> objects are stored in a SQL Server databse.
 * 
 * The tokens are parsed from a tweet, and they consist of any string of
 * characters separated by a space.
 * 
 * @author travesteer.
 *
 */
@Entity(name = "FirstOrder")
@Table(name = "FirstOrder")
public class FirstOrderFrequency implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * The composite key to the record in the <code>FirstOrderFrequency</code>
	 * table.
	 */
	@EmbeddedId
	FrequencyId id;

	/**
	 * The number of times the second token has followed the first token.
	 */
	@Column(name = "count")
	int count;

	/**
	 * Default constructor.
	 * 
	 * @throws Exception
	 */
	public FirstOrderFrequency() throws Exception
	{
		this.id = new FrequencyId();
		setCount(0);
	}

	/**
	 * Copy constructor.
	 * 
	 * @param oldFrequency
	 *            The <code>FirstOrderFrequency</code> instance to copy.
	 * @throws Exception
	 */
	FirstOrderFrequency(FirstOrderFrequency oldFrequency) throws Exception
	{
		this.setId(oldFrequency.getId());
		this.setCount(oldFrequency.count);
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param id
	 *            The instance's <code>FrequencyId</code>.
	 * @param count
	 *            The count of times the id's first token is followed by the
	 *            id's second token.
	 * @throws Exception
	 */
	public FirstOrderFrequency(FrequencyId id, int count) throws Exception
	{
		this.id = id;
		setCount(count);
	}

	/**
	 * Constructor with string arguments.
	 * 
	 * @param first
	 *            The record's first token.
	 * @param second
	 *            The record's second token.
	 * @param count
	 *            The count of times the second token has followed the first
	 *            token.
	 * @throws Exception
	 */
	public FirstOrderFrequency(String first, String second, int count) throws Exception
	{
		this.id = new FrequencyId(first, second);
		setCount(count);
	}

	/**
	 * Compares objects for equality.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FirstOrderFrequency))
			return false;
		FirstOrderFrequency other = (FirstOrderFrequency) obj;
		if (count != other.count)
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Returns the count of times the second token was found following the first
	 * token.
	 * 
	 * @return Returns count.
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * Returns the first token.
	 * 
	 * @return Returns first.
	 */
	public String getFirst()
	{
		return id.getFirst();
	}

	/**
	 * Return the instance's <code>FrequencyId</code>.
	 * 
	 * @return Returns id.
	 */
	public FrequencyId getId()
	{
		return id;
	}

	/**
	 * Returns the second token.
	 * 
	 * @return Returns second.
	 */
	public String getSecond()
	{
		return id.getSecond();
	}

	/**
	 * Generates a hash code for the instance.
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Sets the count of times the second token was found following the first
	 * token.
	 * 
	 * @param count
	 *            Sets count.
	 * @throws Exception
	 */
	public void setCount(int count) throws Exception
	{
		if (count < 0)
			throw new Exception("Count may not be less than 0.");
		else
			this.count = count;
	}

	/**
	 * Sets the first token.
	 * 
	 * @param first
	 *            Sets first.
	 * @throws Exception
	 */
	public void setFirst(String first) throws Exception
	{
		if (first == null)
			throw new Exception("First token may not be null.");
		else
		{
			this.id.setFirst(first);
		}
	}

	/**
	 * Set's the instance's <code>FrequencyId</code>.
	 * 
	 * @param id
	 *            Sets id.
	 */
	public void setId(FrequencyId id)
	{
		this.id = id;
	}

	/**
	 * Sets the second token.
	 * 
	 * @param second
	 *            Sets second.
	 * @throws Exception
	 */
	public void setSecond(String second) throws Exception
	{
		if (second == null)
			throw new Exception("Second token may not be null");
		else
			this.id.second = second;
	}

	/**
	 * Returns a reader-friendly rendering of the instance.
	 */
	@Override
	public String toString()
	{
		return "FirstOrderFrequency [first=" + getFirst() + ", second=" + getSecond() + ", count="
				+ count + "]";
	}
}
