/*
 * Created May 30, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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
 * The frequency with which first and second tokens are followed by a third
 * token. The <code>SecondOrderFrequency</code> objects are stored in a SQL
 * Server databse.
 * 
 * The tokens are parsed from a tweet, and they consist of any string of
 * characters separated by whitespace.
 * 
 * @author travesteer
 *
 */
@Entity(name = "SecondOrder")
@Table(name = "SecondOrder")
public class SecondOrderFrequency implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * The composite key to the record in the <code>SecondOrderFrequency</code>
	 * table.
	 */
	@EmbeddedId
	FrequencyIdSecond id;

	/**
	 * The number of times the third token has followed the first two tokens.
	 */
	@Column(name = "count")
	int count;

	/**
	 * Default constructor.
	 * 
	 * @throws Exception
	 */
	public SecondOrderFrequency() throws Exception
	{
		this.id = new FrequencyIdSecond();
		setCount(0);
	}

	/**
	 * Copy constructor.
	 * 
	 * @param oldFrequency
	 *            The <code>SecondOrderFrequency</code> to copy.
	 * @throws Exception
	 */
	public SecondOrderFrequency(SecondOrderFrequency oldFrequency) throws Exception
	{
		this.setId(oldFrequency.getId());
		this.setCount(oldFrequency.count);
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param id
	 *            The instance's <code>FrequencyIdSecond</code>.
	 * @param count
	 *            The count of times the id's first and second tokens are
	 *            followed by the id's third token.
	 * @throws Exception
	 */
	public SecondOrderFrequency(FrequencyIdSecond id, int count) throws Exception
	{
		this.id = id;
		setCount(count);
	}

	/**
	 * Constructor with <code>String</code> arguments.
	 * 
	 * @param first
	 *            The record's first token.
	 * @param second
	 *            The record's second token.
	 * @param third
	 *            The record's third token.
	 * @param count
	 *            The count of times the third token has folled the first two.
	 * @throws Exception
	 */
	public SecondOrderFrequency(String first, String second, String third, int count) throws Exception
	{
		this.id = new FrequencyIdSecond(first, second, third);
		setCount(count);
	}

	
//	@Override
//	public boolean equals(Object obj)
//	{
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (!(obj instanceof SecondOrderFrequency))
//			return false;
//		SecondOrderFrequency other = (SecondOrderFrequency) obj;
//		return count == other.count && Objects.equals(id, other.id);
//	}

	/**
	 * @return Returns count.
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * @return Returns id.
	 */
	public FrequencyIdSecond getId()
	{
		return id;
	}

//	@Override
//	public int hashCode()
//	{
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + Objects.hash(count, id);
//		return result;
//	}

	
	
	/**
	 * @param count Sets count.
	 */
	public void setCount(int count)
	{
		this.count = count;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SecondOrderFrequency))
			return false;
		SecondOrderFrequency other = (SecondOrderFrequency) obj;
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
	 * @param id Sets id.
	 */
	public void setId(FrequencyIdSecond id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "SecondOrderFrequency [id=" + id + ", count=" + count + "]";
	}

}
