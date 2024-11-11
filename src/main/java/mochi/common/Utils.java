package mochi.common;

public class Utils {
  public static String trimStringArrayWithStartEnd(String[] s, String start, String end, String delimiter) {
    String body = "";
    boolean shouldCopy = false;
    for (String i : s) {
      if (!end.isEmpty() && i.equals(end))
        break;
      if(shouldCopy) {
        body += i + delimiter;
      }
      if (i.equalsIgnoreCase(start))
        shouldCopy = true;
    }
    return body.trim();
  }
}
