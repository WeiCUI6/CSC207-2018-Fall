package grades;

/**
 * A letter grade. One of "A", "B", "C", "D", and "F".
 */
public class LetterGrade extends Grade {
  
  /** The grade, one of "A", "B", "C", "D", and "F". */
  private String grade;
  
  /**
   * A new LetterGrade for grade. One of "A", "B", "C", "D", and "F"
   * @param grade the letter grade.
   */
  public LetterGrade(String grade) {
    this.grade = grade;
  }
  
  @Override
  public double gpa() {
    double gpaValue = 0.0;
    switch (this.grade) {
      case "A": gpaValue = 4.0; break;
      case "B": gpaValue = 3.0; break;
      case "C": gpaValue = 2.0; break;
      case "D": gpaValue = 1.0; break;
      case "F": gpaValue = 0.0; break;
      default: throw new RuntimeException("Ugh");
    }
    
    return gpaValue;
  }

}
