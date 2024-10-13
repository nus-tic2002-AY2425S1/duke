package alice.exception;

public class NoArgsException extends Exception{
    private final String error;

    public NoArgsException(String error){
        this.error = error;
    }

    public String getMessage(){
        return "No "+ error +" in the alice.command!";
    }
}
