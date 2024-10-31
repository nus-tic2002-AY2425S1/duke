public class JavaroException extends Exception {

    protected String problem = null;
    protected String solution = null;

    public JavaroException(String message) {
        super(message);
    }

    public JavaroException(String message, String problem) {
        super(message);
        this.problem = problem;
    }

    public JavaroException(String message, String problem, String solution) {
        this(message, problem);
        this.solution = solution;
    }
}