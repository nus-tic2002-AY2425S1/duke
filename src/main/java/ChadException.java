public class ChadException extends Exception {  
    public ChadException(String errorMessage) {  
    super(errorMessage);  
    }  
} 
/*
 * 1.when user input non-number for mark/unmark/delete task
 * 2.when user input out of range number for mark/unmark/delete task
 * 3.when user input invalid format for todo/event/deadline
 * 3-1. 
 * 
 */