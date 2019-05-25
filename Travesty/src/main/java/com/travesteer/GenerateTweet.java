/*
 * Created May 12, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Generates travesties of Trump's tweets as Markov chains based on his twitter
 * archive. Generated tweet goes to the console.
 * 
 * @author travesteer
 *
 */
public class GenerateTweet implements Constants
{
	/**
	 * Standard entry point.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Travesty");
		EntityManager em = emf.createEntityManager();
		FrequencyPersistence frequencyPersistence = new FrequencyPersistence(em);
		String seed = new String();
		Vector<String> tokens = new Vector<String>(AVERAGE_TOKEN_COUNT);
		Vector<FirstOrderFrequency> frequencyDistribution = null;
		if (args.length == 0)
			seed = BEGIN_TWEET;
		else
			seed = args[0];
		tokens.add(seed);
		FirstOrderFrequency currentFreq = null;
		int random = 0;
		while (!seed.equals(END_TWEET))
		{
			frequencyDistribution = (Vector<FirstOrderFrequency>) frequencyPersistence
					.findFrequenciesByFirst(seed);
			Iterator<FirstOrderFrequency> iterator = frequencyDistribution.iterator();
			int total = 0;
			while (iterator.hasNext())
			{
				currentFreq = (FirstOrderFrequency) iterator.next();
				total += currentFreq.count;
			}
			iterator = frequencyDistribution.iterator();
			random = randomInt(1, total);
			int runningTotal = 0;
			while (iterator.hasNext() && runningTotal < random)
			{
				currentFreq = (FirstOrderFrequency) iterator.next();
				runningTotal += currentFreq.count;
			}
			seed = currentFreq.getSecond();
			tokens.add(seed);
		}
		Iterator<String> iterator = tokens.iterator();
		Vector<String> unescapedTokens = new Vector<String>(tokens.size());
		while (iterator.hasNext())
			unescapedTokens.add(unEscape(iterator.next()));
		ParsedTweet generatedTweet = new ParsedTweet(unescapedTokens);
		System.out.println("Generated Tweet: " + generatedTweet);
		System.out.println("   Tweet Length: " + generatedTweet.toString().length());
	}

	/**
	 * Generates a random <code>int</code> between (and including) a minimum and
	 * a maximum. From <code>https://tinyurl.com/y5f65pwh</code>.
	 * 
	 * @param min
	 *            The low end of the range.
	 * @param max
	 *            The high end of the range.
	 * @return A random <code>int</code> between min and max.
	 */
	private static int randomInt(int min, int max)
	{
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	/**
	 * During database update, some characters or escaped or otherwise modified
	 * to make Java strings and SQL query strings play well together. This
	 * undoes that process.
	 * 
	 * @param inString
	 *            The token as stored in the database.
	 * @return The token with escaping undone.
	 */
	private static String unEscape(String inString)
	{
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < inString.length(); i++)
		{
			char c = inString.charAt(i);
			if (c == '~')
			{
				buffer.append('\'');
				continue;
			}
			if (c != '\\')
				buffer.append(c);
		}
		return buffer.toString();
	}

}
