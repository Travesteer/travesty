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

/**
 * Manifest constants available to this projectd.
 * 
 * @author travesteer
 *
 */
public interface Constants
{
	/**
	 * Assume a 240-character tweet of five-letter words.
	 */
	public static final int AVERAGE_TOKEN_COUNT = 56;

	/**
	 * The dummy token for beginning a tweet.
	 */
	public static final String BEGIN_TWEET = "BEGIN_TWEET";

	/**
	 * The regex for matching a MM-DD-YYYY string, from
	 * <code>https://tinyurl.com/y6ganp3m</code>.
	 */
	public static final String DATE_PATTERN = "/(0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])[- \\/.]((?:19|20)\\d\\d)/";

	/**
	 * The dummy token for ending a tweet.
	 */
	public static final String END_TWEET = "END_TWEET";

	/**
	 * The way an ampersand appears in the Trump twitter archive.
	 */
	public static final String HTML_AMPERSAND = "&amp;";

	/**
	 * Default input file name.
	 */
	public static final String INPUT_FILE_NAME = "twitterArchive.txt";

	/**
	 * The maximum value for an ASCII character.
	 */
	public static final int MAX_ASCII_VALUE = 127;

	/**
	 * A string containing all the characters that need to be escaped. This is
	 * because they're meaningful in Java strings and/or SQL query strings.
	 */
	public static final String NEEDS_ESCAPE = "\"\\*?!-#%_[]^";
}
