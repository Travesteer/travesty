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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 * The composite key for the <code>SecondOrderFrequency</code> object.
 * 
 * @author travesteer
 *
 */
@Embeddable
@Table(name = "SecondOrder")
public class FrequencyIdSecond
{
	@Column(name = "first")
	String first;

	@Column(name = "second")
	String second;

	@Column(name = "third")
	String third;

	/**
	 * Default constructor.
	 */
	public FrequencyIdSecond()
	{
		this.first = new String();
		this.second = new String();
		this.third = new String();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param oldId
	 *            The <code>FrequencyIdSecond</code> to be copied.
	 */
	public FrequencyIdSecond(FrequencyIdSecond oldId)
	{
		this.first = oldId.first;
		this.second = oldId.second;
		this.third = oldId.third;
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param first
	 *            The first token of the key.
	 * @param second
	 *            The second token of the key.
	 * @param third
	 *            The third token of the key.
	 */
	public FrequencyIdSecond(String first, String second, String third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((third == null) ? 0 : third.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FrequencyIdSecond))
			return false;
		FrequencyIdSecond other = (FrequencyIdSecond) obj;
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
		if (third == null)
		{
			if (other.third != null)
				return false;
		}
		else if (!third.equals(other.third))
			return false;
		return true;
	}

	/**
	 * @return Returns first.
	 */
	public String getFirst()
	{
		return first;
	}

	/**
	 * @param first Sets first.
	 */
	public void setFirst(String first)
	{
		this.first = first;
	}

	/**
	 * @return Returns second.
	 */
	public String getSecond()
	{
		return second;
	}

	/**
	 * @param second Sets second.
	 */
	public void setSecond(String second)
	{
		this.second = second;
	}

	/**
	 * @return Returns third.
	 */
	public String getThird()
	{
		return third;
	}

	/**
	 * @param third
	 *            Sets third.
	 */
	public void setThird(String third)
	{
		this.third = third;
	}

	@Override
	public String toString()
	{
		return "FrequencyIdSecond [first=" + first + ", second=" + second + ", third=" + third
				+ "]";
	}

}
