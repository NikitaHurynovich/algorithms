package demo;

import algorithms.Sorting;

public class SortingDemo {

  public static void startBubbleAndInsertDemo() {
    Integer[] testArray = ArraysDemo.createRandomArray(100000);
    ArraysDemo.displayArray(testArray);
    long startTime = System.currentTimeMillis();
    Sorting.bubbleSort(testArray);
    long finishTime = System.currentTimeMillis();
    ArraysDemo.displayArray(testArray);
    System.out.println("========Bubble sorting time: " + (finishTime - startTime) + " mls");

  }
}
