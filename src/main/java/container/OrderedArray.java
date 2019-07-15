package container;

public class OrderedArray {
  private int nElems;
  private long[] a;

  public OrderedArray(int size) {
    this.nElems = 0;
    this.a = new long[size];
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
