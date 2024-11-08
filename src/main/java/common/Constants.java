package common;

/**
 * A utility class that contains various constant values used throughout the application.
 * 
 * <p>
 * The Constants class centralizes commonly used string constants to ensure consistency and ease of maintenance across the application. 
 * This design allows for easy modification of constants without changing the logic of the application.
 * </p>
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
    public static final String X = "X";

    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String OPEN_ROUND_BRACKET = "(";
    public static final String CLOSE_ROUND_BRACKET = ")";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "]";
    public static final String OPEN_ANGLE_BRACKET = "<";
    public static final String CLOSE_ANGLE_BRACKET = ">";
    public static final String ENCODE_TASK_SEPARATOR = " | ";
    
    public static final String DESCRIPTION = "description";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String BY = "by";
    public static final String DUE_DATE = "due date";
    public static final String SLASH_BY = SLASH + BY;
    public static final String FROM = "from";
    public static final String START_DATE_TIME = "start date/time";
    public static final String SLASH_FROM = SLASH + FROM;
    public static final String TO = "to";
    public static final String END_DATE_TIME = "end date/time";
    public static final String SLASH_TO = SLASH + TO;
    public static final String TASK = "task";
    public static final String TASK_NUMBER = TASK + SPACE + "number";
    public static final String DATE = "date";
}
