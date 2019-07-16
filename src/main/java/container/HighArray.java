package container;

public class HighArray {
  private int nElems;
  private int[] array;

  public HighArray(int size) {
    this.nElems = 0;
    array = new int[size];
  }

  public int size() {
    return nElems;
  }

  public int find(int searchKey) {
    for(int i = 0; i < nElems; i++) {
      if (searchKey == array[i]) {
        return i;
      }
    }
    return -1;
  }

  public void insert(int value) {
    if (array.length >= nElems) {
      this.increaseSize();
    }
    this.array[nElems + 1] = value;
    nElems++;
  }

  private void increaseSize() {
    int[] newArray = new int[array.length * 2];
    for(int i = 0; i < array.length; i ++) {
      newArray[i] = array[i];
    }
    this.array = newArray;
  }

  public boolean delete(int value) {
    int deletePos = find(value);
    if (deletePos == -1) {
      return false;
    }
    nElems--;
    for (int i = deletePos; i < nElems; i++) {
      array[i] = array[i +1];
    }
    return true;
  }

  public int getMax() {
    if (nElems == 0) {
      return -1;
    }
    int max = array[0];
    for(int i = 0 ; i < nElems; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  public int removeMax() {
    if (nElems == 0) {
      return -1;
    }
    int max = array[0];
    for(int i = 0 ; i < nElems; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    delete(max);
    return max;

  }

  public void display() {
    if (nElems == 0) {
      System.out.println("High array is empty");
      return;
    }
    System.out.println("===========High array===========");
    for (int i = 0; i < nElems; i ++) {
      System.out.println("[" + i+ "] = " + array[i]);
    }
  }
}
