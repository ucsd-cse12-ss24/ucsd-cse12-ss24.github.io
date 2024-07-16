---
layout: with-sidebar
index: 4
name: PA4
released-on: "2024-07-12"
---
# CSE 12 Programming Assignment 4


### Runtime, Measured and Modeled

**This assignment is open to collaboration.**

This assignment will give you experience working with big-Ο/θ/Ω
representations, practice matching them to implementations, and perform
measurements of the runtime of different methods.

_This assignment is inspired by a combination of a lab in Swarthmore College's
CS35, and by a similar assignment by Marina Langlois in CSE12 at UCSD_

This PA is due on ** **Wednesday, July 24 at 11pm** **


# Part 1: Runtime Analysis Questions

## Big-O Justification

Indicate whether the following assertions are true or false, and give a
justification:

- _(n*log(n) + 2n)/3n<sup>2</sup>_ is _Ω(1/n)_
- _(n*log(n) + 2n)/3n<sup>2</sup>_ is _O(1/n)_
- _log(log(n))_ is _Ω(1/n<sup>10</sup>)_
- _(2<sup>n</sup>)<sup>2</sup> + n/4 + 6_ is _O((2n)!)_
- _1/(n<sup>50</sup>) + log16_ is _Ω(1)_
- n!-n<sup>1000</sup> is _O(2<sup>n +1)_

If you are justifying the positive direction, give choices of `n0` and `C`. For
big-Θ, make sure to justify both big-O and big-Ω, or big-O in both directions.

If you are justifying the negative direction, indicate which term(s) can't work
because one is guaranteed to grow faster or slower than the other.

As a quick guide, here is an ordering of functions from slowest-growing
(indeed, the first two _shrink_ as n increases) to fastest-growing that you
might find helpful:

- f(n) = 1/(n<sup>2</sup>)
- f(n) = 1/n
- f(n) = 1
- f(n) = log(n)
- f(n) = sqrt(n)
- f(n) = n
- f(n) = n<sup>2</sup>
- f(n) = n<sup>3</sup>
- f(n) = n<sup>4</sup>
- ... and so on for constant polynomials ...
- f(n) = 2<sup>n</sup>
- f(n) = n!
- f(n) = n<sup>n</sup>

This portion will be submitted via Gradescope. It can be found in the *Programming Assignment 4 - questions* assignment (this is question 1).

## List Analysis

