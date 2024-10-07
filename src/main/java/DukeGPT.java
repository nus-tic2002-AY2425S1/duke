import java.util.*;

import command.*;
import command.CommandHandler;
import exception.DukeException;
import tasks.*;
import static output.OutputHandler.*;



public class DukeGPT {
    private static String chatbotName = "DukeGPT";
    private static List<Task> tasks = new ArrayList<>();
    private static Map<String, CommandHandler> commandHandlerMapping = new HashMap<>();

    private static void initCommandHandlers() {
        commandHandlerMapping.put("list", new ListCommandHandler());
        commandHandlerMapping.put("mark", new MarkCommandHandler());
        commandHandlerMapping.put("unmark", new UnmarkCommandHandler());
        commandHandlerMapping.put("delete", new DeleteCommandHandler());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initCommandHandlers();
        printGreetings(chatbotName);

        while (true){
            String userInput = scanner.nextLine().trim();
            try{
                if(userInput.equalsIgnoreCase("bye")){
                    printGoodbye();
                    break;
                }
                else if(userInput.trim().isEmpty()){
                    throw new DukeException("OOPS!!! Input cannot be empty!");
                }
                String[] inputParts = userInput.split(" ", 2);
                CommandHandler commandHandler = commandHandlerMapping.get(inputParts[0].toLowerCase());

                if(commandHandler != null){
                    commandHandler.handle(userInput, tasks);
                }else {
                    Task newTask  = CommandFactory.generateTask(userInput);
                    tasks.add(newTask);
                    printAddedItems(newTask, tasks);
                }
            } catch (DukeException e){
                printError(e.getMessage());
            } catch (NumberFormatException e){
                printError("OOPS!!! Please provide a valid task number!");
            } catch (Exception e){
                printError("OOPS!!! Something went wrong, please try again");
            }
        }
    }
}
