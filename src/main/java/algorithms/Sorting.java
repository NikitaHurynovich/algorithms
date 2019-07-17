package algorithms;

public class Sorting {

  public static void bubbleSort(Integer[] input) {
    Integer element;
    for (int i = 0; i < input.length; i++) {
      for (int j = i; j < input.length; j++) {
        if (input[i] > input[j]) {
          element = input[j];
          input[j] = input[i];
          input[i] = element;
        }
      }
    }
  }
}
