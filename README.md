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

Given that its output comes eerily close to making sense, and is often even grammatically correct, the algorithm is surprisingly simple. Mark V. Shaney (hereafter “MVS”) is a pun on “markov chain.” MVS reads a collection of documents. Each document contains input text, and makes a transition table that answers the following question: “Given a previous pair of words, what are the choices for the next word, and how often do they occur?”

For example, for the document “Jack be nimble, Jack be quick”, MVS generates the following transition table:

<table>
  <tr><th align="left">from</th> <td>→</td> <th>to</th></tr>
  <tr><td></td> <td>→</td> <td>Jack:1</td></tr>
  <tr><td>Jack</td> <td>→</td> <td>be:1</td></tr>
  <tr><td>Jack be</td> <td>→</td> <td>nimble,:1</td> <td>quick:1</td></tr>
  <tr><td>be nimble,</td> <td>→</td> <td>Jack:1</td></tr>
  <tr><td>nimble, Jack</td><td>→</td> <td>be:1</td></tr>
</table>

Note that the table includes entries for _no words preceding_ (i.e. the very beginning of the text), and _one word preceding_ (the first word of the text).

Also note that MVS is case sensitive, and considers anything that is not a space to be part of a “word” — including line breaks and punctuation. This behavior allows it to form sentences and paragraphs.

Continuing our example, presume that MVS next encounters the document "Why does Jack be nimble, and quick?" The table now becomes:

<table>
  <tr><th align="left">from</th> <td>→</td> <th>to</th></tr>
  <tr><td></td> <td>→</td> <td>Jack:1</td><td>Why:1</td></tr>
  <tr><td>Jack</td> <td>→</td> <td>be:1</td></tr>
  <tr><td>Jack be</td> <td>→</td> <td>nimble,:2</td> <td>quick:1</td></tr>
  <tr><td>be nimble,</td> <td>→</td> <td>Jack:1</td><td>and:1</td></tr>
  <tr><td>nimble, Jack</td><td>→</td> <td>be:1</td></tr>
  <tr><td>Why</td> <td>→</td> <td>does:1</td></tr>
  <tr><td>Why does</td> <td>→</td> <td>Jack:1</td></tr>
  <tr><td>does Jack</td> <td>→</td> <td>be:1</td></tr>
  <tr><td>nimble, and</td> <td>→</td> <td>quick?:1</td></tr>
</table>

Having built this table of information, MVS  generates new text by looking up the last pair of words output, and randomly selecting the next word from the available options. If there are no options, it stops. For example, with the table above:

* The initial context is “” (i.e. nothing). In the table, the next word for that context is “Jack”, so that’s what MVS outputs.
* Now the context is “Jack”. The next word is “be”.
* Now the context is “Jack be”. For this context, MVS has _two_ choices: “nimble,” or “quick”. MVS randomly chooses one; let’s say it’s “quick”.
* Now the context is “be quick”. There is no next word in the table for this context, so MVS stops.

### Part 0: Prepare your environment

 - Fork this repo, and clone your fork.
 - Import the module into IntelliJ.
 - **Important:** Add all the jars in the `hw9/lib` as libraries (right click -> add as library).

Begin by looking over the framework I have given you:

* `BigramCounter.java` keeps track of the count of words that follows a [bigram](http://en.wikipedia.org/wiki/Bigram, or pair of words that appears consecutively.
For example, the BigramCounter remembers that for the bigram "Jack be,"  "nimble" folows it twice and "quick" follows it once.
* `TextGenerator.java` will update the bigram counter to reflect a collection of texts, and implement the code to use the bigram counter to generate text.
* `BigramCounter.java` ensures that your PhraseStats implementation is correct.
* `TestTextGenerator.java` ensures that your TextGenerator *trains* correctly.

### Part 1: Make BigramCounter count

Your first step is to make BigramCounter count correctly. Begin by making sure you understand exactly what it *should* do.

Once you understand the purpose of BigramCounter, add all the instance variables it will need to do its job and implement its methods.

Once you have successfully finished this step, TestBigramCounter should pass.



### Part 3: Train TextGenerator

Next you will implement `TextGenerator.train()`.

The method takes a list of documents and will build up BigramCounter object for all the unique word pairs it encounters.
To split text into words, you **must** do the following: 

```java
String words[] = text.split(" +");
```

You should skip all documents with less than two words.

If you have successfully implemented the method, all the TestTextGenerator methods should pass **except for testSelect()**.
The test cases are arranged from easiest to most difficult.
The last few cases carefully check the boundary conditions (first and last words) and are most difficult.


### Part 4: Make up text!

Implement the `TextGenerator.select() method`.
It should randomly select from among the candidate words following the bigram.
After you've successfully implemented it, testSelect should pass.

Finally, implement the generate() method.
You should be able to run TextGenerator to see it in actaion!

Select a favorite text from [project Gutenberg](http://www.gutenberg.org/) and change the url in `TextGenerator.main()` to your url.
Run your program. Does it make sense?

### Challenges

* Instead of always using the two preceding words, make your MarkVShaney class take the context size as an input. Two works best, but 1 and 3 yield interesting results as well!
* Weight the probability of the different successors according to frequency. A simple implementation strategy is to allow duplicate successor words in the table, but as a challenge, save memory by keeping a count of each word instead of duplicating it.
