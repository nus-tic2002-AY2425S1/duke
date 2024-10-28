package checkbot.parser;

import checkbot.exception.CommandNotFoundException;
import checkbot.exception.EmptyInputException;
import checkbot.exception.EmptyTimeException;
import checkbot.storage.StorageFile;
import checkbot.task.Task;
import checkbot.task.TaskList;
import checkbot.ui.TextUi;
import checkbot.utils.Messages;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {
    static boolean isFinished = false;

    public static void parse() {
        do {
            String input = TextUi.readInput().trim();
            String keyword = input.split(" ")[0].toLowerCase();
            
            switch (keyword) {
            case "bye":
                TextUi.printExit();
                isFinished = true;
                break;
            case "help":
                TextUi.printHelp();
                break;
            case "list":
                TextUi.printTasks();
                break;
            case "mark":
                // fallthrough
            case "unmark":
                // fallthrough
            case "delete":
                // fallthrough
            case "rank":
                try {
                    TaskList.setStatus(input);
                    StorageFile.updateFile();
                    break;
                } catch (EmptyInputException e) {
                    System.out.println(Messages.EMPTY_NUMBER);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(Messages.NON_INTEGER_NUMBER);
                    break;
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(Messages.INVALID_TASK_NUMBER);
                    break;
                } catch (CommandNotFoundException e) {
                    System.out.println(Messages.INVALID_PRIORITY);
                    break;
                }
            case "find":
                TextUi.printMatchingTasks(input.split(" ",2)[1].trim());
                break;
            case "todo":
                // fallthrough
            case "deadline":
                // fallthrough
            case "event":
                try {
                    Task task = TaskList.addTask(input);
                    StorageFile.updateFile();
                    TextUi.echoAddTask(task);
                    break;
                } catch (EmptyInputException e) {
                    TextUi.printEmptyDescription();
                    break;
                } catch (EmptyTimeException e) {
                    TextUi.printEmptyTime();
                    break;
                } catch (DateTimeException e) {
                    System.out.println(e.getMessage());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(Messages.INVALID_DATETIME);
                    break;
                }
            default:
                TextUi.printCommandNotFound();
            }
        } while (!isFinished);
    }

    /**
     * Takes in date and time in String with format of DD/MM/YYYY HHMM(24H)
     * and converts it into a LocalDateTime object.
     *
     * @param input Format: DD/MM/YYYY HHMM(24H)
     * @return LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String input) {
        String[] dateTimeArray = input.split(" ",2);

        String date = dateTimeArray[0].trim();
        String[] dateArray = date.split("/",3);
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);

        String time = dateTimeArray[1].trim();
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2, 4));

        return LocalDateTime.of(year, month, day, hour, min);
    }
}
