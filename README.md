Comp 124: Homework 9
====


Mark V. Shaney
---

### Acknowledgement

Paul Cantrell inspired this assignment, and large sections of the instructions are borrowed from his original homework writeup.

### Background

Long ago, before social networking, before the web, dinosaurs roamed the earth — and one of those dinosaurs is [Usenet](http://en.wikipedia.org/wiki/Usenet). In the mid 80s, a user named Mark V. Shaney began posting to the Usenet group _net.singles_. His posts seemed on-topic, but curiously incoherent. An example:

<blockquote>When I meet someone on a professional basis, I want them to shave their
arms.  While at a conference a few weeks back, I spent an interesting evening
with a grain of salt.  I wouldn't dare take them seriously!  This brings me back
to the brash people who dare others to do so or not.</blockquote>

In [his article about Mark V. Shaney](http://www.clear.rice.edu/comp200/09fall/textriff/sci_am_paper.htm), A. K. Dewdney wrote, “The overall impression is not unlike what remains in the brain of an inattentive student after a late-night study session.”

The irritated group members quickly figured out that this author was a prankster — but it took them a little longer to realize that he was not even a human. Mark V. Shaney is a simple artificial intelligence algorithm, unleashed on the poor members of _net.singles_ by mischievous researchers at [Bell Labs](http://en.wikipedia.org/wiki/Bell_labs).

### The Algorithm

Given that its output comes eerily close to making sense, and is often even grammatically correct, the algorithm is surprisingly simple. Mark V. Shaney (hereafter “MVS”) is a pun on “markov chain.” MVS reads a body of input text, and makes a transition table that answers the following question: “Given a previous pair of words, what are the choices for the next word?”

For example, for the input string “Jack be nimble, Jack be quick”, MVS generates the following transition table:

<table>
  <tr><th align="left"></th> <td>→</td> <td>Jack</td></tr>
  <tr><th align="left">Jack</th> <td>→</td> <td>be</td></tr>
  <tr><th align="left">Jack be</th> <td>→</td> <td>nimble,</td> <td>quick</td></tr>
  <tr><th align="left">be nimble <td>→</td> <td>Jack</td></tr>
  <tr><th align="left">nimble, Jack</th> <td>→</td> <td>be</td></tr>
</table>

Note that the table includes entries for _no words preceding_ (i.e. the very beginning of the text), and _one word preceding_ (the first word of the text).

Also note that MVS is case sensitive, and considers anything that is not a space to be part of a “word” — including line breaks and punctuation. This behavior allows it to form sentences and paragraphs.

Having built this table of information, MVS  generates new text by looking up the last pair of words output, and randomly selecting the next word from the available options. If there are no options, it stops. For example, with the table above:

* The initial context is “” (i.e. nothing). In the table, the next word for that context is “Jack”, so that’s what MVS outputs.
* Now the context is “Jack”. The next word is “be”.
* Now the context is “Jack be”. For this context, MVS has _two_ choices: “nimble,” or “quick”. MVS randomly chooses one; let’s say it’s “quick”.
* Now the context is “be quick”. There is no next word in the table for this context, so MVS stops.

### Part 0: Prepare your environment

 - Fork this repo, and clone your fork.
 - Import the module into IntelliJ.
 - **Important:** Add all the jars in the `hw8/lib` as libraries (right click -> add as library).
 - Download the [database](http://poliwiki.macalester.edu/shilad/wiki-text-generator.zip) and extract it.
It contains a single `wikAPIdia` directory that contains a `db` directory.
If you are using a lab computer, **do not put the database in your H: drive** or your program will run ridiculously slowly.
 - **Important:** Place the full wikAPIdia directory underneath hw8 (unless it is on your H: drive), so you have hw8/wikAPIdia/db.

### Code structure

Begin by looking over the framework I have given you:

* `PhraseStats.java` keeps track of the count of words that follows a pair of words.
You will have one PhraseStats instance for every unique pair of words that appears in your corpus of documents.
For example, one instance of PhraseStats may track all the words following "there is".
It would know that this phrase appears 129 times in total, and "no" follows the phrase
25 times, but "interplanetary" follows the phrase only once.
* `TextGenerator.java` will remember all phrase stats and actually string together a series of words.
* `TestPhraseStats.java` ensures that your PhraseStats implementation is correct.
* `TestTextGenerator.java` ensures that your TextGenerator *trains* correctly.

### Part 1: Make PhraseStats count

Your first step is to make PhraseStats count correctly. Begin by making sure you understand exactly what it *should* do.

Once you understand the purpose of PhraseStats, add all the instance variables it will need to do its job and implement the
constructor and all methods **except** `pickRandomTo()`.

Once you have successfully finished this step, `testConstruction()` and `testSmall()` will pass in PhraseStatsTest.

### Part 2: Make PhraseStats pick randomly

Next, implement `PhraseStats.pickRandomTo()`.
The method should pick randomly from all available "to" words.
To be clear, all "to"s should be equally likely (I've listed an extra credit to improve this).
After you complete this correctly, all three tests in TestPhraseStats should pass.

Carefully consider the algorithm for randomly picking a "to". I expect your algorithm to be *perfectly fair*, and the tests check this.

### Part 3: Train TextGenerator

Next you will implement `TextGenerator.train()`.

The method takes a list of documents and will build up PhraseStats objects for all the unique word pairs it encounters.
What datastructure do you need to keep track of this information?
Add the necessary instance variables and implement `train()` and `getPhraseStats()`.
I've given you a `splitIntoWords()` method that you **MUST** use to break up a document into words.

If you have successfully implemented the method, all the TestTextGenerator methods should pass.
However, the edge cases (the first and last few words) are tricky.
The test cases are arranged from easiest to most difficult.
The last few cases carefully check the boundary conditions and are most difficult.


### Part 4: Make up text!

Finally, implement `generate()` and a reasonable `main()` method.
The main method should simply call train with data from the WikAPIdia wrapper (look for the appropriate method).
Note that you have access to six languages - all defined by constants in Utils.
It will then call `generate()` - perhaps several times - and print out the results.

**Important note:** I found some invocations of generate would result in ginormous Strings that made my program run out of memory.
To work around this, stop generating a string if it gets to long (e.g. if it has more than 1000 words).

### Challenges

* Instead of always using the two preceding words, make your MarkVShaney class take the context size as an input. Two works best, but 1 and 3 yield interesting results as well!
* Weight the probability of the different successors according to frequency. A simple implementation strategy is to allow duplicate successor words in the table, but as a challenge, save memory by keeping a count of each word instead of duplicating it.