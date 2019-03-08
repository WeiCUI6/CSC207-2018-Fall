package floatingpoint;

public class Adding {
  public static void main(String[] args) {
    double v = 0.1;
   while (v != 1.0) {
     System.out.println(v);
     v = v + 0.1;
   }

    double sum = v + v + v;
    System.out.print("A: ");
    System.out.println(sum == 0.3);

    double s = 1.0e-6; // 0.000001
    double bigger = 1.0;

    double sum1 = s + s + s + s + s + s + s + s + s + bigger;
    double sum2 = bigger + s + s + s + s + s + s + s + s + s;
    System.out.print("B: ");
    System.out.println(sum1 == sum2);
    System.out.print("C: ");
    System.out.println(sum1);
    System.out.print("D: ");
    System.out.println(sum2);

    float x;
    x = 4.0f / 5.0f;
    // Print x to 7 decimal places.
    System.out.println(String.format("x = %.7f  \n", x));
    // Print x to 16 decimal places.
    System.out.println(String.format("x = %.16f  \n", x));
  }
}
