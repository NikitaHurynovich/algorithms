package container;

public class OrderedArray {
  private int nElems;
  private Integer[] array;

  public OrderedArray(int size) {
    this.nElems = 0;
    this.array = new Integer[size];
  }

  public int size() {
    return nElems;
  }

  public int find(int searchKey) {
    int leftBound = 0;
    int rightBound = nElems - 1;

    while (leftBound >= rightBound) {
      int middle = (rightBound + leftBound) / 2;
      if (searchKey == array[middle]) {
        return middle;
      }

      if (searchKey == array[leftBound]) {
        return leftBound;
      }

      if (searchKey == array[rightBound]) {
        return rightBound;
      }

      if (searchKey > array[middle]) {
        leftBound = middle;
      } else if (searchKey < array[middle]) {
        rightBound = middle;
      }
    }

    return -1;
  }

  public void insert(int value) {
    if (nElems + 1 > array.length) {
      this.increaseSize();
    }
    for (int i = 0; i < nElems; i++) {
      if (array[i] >= value) {
        nElems++;
        return;
      }
    }
  }

  public boolean delete(int value) {
    int index = find(value);
    if (index < 0) {
      return false;
    }

    for (int i = index; i < nElems; i++) {
      array[i] = array[i+1];
    }
    nElems--;
    return true;
  }

  private void increaseSize() {
    Integer[] newArray = new Integer[array.length * 2];
    for(int i = 0; i < array.length; i ++) {
      newArray[i] = array[i];
    }
    this.array = newArray;
  }

  public int[] merge(int[] secondArray) {
    if (secondArray == null || secondArray.length == 0) {
      return null;
    }
    int[] mergedArray = new int[array.length + secondArray.length];
    int firstPointer = 0;
    int secondPointer = 0;
    for (int i = 0; i < mergedArray.length; i++) {
      if (firstPointer < array.length && secondPointer < secondArray.length) {
        if (array[firstPointer] < secondArray[secondPointer]) {
          mergedArray[i] = array[firstPointer];
          firstPointer++;
        } else {
          mergedArray[i] = secondArray[secondPointer];
          secondPointer++;
        }
      } else if (secondPointer < secondArray.length) {
        mergedArray[i] = secondArray[secondPointer];
        secondPointer++;
      } else if (firstPointer < array.length) {
        mergedArray[i] = array[firstPointer];
        firstPointer++;
      }
    }
    return mergedArray;
  }

  public void noDups() {
    if (nElems == 0) {
      return;
    }
    for (int i = 0; i < nElems; i++) {
      Integer valueToCompare = array[i];
      if (valueToCompare != null) {
        for (int j = i + 1; j < nElems; j++) {
          if (array[j] == valueToCompare) {
            array[i] = null;
            array[j] = null;
          }
        }
      }
    }

    int duplicateCount = 0;
    for (int i = 0; i < nElems; i++) {
      Integer value = array[i];
      if (value == null) {
        duplicateCount++;
        for(int j = i; j < nElems - 1; j++) {
          array[j] = array[j+1];
        }
      }
    }
    nElems -= duplicateCount;
  }

  public void display() {
    if (nElems == 0) {
      System.out.println("Ordered array is empty");
      return;
    }
    System.out.println("===========Ordered array===========");
    for (int i = 0; i < nElems; i ++) {
      System.out.println(array[i]);
    }
  }
}
