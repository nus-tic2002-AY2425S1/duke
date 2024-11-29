package common;

/**
 * Contains various constant values used throughout the application. It serves as a utility class.
 * The Constants class centralizes commonly used string constants to
 * ensure consistency and ease of maintenance across the application.
 * This design allows for easy modification of constants without changing the logic of the application.
 */
// to put magic literals
public class Constants {

    // Add a private constructor to hide the implicit public one
    private Constants() {
    }

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
    public static final String BACKTICK = "`";
    public static final String COMMA = ",";
    public static final String UNDERSCORE = "_";
    public static final String ENCODE_TASK_SEPARATOR = SPACE + "|" + SPACE;

    public static final String DESCRIPTION = "description";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String DESCRIPTION_IN_ANGLE_BRACKETS = OPEN_ANGLE_BRACKET + DESCRIPTION + CLOSE_ANGLE_BRACKET;
    public static final String BY = "by";
    public static final String DATE = "date";
    public static final String DUE = "due";
    public static final String DUE_DATE = DUE + SPACE + DATE;
    public static final String DUE_DATE_IN_ANGLE_BRACKETS = OPEN_ANGLE_BRACKET + DUE_DATE + CLOSE_ANGLE_BRACKET;
    public static final String SLASH_BY = SLASH + BY;
    public static final String FROM = "from";
    public static final String START = "start";
    public static final String TIME = "time";
    public static final String START_DATE_TIME = START + SPACE + DATE + SLASH + TIME;
    public static final String START_DATE_TIME_IN_ANGLE_BRACKETS = OPEN_ANGLE_BRACKET + START_DATE_TIME + CLOSE_ANGLE_BRACKET;
    public static final String SLASH_FROM = SLASH + FROM;
    public static final String TO = "to";
    public static final String END = "end";
    public static final String END_DATE_TIME = END + SPACE + DATE + SLASH + TIME;
    public static final String END_DATE_TIME_IN_ANGLE_BRACKETS = OPEN_ANGLE_BRACKET + END_DATE_TIME + CLOSE_ANGLE_BRACKET;
    public static final String SLASH_TO = SLASH + TO;
    public static final String DURATION = "duration";
    public static final String HOURS = "hours";
    public static final String SLASH_DURATION = SLASH + DURATION;
    public static final String DURATION_IN_HOURS_IN_ANGLE_BRACKETS = OPEN_ANGLE_BRACKET + DURATION + SPACE + "in" + SPACE + HOURS + CLOSE_ANGLE_BRACKET;
    public static final String TASK = "task";
    public static final String TASK_NUMBER = TASK + SPACE + "number";
    public static final String TASK_NUMBER_IN_ANGLE_BRACKETS = OPEN_ANGLE_BRACKET + TASK + SPACE + "number" + CLOSE_ANGLE_BRACKET;

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int FOUR = 4;
    public static final int FIVE = 5;

}