Consider the two files `ArrayStringList.java` and
`LinkedStringList.java`, which are included in [this repository](https://github.com/ucsd-cse12-ss24/ucsd-cse12-ss24.github.io/tree/main/_lectures/lecture-02).
Answer the following questions, and justify them with one or two sentences
each:

- Give a tight big-O bound for the running time of `insert` in
  ArrayStringList
- Give a tight big-O bound for the running time of `insert` in
  LinkedStringList
- Give a tight big-O bound for the running time of `remove` in
  ArrayStringList
- Give a tight big-O bound for the running time of `remove` in
  LinkedStringList
- Give a tight big-O bound for the _best case_ running time of `add` in
  ArrayStringList
- Give a tight big-O bound for the _worst case_ running time of `add` in
  ArrayStringList
- Give a tight big-O bound for the _best case_ running time of `add` in
  LinkedStringList
- Give a tight big-O bound for the _worst case_ running time of `add` in
  LinkedStringList

In all cases, give answers in terms of the _current size of the list_, and
assume that the list has some non-empty size _n_. That is, you shouldn't
consider the empty list as a best case; instead think about the best case based
on other factors like size, capacity, and nodes.

Notable points to consider:

- Creating an array takes time proportional to the length of the array
- When considering the running time of a method, make sure to take into
  account any helpers methods it uses!

Example for `get` in the `LinkedStringList` class:

    The get method is O(1) in the best case, when the index is 0. In this case
    it will do constant work checking the index and immediately return the
    first element, never entering the while loop.

    The get method is O(n) in the worst case, because the index could be at
    the end of the list (for example, index n - 1). In this case, the while
    loop will run n times, spending constant time on each iteration, resulting
    in overall O(n) number of steps taken.

This portion will be submitted via Gradescope. It can be found in the *Programming Assignment 4 - questions* assignment (this is question 2). Make sure to follow the formatting instuctions!




## Part 2 (Sorting): A Bad (and Good) Implementation Detector

This part of the assignment will teach you how to write tests in a thorough, automated way, will explore some properties of quicksort, and will give you structured practice in re-using code you find on the Web.

*This part of the assignment is inspired by [an assignment from Brown University's
CS019](https://cs.brown.edu/courses/cs019/2016/sortaclesortacle.html).*

The starter code for part 2 can be found from: [https://github.com/ucsd-cse12-ss24/cse12-pa5-pt2-Partition](https://github.com/ucsd-cse12-ss24/cse12-pa5-pt2-Partition).


### Testing with Properties

So far in this class, we have usually written tests by following this process:

1. Construct the input data
2. Perform an operation
3. Check that the resulting data is equal to some expected value

This works well for writing a small or medium number of tests targeted at
particularly interesting cases. Checking specific output values, however, isn't
the only or necessarily the best way to test and gain confidence in an
implementation. In fact, sometimes it won't work at all.

Consider the `partition` helper method of quick sort as an interface (here
we'll restrict it to just partitioning arrays of `String`s):

```java
interface Partitioner {
  // Change strs between start (inclusive) and end (exclusive), such that
  // all values at indices lower than a pivot index are smaller than or equal
  // to the value at the pivot, and all values at indices higher than the pivot
  // are larger than or equal to the value at the pivot

  int partition(String[] strs, int start, int end);
}
```

In lecture and discussion, we noted that there are many ways to implement
`partition`, in particular the choice of the _pivot index_ is important. Not
only could we choose different pivots, but one choice is to have a _random_
choice of pivot!  Let's imagine writing a test for a `Partitioner`:

```java
class PartitionerFromLecture implements Partitioner {
  public int partition(String[] strs, int low, int high) {
    int pivotStartIndex = Random.nextInt(high - low);
    ... implementation from lecture ...
  }
}


@Test
public void testPartitionerFromLecture() {
  Partitioner p = new PartitionerFromLecture();
  String[] input = {"z", "b", "a", "f"};
  int pivot = p.partition(input, 0, 4);

  assertArrayEquals(???, input); // What to expect?
  assertEquals(???, pivot);
}
```

For two items, there are some clever solutions. You can use [special
matchers](https://stackoverflow.com/a/19064484/2718315),
for instance.

Instead of writing out all the tests by hand, we should step back from the
problem. We really care that the array is _correctly partitioned_ – there
shouldn't be elements larger than the pivot value at earlier indices, or
elements smaller than the pivot value at later indices. There are other
properties, too, like all the elements that were in the input list should
appear the same number of times in the output list – if `partition` duplicates
or loses elements, it isn't doing its job!

So, instead of writing single tests, we should write methods that, given a
partition algorithm, check if it satisfies some desired _properties_ that
partitioning ought to. Properties sufficient to show a valid partitioning are:

- All the elements in the original array are present in the array _after_ we
  call partition
- No values at indices other than those from `low` (inclusive) to `high`
  (exclusive) changed their values
- The elements from `low` to `high` are correctly partitioned:
  - `partition` returns some _pivot index_ between `low` (inclusive) and `high`
    (exclusive)
  - At all indices from `low` up to the pivot index the string is smaller
    than or equal to (according to `compareTo`) the value at the pivot index
  - At all indices from the pivot index up to `high - 1`, the string is larger
    than or equal to (according to `compareTo`) the value at the pivot index

### Your Task

You will turn the properties above into code that checks if a given result from
partition is valid.  That means your program will decide, for any call to
`partition`, if it behaves as we'd expect. Further, we can extend this idea to
build a method that takes a `Partitioner` and returns `null` if we believe it
to be a good partitioner, and a `CounterExample` if we can find an input array
and low/high bounds that partition incorrectly:

```java
CounterExample findCounterExample(Partitioner p);
```

`CounterExample` is defined to contain:

- The _input_ to a call to partition (an array, a low index, and a high index)
- The _result_ of a call to partition (an array and a returned pivot index)
- A `reason`, as a `String`, that you choose in order to describe why it is
  invalid. Some suggestions are below.

You will write a version of `CounterExample` and use it to check multiple
different partition implementations, some good and some bad. Note that, even
beyond the argument above about randomness, there are _multiple possible
correct implementations of partition_.

You must implement two methods to help you implement `CounterExample`; you can implement other helpers as
you see fit. The two methods you must implement are:

```java
/*
 * Return null if the pivot and after array reflect a correct partitioning of 
 * the before array between low and high.
 *
 * Return a non-null String (your choice) describing why it isn't a valid
 * partition if it is not a valid result. You might choose Strings like these,
 * though there may be more you want to report:
 *
 * - "after array doesn't have same elements as before"
 * - "Item before pivot too large"
 * - "Item after pivot too small"
 */
String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after)
```

```java
/*
 * Generate a list of items of size n
 */
String[] generateInput(int n);
```

This method should create a list of items to use as input to purported
partition algorithms. It's up to you how it generates the items; it should
produce an array of length `n`, however.

### An Overall Strategy

Here's one way you might approach this problem:

- First, implement and test `isValidPartitionResult`. Think of several
  interesting individual cases (specific arrays and low/high bounds) you can
  imagine in a first pass, and test it on those cases.  Note that to test
  `isValidPartitionResult`, you will be creating pairs of arrays of strings for
  input and expected output (at first, by hand), and checking _both_ for
  success and for failure: you should have some tests where the `after`
  parameter and `pivot` describe an incorrect partitioning, and some correct.
- Implement `generateInput` in a simple way – make `n` Strings of random single
  characters. Test that the method returns the right number of elements without
  any errors.
- Implement a (really) incorrect version of `Partitioner`, that makes no
  changes at all to the underlying array in its `partition` method. Implement
  a _good_ version of `Partitioner` as well (you can take the one from
  class/discussion), adapted to work as a `Partitioner`.
- Try putting together a first version of `findCounterExample`. It could create
  a single list using `generateInput`, partition it with the given partitioner,
  check if it was sorted correctly using `isValidPartitionResult`, and return
  `null` if it partitioned correctly or a `CounterExample` if it didn't. Note:
  you will need to _save_ the original array, since sorters can and will make
  changes to them! You can use `Arrays.copyOf` to make a copy of an array:
  
  ```java
  String[] input1 = {"a", "b", "c", "a"};
  String[] original1 = Arrays.copyOf(input1, input1.length);
  ```
    
  With this flow, you can test that `findCounterExample` returns `null` when
  passed the good partitioner, and a `CounterExample` when given the bad
  partitioner. The testing methods `assertNull` and `assertNotNull` can be
  helpful here.
- Note that you should generate multiple lists and test several partitions in `findCounterExample` 
  to properly vet each partitioner.
  
You can write these tests in `TestPartitionOracle.java` (yes, the tester has
its own tests!). This will get you through the beginning of the problem, and
familiar with all the major interfaces. With this in hand, you can proceed with
more refined tests. Here are some ideas:

- Make a copy of the good `Partitioner` you wrote, and change it in a subtle
  way, maybe change a < to a <= in comparison or vice versa. Is it still a good
  partitioner? Can your `findCounterExample` check that?
- Make a copy of the good `Partitioner` you wrote and change it in an obviously
  breaking way, maybe by setting an element to the wrong value. Does
  `findCounterExample` correctly return some `CounterExample` for this
  implementation?
- Change `findCounterExample` to call `generateInput` many times, and check that
  _all_ the generated lists sort correctly, returning the first failure as a
  `CounterExample` if it didn't.
- Feel free to add some interesting hand-written cases to `findCounterExample`
  where you use interesting input lists that you construct by hand. You can
  combine whether they sort correctly or not (e.g. partition them and then check
  `isValidPartitionResult`).
- Use the two partition implementations that you wrote and the implementation you found on the web (below) to check
  if they are good or bad.
- The `java.util.Random` class has useful tools for generating random numbers
  and strings.  You can create a random number generator and use it to get
  random integers from 0 to a bound, which you can combine with ASCII codes to
  get readable random strings:

  ```
  Random r = new Random();
  int asciiForACapLetter = r.nextInt(26) + 65;  // Generates a random letter from A - Z
  String s = Character.toString((char)(asciiForACapLetter));
  ```
- You may find it useful to copy the arrays into lists so you can remove
  elements and use other list operations in your oracle. This is a useful
  one-line way to copy an array into an ArrayList:

  ```
  List<String> afterAsList = new ArrayList<>(Arrays.asList(after));
  ```

Overall, your goal is to make it so `findCounterExample` will return `null` for
any reasonable good partition implementation, and find a `CounterExample` for
any bad partition implementation with extremely high probability. We will
provide you with a bunch of them to test against while the assignment is out,
and we may test on more than we provide you in the initial autograder.

We won't test on truly crazy situations, like a partitioner that only fails
when passed lists of 322 elements, or when a one of the strings in the array is
`"Henry"`. The bad implementations will involve things logically related to
sorting and manipulating lists, like boundary cases, duplicates, ordering,
length, base cases, and comparisons, as a few examples.

### What to do about `null`?
**Assume** that there are **no `null`** items in the arrays, that sorts won't put`null` items in the arrays, and that the variables holding lists of items won't contain `null`. There are plenty of interesting behavior to consider without it!

**Don't** have your implementation of `findCounterExample` take more than a few
seconds per sorting implementation. You don't need to create million element
lists to find the issues, and it will just slow down grading. You should focus
on generating (many, maybe hundreds or thousands of) small interesting lists
rather than a few big ones, which should process very quickly.


On this PA, we will not give deductions for violating stnadard style guidelines (but you should still follow them):



## Submission

#### Part 1
You may submit as many times as you like till the deadline.

- The `Programming Assignment 4 - questions` assignment in Gradescope, where you will submit the written part of this PA.
    - The first question your big-O justifications.
  - The second question is for your List analysis.
  - The third question is for your matchings for the mystery functions, along with your graphs and justifications. The following are what need to be answered in the subquestions.
      - The BigO bounds for each implementation f1-6.
    - A listing that matches each of mysteryA-F to an implementation f1-6 above 
    - Three graphs that justify a few choices above. These don't need to
      exhaustively describe all of your matchings, but they must be generated
      from real data that you measured using `measure`, and they must show an
      interesting relationship that helps justify the matching. 
  - The last section gives you a space to indicate who you collaborated with (if you collaborated with anyone).

If you want a guide on how to get from the CSV data to a graph, look here:
[https://docs.google.com/document/d/1efFyv4QmggBMxrphb5gF40i5_wBUfIeUMw9-Abrs0fY/edit?usp=sharing](https://docs.google.com/document/d/1efFyv4QmggBMxrphb5gF40i5_wBUfIeUMw9-Abrs0fY/edit?usp=sharing)

#### Part 2

On the Gradescope assignment **Programming Assignment 4 - code** please submit the following files:

* PartitionOracle.java
* CounterExample.java
        
You may encounter errors if you submit extra files or directories. You may submit as many times as you like till the deadline. 

## Grade Breakdown

Note that this assignment has a lot of manual grading, so there’s less value in submitting after the deadline.

#### Part 1 (52 points total)

- 12 points: initial big-O justifications [manually graded]
- 40 points: stack and queue method analysis [manually graded]
- 0 points: Who you collaborated with for both Part 1 and Part 2. Provide this in your `Programming Assignment 4 - questions` submission.

#### Part 2 (36 points total)

- 10 points: `isValidPartitionResult`, graded automatically
- 5 points: `generateInput`, graded automatically
- 11 points: `findCounterExample`, graded by how it performs on good and bad
  partitions that we provide, graded automatically



