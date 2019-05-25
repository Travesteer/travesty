/*
 * Created Apr 29, 2019 by travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Encapsulates a tweet as a series of tokens (words).
 * 
 * @author travesteer
 * 
 */
public final class ParsedTweet implements Constants
{
	/**
	 * Parses a comma-delimited set of words into a <code>ParsedTweet</code>.
	 * 
	 * @param rawTweet
	 *            The space-delimited raw tweet, presumed to be read from an
	 *            input text file.
	 * @return The <code>ParsedTweet</code>
	 */
	public static ParsedTweet parseString(String rawTweet)
	{
		Vector<String> newTokens = new Vector<String>(AVERAGE_TOKEN_COUNT);
		newTokens.add(0, BEGIN_TWEET);
		StringTokenizer tokenizer = new StringTokenizer(rawTweet, " ");
		while (tokenizer.hasMoreTokens())
		{
			newTokens.add(tokenizer.nextToken());
		}
		newTokens.add(END_TWEET);
		return new ParsedTweet(newTokens);
	}

	/**
	 * Holds the "tokens" of the tweet. A token is any string of ASCII
	 * characters that doesn't contain whitespace.
	 */
	private Vector<String> tokens = new Vector<String>(AVERAGE_TOKEN_COUNT);

	/**
	 * Default constructor.
	 * 
	 * @param tokens
	 */
	public ParsedTweet()
	{
		this.tokens = new Vector<String>(AVERAGE_TOKEN_COUNT);
	}

	/**
	 * Copy constructor.
	 * 
	 * @param oldTweet
	 *            The parsed tweet to copy.
	 */
	public ParsedTweet(ParsedTweet oldTweet)
	{
		try
		{
			setTokens(oldTweet.tokens);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param tokens
	 */
	public ParsedTweet(Vector<String> tokens)
	{
		try
		{
			setTokens(tokens);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ParsedTweet))
			return false;
		ParsedTweet other = (ParsedTweet) obj;
		if (tokens == null)
		{
			if (other.tokens != null)
				return false;
		}
		else if (!tokens.equals(other.tokens))
			return false;
		return true;
	}

	/**
	 * Returns the <code>Vector</code> of words parsed from a tweet.
	 * 
	 * @return Returns tokens.
	 */
	public Vector<String> getTokens()
	{
		return tokens;
	}

	/**
	 * Sets the <code>Vector</code> of tokens in the parsed tweet. First and
	 * last tokens are validated.
	 * 
	 * @param tokens
	 *            Sets tokens.
	 * @throws Exception
	 *             Exception thrown for missing begin and end tokens.
	 */
	public void setTokens(Vector<String> tokens) throws Exception
	{
		if (!tokens.elementAt(0).equals(BEGIN_TWEET))
			throw new Exception("Missing BEGIN_TWEET");
		if (!tokens.elementAt(tokens.size() - 1).equals(END_TWEET))
			throw new Exception("Missing END_TOKEN");
		this.tokens = tokens;
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		Iterator<String> iterator = tokens.iterator();
		while (iterator.hasNext())
		{
			String currentToken = iterator.next();
			if (currentToken.equals(END_TWEET))
				break;
			if (currentToken.equals(BEGIN_TWEET))
				continue;
			buffer.append(currentToken);
			buffer.append(" ");
		}
		return buffer.toString().trim();
	}

}
