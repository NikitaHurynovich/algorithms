import container.HighArray;
import container.OrderedArray;


public class Application {

  private final static Integer SIZE = 20;

  public static void main(String[] args) {

    HighArray highArray = new HighArray(20);
    int valueToFind = 0;
    for (int i = 0 ; i < SIZE; i ++) {
      int value = (int)(Math.random() * 100);
      highArray.insert(value);
      if (i == 10) {
        valueToFind = value;
      }
    }

    highArray.display();
    int idex = highArray.find(valueToFind);
    System.out.println("Index = " + idex + ", value = " + valueToFind);
    System.out.println("Delete value = " + valueToFind);
    highArray.delete(valueToFind);
    highArray.display();
    System.out.println("Max value = " + highArray.getMax());

    OrderedArray orderedArray = new OrderedArray(20);
    boolean createDuplicate = false;
    for (int i = 0; i < SIZE; i++) {
      if (createDuplicate) {
        orderedArray.insert(i - 1);
      }
      orderedArray.insert(i);
      createDuplicate = (int) (Math.random() * 100) % 2 == 0;

    }
    orderedArray.display();

    valueToFind = 6;
    idex = orderedArray.find(valueToFind);
    System.out.println("Index = " + idex + ", value = " + valueToFind);
    System.out.println("Delete value = " + valueToFind);
    orderedArray.delete(valueToFind);
    orderedArray.display();
    int[] mergedArray = orderedArray.merge(new int[]{2,5,7,33,37});
    System.out.println("==============merged array");
    for (int i = 0; i < mergedArray.length; i ++) {
      System.out.println("[" +i +"] = " + mergedArray[i]);
    }

    orderedArray.noDups();
    orderedArray.display();


  }
}
