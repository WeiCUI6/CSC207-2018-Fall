import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcherL0101 {

    public static void main(String[] args) {
        // Come up with a string that satisfies
        // ^[abc]*$ but not ^a*b*c*$

        // Pattern.matches automatically anchors.
//        System.out.println(
//                Pattern.matches("a*b", "aaaab"));
////        System.out.println(
////                Pattern.matches("a*b", "%aaaab"));
//        System.out.println(
//                Pattern.matches("\\(", "("));
//        System.out.println(
//                Pattern.matches("\\\\", "\\"));

//        doMatching();

        // Match course codes.
        // Regex is: [A-Z][A-Z][A-Z]
        // Regex is: [A-Z]{3}[1-4]\d{2}[HY][15][FSY]
//        Pattern p = Pattern.compile("([A-Z]{3})([1-4]\\d{2})[HY][15]([FSY])");
//        Matcher m = p.matcher("CSC207H1S");
//        System.out.println(m.matches());
//        System.out.println(m.group(0));
//        System.out.println(m.group(1));
//        System.out.println(m.group(2));
//        System.out.println(m.group(3));

//        Pattern p = Pattern.compile("(\\d\\d\\d)\\\\\\1");
//        Matcher m = p.matcher("123\\123");
//        System.out.println(m.matches());
//        System.out.println(m.group(-1));

        // Match a floating point number.
        /*
        1.0
        2.2
        1.85
        -3.14
        4.0
        .0
        0.
        12.
         */

        System.out.println(
                Pattern.matches(
                        "(-)?((\\d+\\.\\d*)|(\\.\\d+))",
                        "-11."));
        System.out.println(
                Pattern.matches(
                        "(-)?((\\d+\\.\\d*)|(\\.\\d+))",
                        "-.11"));
        System.out.println(
                Pattern.matches(
                        "(-)?((\\d+\\.\\d*)|(\\.\\d+))",
                        "-121.11"));



    }

    /**
     * Prompts the user to enter a regex and a string
     * and reports whether that regex matches the string.
     * Type quit to exit.
     */
    private static void doMatching() {
        Scanner sc = new Scanner(System.in);
        String re, line;
        System.out.println("Regex?");
        re = sc.nextLine();

        System.out.println("String?");
        line = sc.nextLine();
        while (!line.equals("quit")) {
            System.out.println(Pattern.matches(re, line));
            System.out.println("String?");
            line = sc.nextLine();
        }
    }
}
