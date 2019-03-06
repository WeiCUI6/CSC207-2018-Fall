import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexChecker {
 

  private static Pattern pattern;
  private static Matcher matcher;
  
  public RegexChecker() {
    this("What the heck?");
  }

  public RegexChecker(String s) {
    
  }
  
  
  public static void main(String[] args){
    System.out.println(Pattern.matches("([\\w]){2}\\1", "aff"));

    System.out.println(Pattern.matches("[k*]a*b", "*aaaaab"));

    pattern = Pattern.compile("\\+{5}");
    matcher = pattern.matcher("a1bb\\\\c2a4++++++++6c89");
    List<String> alphaNumSequence = new ArrayList<String>();
    while(matcher.find()) {
    	System.out.println(matcher.group());
    	alphaNumSequence.add(matcher.group());
    }

    String str = new String();
    str = "Hello_World";
    boolean isSame = str.matches("Hello\\w.*");
    System.out.println(isSame);
   }
}
