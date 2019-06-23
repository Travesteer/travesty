/*
 * Created Jun 7, 2019 travesteer.  Copyright (c) 2019, Trump Travesty (travesteer@travesteer.com).
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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Writes multiple tweets to a text file.
 * 
 * @author travesteer.
 *
 */
public class TweetSecondToFile
{

	/**
	 * Standard entry point.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Writer writer = null;
		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("TravestySecond");
			EntityManager em = emf.createEntityManager();
			FrequencySecondPersistence frequencySecondPersistence = new FrequencySecondPersistence(
					em);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					"multipleTweets.txt"), "utf-8"));
			for (int i = 0; i < 2000; i++)
			{
				ParsedTweet result = GenerateSecondTweet.generateSecondOrder(
						frequencySecondPersistence);
				String tweet = result.toString() + "\n";
				writer.write(tweet);
			} 
		}
		finally
		{
			writer.close();
		}

	}
}
