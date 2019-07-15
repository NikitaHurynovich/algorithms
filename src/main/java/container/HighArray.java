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

  public int find(long searchKey) {
    return -1;
  }

  public void insert(long value) {

  }

  public boolean delete(long value) {
    return true;
  }
}
