# cz2001-algorithms


### Lab 3A - Empirical comparison between Insertion sort and Mergesort

```
The objective for this lab is to perform empirical comparison of time efficiency between the two sorting algorithms, namely Insertion Sort and Mergesort. For simplicity, suppose the input data are arrays of integers and the algorithms should sort the integers into ascending order.

In this lab, the following steps need to be carried out:

1. Algorithm implementation: Implement the algorithms of Insertion Sort and Mergesort in the same programming language (after choosing one from Java, C or C++). For Mergesort, in order to achieve high speed, you can use an auxiliary array to store the result of merging.

2. Generating input data: Generate arrays of increasing sizes, say in a range from 1000 to 1 million. For each of the sizes, generate the following types of data:
    (1) Randomly generated datasets of integers in the range [1 ... n].
    (2) Integers 1, 2, ..., n sorted in ascending order.
    (3) Integers n, n−1, ...,1 sorted in descending order.
    
3. Measuring time complexity: Run your programs of the two sorting algorithms on the datasets generated in Step 2. Count the numbers of key comparisons (i.e. comparisons between array elements), and record the CPU times. The statistical results (i.e. numbers of key comparisons and CPU times) should be recorded into a table.

4. Analysis of results: Draw scatter plots to visualize the running times of the two algorithms on the different types of data (i.e. integers in random, ascending, and descending orders). The variable on x-axis is the size of input array, and the variable on y-axis is the running time. In each plot, show how the running time increases with the input size. Compare your empirical results with theoretical analysis of time complexity.
```


### Lab 4B - Application of BFS to flight scheduling

```
Construct an undirected graph to represent non-stop airline flights between cities in the world (a hypothetical graph for Asia Pacific is given below). In your graph, please add more cities and flights to make it more realistic. It should contain at least one pair of cities, between which there is no non-stop flight, but there is a route (or path) between them.

Implement the Breadth-first search (BFS) algorithm (using Java or C/C++) to find a route between two cities with the minimum number of stops. That is, when user inputs the names of two cities, your program should return one route with the smallest possible number of stop(s). If a non-stop flight is available, it will just return the departure city and arrival city.
```
