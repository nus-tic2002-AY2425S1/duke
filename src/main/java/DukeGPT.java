import java.util.*;

import command.*;
import command.CommandHandler;
import exception.DukeException;
import output.OutputHandler;
import tasks.*;

public class DukeGPT {
    private static String chatbotName = "DukeGPT";
    private static List<Task> tasks = new ArrayList<>();
    private static Map<String, CommandHandler> commandHandlerMapping = new HashMap<>();

    private static void initCommandHandlers() {
        commandHandlerMapping.put("list", new ListCommandHandler());
        commandHandlerMapping.put("mark", new MarkCommandHandler());
        commandHandlerMapping.put("unmark", new UnmarkCommandHandler());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initCommandHandlers();
        OutputHandler.printGreetings(chatbotName);

        while (true){
            String userInput = scanner.nextLine().trim();
            try{
                if(userInput.equalsIgnoreCase("bye")){
                    OutputHandler.printGoodbye();
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
                    OutputHandler.printAddedItems(newTask, tasks);
                }
            } catch (DukeException e){
                OutputHandler.printError(e.getMessage());
            } catch (NumberFormatException e){
                OutputHandler.printError("OOPS!!! Please provide a valid task number!");
            } catch (Exception e){
                OutputHandler.printError("OOPS!!! Something went wrong, please try again");
            }
        }
    }
}
