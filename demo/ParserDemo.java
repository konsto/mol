
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParserDemo {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String text    = "print (a)  ";
        String temp = "";
      String patternString1 = "(\\(.*\\))";
      Pattern pattern = Pattern.compile(patternString1);
      Matcher matcher = pattern.matcher(text);
      while(matcher.find()) {
          temp = matcher.group(1);
      }
      System.out.println(temp);
    }
}
