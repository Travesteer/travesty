/*
 * Created May 6, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Updates the first order frequency database from a text file from
 * http://trumptwitterarchive.com/
 * 
 * @author travesteer
 *
 */
public class FreqUpdate implements Constants
{
	/**
	 * Standard entry point. Requires a single argument, to wit, the input text
	 * file of parsed tweets.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		String inFileName;
		if (args.length == 0)
			inFileName = INPUT_FILE_NAME;
		else
			inFileName = args[1];
		BufferedReader br = new BufferedReader(new FileReader(inFileName));
		int linesRead = 0;
		long startTime = System.nanoTime(), endTime;
		double duration;
		try
		{
			String lastToken = null;
			String line = null;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Travesty");
			EntityManager em = emf.createEntityManager();
			FrequencyPersistence frequencyPersistence = new FrequencyPersistence(em);
			FirstOrderFrequency currentFreq = null;
			
			startTime = System.nanoTime();
			while ((line = br.readLine()) != null)
			{
				++linesRead;
				line = prep(line);
				lastToken = BEGIN_TWEET;
				StringTokenizer tokenizer = new StringTokenizer(line, " ");
				while (tokenizer.hasMoreTokens())
				{
					String currentToken = tokenizer.nextToken();
					// Skip time stamps.
					if (currentToken.contains(",false,"))
						continue;
					// Skip MM-DD-YYYY strings.
					if (currentToken.matches(DATE_PATTERN))
						continue;
					currentToken.replaceAll(DATE_PATTERN, "");
					// Skip URL's.
					if (currentToken.contains("http"))
						continue;
					if (currentToken.contains("www."))
						continue;
					if (currentToken.equals(HTML_AMPERSAND))
						currentToken = "&";
					if (currentToken.contains(HTML_AMPERSAND))
						currentToken.replace(HTML_AMPERSAND, "&");
					currentFreq = frequencyPersistence.incrementFrequencyById(new FrequencyId(
							lastToken, currentToken));
					lastToken = currentToken;
				}
				currentFreq = new FirstOrderFrequency(lastToken, END_TWEET, 0);
				frequencyPersistence.incrementFrequency(currentFreq);
			}
		}
		catch (Exception e)
		{
			System.out.println("***************** Exception on line " + linesRead
					+ " **********************");
			e.printStackTrace();
		}
		finally
		{
			br.close();
		}
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000000l/60l/60.0;
		System.out.println(linesRead + " lines read from " + args[1] + " in " + duration + " hours");
	}

	/**
	 * Prepares a string for handling as a Java <code>String</code> and in SQL
	 * Server queries.  This preparation will have to be undone when tweets are
	 * generated.
	 * 
	 * Apostrophes don't play well with JPA and SQL Server, so they are
	 * converted to tildes.
	 * 
	 * Declared <code>static</code> so it can be tested more easily and be used
	 * as a utility.
	 * 
	 * @param input
	 *            The string be prepped.
	 * @return The prepped string.
	 */
	public static String prep(String input)
	{
		// Skip non-ascii characters.
		StringBuffer lineBuffer = new StringBuffer();
		int value;
		char c = '\0';
		for (int i = 0; i < input.length(); i++)
		{
			c = input.charAt(i);
			value = (int) c;
			if (value <= MAX_ASCII_VALUE)
				lineBuffer.append(c);
		}
		input = lineBuffer.toString();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < input.length(); i++)
		{
			c = input.charAt(i);
			String currentChar = Character.toString(c);
			// Change apostrophe to tilde.
			if (input.charAt(i) == '\'')
			{
				result.append('~');
				continue;
			}
			// Escape as necessary.
			if (NEEDS_ESCAPE.contains(currentChar))
				result.append('\\');
			result.append(c);
		}
		return result.toString();
	}
}
