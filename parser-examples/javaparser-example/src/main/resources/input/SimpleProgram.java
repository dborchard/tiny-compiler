public class SimpleProgram {

  public static void main(String[] args) {
    int a = 10;
    int b = 20;
    int c = a + b;
    System.out.println(c + "");

    for (int age = 1; age < 20; age++) {
      System.out.println("Your age is " + age);

      if (age > 18) {
        System.out.println("You can vote in your current age");
      }
    }

    for (int vote = 0; vote < 5; vote++) {
      System.out.println("You voted " + vote + " times.");
    }

    if (true) {
      System.out.println("True");
    } else {
      System.out.println("False");
    }
  }
}
