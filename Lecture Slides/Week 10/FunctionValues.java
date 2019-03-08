package floatingpoint;

public class FunctionValues {

  public static void functionValues_v1(double start, double stop, double increment) {
    double x;
    // Don't expect floating point numbers to be exact.
    for (x = start; x <= stop; x += increment) {
      System.out.println("x=" + x);
    }
  }

  public static void functionValues_v2(double start, double stop, double increment) {
    double x = 0;
    for (int i = 1; i <= 5; i += 1) {
      x = start + i * 0.1;
      System.out.println("x=" + x);
    }
  }

  /**
   * @param args
   *            the command line arguments
   */
  public static void main(String[] args) {

//		System.out.println("v1(0.1, 0.5, 0.1)");
//		functionValues_v1(0.1, 0.5, 0.1);
//		System.out.println("v1(1.1, 1.5, 0.1)"); // Get 4 iterations,
//													// but we expect 5!
//		functionValues_v1(1.1, 1.5, 0.1);

    System.out.println("v2(0.1, 0.5, 0.1)");
    functionValues_v2(0.1, 0.5, 0.1);
    System.out.println("v2(1.1, 1.5, 0.1)");
    functionValues_v2(1.1, 1.5, 0.1);
  }
}