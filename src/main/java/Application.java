import container.HighArray;

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


  }
}
