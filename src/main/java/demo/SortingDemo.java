package demo;

import algorithms.Sorting;

public class SortingDemo {

  public static void startBubbleAndInsertDemo() {
    Integer[] testArray = ArraysDemo.createRandomArray(10);
    ArraysDemo.displayArray(testArray);
    long startTime = System.currentTimeMillis();
    Sorting.bubbleSort(testArray);
    long finishTime = System.currentTimeMillis();
    ArraysDemo.displayArray(testArray);
    long bubbleSortingTime = finishTime - startTime;


    testArray = ArraysDemo.createRandomArray(10);
    ArraysDemo.displayArray(testArray);
    startTime = System.currentTimeMillis();
    Sorting.insertSort2(testArray);
    finishTime = System.currentTimeMillis();
    ArraysDemo.displayArray(testArray);
    long insertSortingTime = finishTime - startTime;

    System.out.println("========Insert sorting time: " + insertSortingTime + " mls");
    System.out.println("========Bubble sorting time: " + bubbleSortingTime + " mls");


  }
}
