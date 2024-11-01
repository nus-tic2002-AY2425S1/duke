public class TaskListDecoderException extends JavaroException {
    public TaskListDecoderException(String error) {
        super(error);
    }
    
    public TaskListDecoderException(String error, String info) {
        super(error, info);
    }

    public TaskListDecoderException(String error, String info, String usage) {
        super(error, info, usage);
    }
}
