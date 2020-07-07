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

  public static void insertSort(Integer[] input) {

    int temp = 0,
        j =0;
    for(int i = 1; i < input.length; i++) {
      temp = input[i];
      for (j = i; j > 0 && input[j] >= temp; j--) {
        input[j] = input[j - 1];
      }
      input[j] = temp;
    }
  }

  public static void insertSort2(Integer[] input) {

    int temp = 0, j =0;
    for(int i = 1; i < input.length; i++) {
      temp = input[i];
      j = i;
      while (j > 0) {
        if (temp < input[j - 1]) {
          input[j] = input[j - 1];
          j--;
        } else {
          break;
        }
      }
      input[j] = temp;
    }
  }


  public static void insertSortReverse(Integer[] input) {

    int temp = 0, j=0;
    for(int i = input.length - 2; i >= 0; i--) {
      temp = input[i];
      j = i;
      while(j + 1 < input.length ) {
        if (temp < input[j + 1]) {
          input[j] = input[j + 1];
          j++;
        } else {
          break;
        }

      }
      input[j] = temp;
    }
  }
}
