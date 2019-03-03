package grades;

import java.util.ArrayList;
import java.util.List;

public class DemoAbstract {

  public static void main(String[] args) {
    List<Grade> grades = new ArrayList<Grade>();
    Grade g1 = new LetterGrade("B");
    grades.add(g1);
    Grade g2 = new NumericGrade(58);
    grades.add(g2);
    for (Grade g : grades) {
      System.out.println(g.gpa());
    }
  }
}
