package demo;

import algorithms.Sorting;

public class SortingDemo {

  public static void startBubbleAndInsertDemo() {
    Integer[] testArray = ArraysDemo.createRandomArray();
    ArraysDemo.displayArray(testArray);
    Sorting.bubbleSort(testArray);
    System.out.println("========After sorting");
    ArraysDemo.displayArray(testArray);
  }
}
