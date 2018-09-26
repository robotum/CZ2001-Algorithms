# CZ2001-Algorithms

## Lab 3A - Empirical comparison between Insertion sort and Mergesort

```
The objective for this lab is to perform empirical comparison of time efficiency between the two sorting algorithms, namely Insertion Sort and Mergesort. For simplicity, suppose the input data are arrays of integers and the algorithms should sort the integers into ascending order.

In this lab, the following steps need to be carried out:

1. **Algorithm implementation:** Implement the algorithms of Insertion Sort and Mergesort in the same programming language (after choosing one from Java, C or C++). For Mergesort, in order to achieve high speed, you can use an auxiliary array to store the result of merging.

2. **Generating input data:** Generate arrays of increasing sizes, say in a range from 1000 to 1 million. For each of the sizes, generate the following types of data:
    (1) Randomly generated datasets of integers in the range [1 ... n].
    (2) Integers 1, 2, ..., n sorted in ascending order.
    (3) Integers n, n−1, ...,1 sorted in descending order.
    
3. **Measuring time complexity:** Run your programs of the two sorting algorithms on the datasets generated in Step 2. Count the numbers of key comparisons (i.e. comparisons between array elements), and record the CPU times. The statistical results (i.e. numbers of key comparisons and CPU times) should be recorded into a table.

4. **Analysis of results:** Draw scatter plots to visualize the running times of the two algorithms on the different types of data (i.e. integers in random, ascending, and descending orders). The variable on x-axis is the size of input array, and the variable on y-axis is the running time. In each plot, show how the running time increases with the input size. Compare your empirical results with theoretical analysis of time complexity.
```
