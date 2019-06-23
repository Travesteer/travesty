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

import java.util.Iterator;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Generates second-order Markov chain travesties of Trump's tweets based on his
 * twitter archive. Generated tweet goes to the console.
 * 
 * @author travesteer
 *
 */
public class GenerateSecondTweet implements Constants
{
	/**
	 * Standard entry point.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TravestySecond");
		EntityManager em = emf.createEntityManager();
		FrequencySecondPersistence frequencySecondPersistence = new FrequencySecondPersistence(em);
		ParsedTweet result = generateSecondOrder(frequencySecondPersistence);
		System.out.println(result);
	}

	/**
	 * Actually does all the generation.
	 * 
	 * @param frequencySecondPersistence
	 * @return The generated <code>ParsedTweet</code>.
	 * @throws Exception
	 */
	public static ParsedTweet generateSecondOrder(
			FrequencySecondPersistence frequencySecondPersistence) throws Exception
	{
		String seed1 = BEGIN_TWEET;
		String seed2 = BEGIN_TWEET;
		Vector<String> tokens = new Vector<String>(AVERAGE_TOKEN_COUNT);
		Vector<SecondOrderFrequency> frequencyDistribution = null;
		tokens.add(seed1);
		tokens.add(seed2);
		SecondOrderFrequency currentFreq = null;
		int random = 0;
		while (!seed2.equals(END_TWEET))
		{
			frequencyDistribution = (Vector<SecondOrderFrequency>) frequencySecondPersistence
					.findFrequenciesByFirstAndSecond(seed1, seed2);
			Iterator<SecondOrderFrequency> iterator = frequencyDistribution.iterator();
			int total = 0;
			while (iterator.hasNext())
			{
				currentFreq = (SecondOrderFrequency) iterator.next();
				total += currentFreq.count;
			}
			iterator = frequencyDistribution.iterator();
			random = GenerateTweet.randomInt(1, total);
			int runningTotal = 0;
			while (iterator.hasNext() && runningTotal < random)
			{
				currentFreq = (SecondOrderFrequency) iterator.next();
				runningTotal += currentFreq.count;
			}
			seed1 = currentFreq.id.second;
			seed2 = currentFreq.id.third;
			tokens.add(seed2);
		}
		Iterator<String> iterator = tokens.iterator();
		Vector<String> unescapedTokens = new Vector<String>(tokens.size());
		while (iterator.hasNext())
			unescapedTokens.add(GenerateTweet.unEscape(iterator.next()));
		return new ParsedTweet(unescapedTokens);
	}
}
