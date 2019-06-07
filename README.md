# travesty
Generates second-order Markov chain travesties of Trump tweets.

1. Get text of Trump tweets from http://trumptwitterarchive.com/.
    1. Text only.  No re-tweets.
    2. Filter out URL's, time/date stamps.
    3. Handle characters that are problematic with JPA and SQL.
    4. Filter out non-ASCII characters.
2. For each line resulting from (1) above
    1. Parse tweet text into "words." A word is any (case-sensitive) string of ASCII characters without white space.
    2. Add dummy words BEGIN_TWEET and END_TWEET.
    3. For each pair of words, store the count of times a third word follows the pair in a database.  A case-sensitve collation sequence must be used.
3. To generate tweet travesties,
    1. Start with a pair of the dummy BEGIN_TWEET words.
    2. Retrieve the frequency count for each following word from the database.
    3. Select a third word at random, based on its likelihood of following the first two.  If the dummy word END_TWEET is selected, we're done.
    4. Add the selected third word of the triad to the generated tweet.
    5. Make the last two words of the triad the first two of the next triad and continue.
