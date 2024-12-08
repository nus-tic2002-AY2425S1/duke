package mochi.common.exception;

public class ExceptionMessages {
    public static final String TASK_EXIST = "Input task name exist in the system, please use another task name";
    public static final String INVALID_PATH_EXCEPTION = "The provided path is invalid: ";
    public static final String SECURITY_PATH_EXCEPTION = "Insufficient permissions to access the path: ";
    public static final String NUMBER_FORMAT_EXCEPTION = "The expected parameter is in a wrong format";
    public static final String TASK_ID_NOT_FOUND = "Input task ID not found, please specify correct task ID";
    public static final String TASK_NAME_EMPTY = "Ops, `name` parameter is empty, please supply in correct format";
    public static final String TASK_EVENT_FROM_EMPTY = "Ops, the event's `from` parameter is empty, please supply in correct format";
    public static final String TASK_EVENT_TO_EMPTY = "Ops, the event's `to` parameter is empty, please supply in correct format";
    public static final String TASK_DEADLINE_BY_EMPTY = "Ops, the deadline's `to` parameter is empty, please supply in correct format";
    public static final String DATE_FORMAT_INVALID = "Invalid date format. Please use 'd/M/yyyy HHmm' format.";
    public static final String FIND_INVALID = "Keyword is empty, please specify correct keyword";
}
