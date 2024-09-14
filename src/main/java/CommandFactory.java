import java.util.HashMap;
import java.util.Map;
import tasks.*;
public class CommandFactory {
    public static final Map<String, Task> taskMap = new HashMap<>();
    static {
        taskMap.put("todo", new ToDo(null));
        taskMap.put("event", new Event(null,null,null));
        taskMap.put("deadline", new Deadline(null,null));
    }

    public static Task generateTask(String userInput){
        String [] commandParts = userInput.split(" ", 2);
        String command = commandParts[0];
        String commandString = commandParts.length == 2 ? commandParts[1] : null;

        Task taskTemplate = taskMap.get(command.toLowerCase());

        if(taskTemplate != null && commandString != null){
            return taskTemplate.createTask(commandString);
        } else {
            throw new IllegalArgumentException("Invalid command");
        }
    }
}
