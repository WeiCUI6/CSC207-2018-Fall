package grades;

public abstract class Grade {
  
  /**
   * Return the GPA equivalent on the regular 4-point scale.
   * @return a number in the range [0.0 .. 4.0]
   */
  public abstract double gpa();
  
  public static void main(String[] args) {
    System.out.println("Moogah");
  }
}
