package common;

/**
 * Contains various constant values used throughout the application. It serves as a utility class.
 * 
 * The Constants class centralizes commonly used string constants to 
 * ensure consistency and ease of maintenance across the application. 
 * This design allows for easy modification of constants without changing the logic of the application.
 * 
 */
// to put magic literals
public class Constants {
    // Ui
    public static final String NEW_LINE = "\n";
    public static final String PERCENT = "%";
    public static final String S = "s";
    public static final String T = "T";
    public static final String D = "D";
    public static final String E = "E";
    public static final String FD = "FD";
    public static final String X = "X";

    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String DOT = ".";
    public static final String DOT_SPACE = DOT + SPACE;
    public static final String OPEN_ROUND_BRACKET = "(";
    public static final String CLOSE_ROUND_BRACKET = ")";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "]";
    public static final String OPEN_ANGLE_BRACKET = "<";
    public static final String CLOSE_ANGLE_BRACKET = ">";
    public static final String ENCODE_TASK_SEPARATOR = SPACE + "|" + SPACE;
    
    public static final String DESCRIPTION = "description";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String BY = "by";
    public static final String DATE = "date";
    public static final String DUE = "due";
    public static final String DUE_DATE = DUE + SPACE + DATE;
    public static final String SLASH_BY = SLASH + BY;
    public static final String FROM = "from";
    public static final String START = "start";
    public static final String START_DATE_TIME = START + SPACE + DATE + SLASH + "time";
    public static final String SLASH_FROM = SLASH + FROM;
    public static final String TO = "to";
    public static final String END = "end";
    public static final String END_DATE_TIME = END + SPACE + DATE + SLASH + "time";
    public static final String SLASH_TO = SLASH + TO;
    public static final String DURATION = "duration";
    public static final String HOURS = "hours";
    public static final String SLASH_DURATION = SLASH + DURATION;
    public static final String TASK = "task";
    public static final String TASK_NUMBER = TASK + SPACE + "number";
}
