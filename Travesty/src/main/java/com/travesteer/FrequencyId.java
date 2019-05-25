/*
 * Created May 2, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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
import javax.persistence.Embeddable;

/**
 * The composite key for the <code>FirstOrderFrequency</code> object.
 * 
 * @author travesteer
 *
 */
@Embeddable
public class FrequencyId implements Serializable
{
	@Column(name = "first")
	String first;

	@Column(name = "second")
	String second;

	/**
	 * Default constructor.
	 */
	public FrequencyId()
	{
		this.first = new String();
		this.second = new String();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param oldId
	 *            The <code>FrequencyId</code> to be copied.
	 */
	public FrequencyId(FrequencyId oldId)
	{
		this.first = oldId.first;
		this.second = oldId.second;
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param first
	 *            The first token of the key.
	 * @param second
	 *            The second token of the key.
	 */
	public FrequencyId(String first, String second)
	{
		this.first = first;
		this.second = second;
	}

	/**
	 * Tests two instances of <code>FrequencyId</code> for equality.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FrequencyId))
			return false;
		FrequencyId other = (FrequencyId) obj;
		if (first == null)
		{
			if (other.first != null)
				return false;
		}
		else if (!first.equals(other.first))
			return false;
		if (second == null)
		{
			if (other.second != null)
				return false;
		}
		else if (!second.equals(other.second))
			return false;
		return true;
	}

	/**
	 * Returns the first database key part.
	 * 
	 * @return The first token.
	 */
	public String getFirst()
	{
		return first;
	}

	/**
	 * Returns the second database key part.
	 * 
	 * @return The second token.
	 */
	public String getSecond()
	{
		return second;
	}

	/**
	 * Generates a haxh code for this <code>FrequencyId</code>.
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	/**
	 * Sets the value of the first database key part.
	 * 
	 * @param first
	 *            Sets first.
	 */
	public void setFirst(String first)
	{
		this.first = first;
	}

	/**
	 * Sets the value of the second database key part.
	 * 
	 * @param second
	 *            Sets second.
	 */
	public void setSecond(String second)
	{
		this.second = second;
	}

	/**
	 * Returns a reader-friendly representation of the <code>FrequencyId</code>
	 * instance.
	 */
	@Override
	public String toString()
	{
		return "FrequencyId [first=" + first + ", second=" + second + "]";
	}
}
