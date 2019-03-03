package grades;

/**
 * A representation of a numeric grade in the range [0 .. 100].
 * 
 * @author pgries
 *
 */
public class NumericGrade extends Grade {

  /** A numeric grade value in the range [0 .. 100]. */
  private int grade;

  /**
   * A new NumericGrade with value grade.
   * 
   * @param grade the numeric grade.
   */
  public NumericGrade(int grade) {
    this.grade = grade;
  }

  @Override
  public double gpa() {
    if (grade < 50) {
      return 0.0;
    }
    if (grade < 53) {
      return 0.3;
    }
    if (grade < 57) {
      return 0.7;
    }
    return 4.0;
  }

}
