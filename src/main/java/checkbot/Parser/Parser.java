package checkbot.Parser;

import checkbot.Exception.EmptyInputException;
import checkbot.Exception.EmptyTimeException;
import checkbot.Storage.StorageFile;
import checkbot.Task.TaskList;
import checkbot.Ui.TextUi;
import checkbot.Utils.Messages;

import java.time.LocalDateTime;

public class Parser {
    static boolean goToExit = false;

    public static void parse() {
        do {
            String input = TextUi.readInput().trim();
            String keyword = input.split(" ")[0].toLowerCase();

            switch (keyword) {
                case "bye":
                    TextUi.printExit();
                    goToExit = true;
                    break;
                case "list":
                    TextUi.printTasks();
                    break;
                case "mark":
                    // fallthrough
                case "unmark":
                    // fallthrough
                case "delete":
                    try {
                        TaskList.setStatus(input);
                        StorageFile.updateFile();
                        break;
                    } catch (EmptyInputException e) {
                        System.out.println(Messages.emptyNumber);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(Messages.nonIntegerNumber);
                        break;
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println(Messages.invalidTaskNumber);
                        break;
                    }
                case "todo":
                    // fallthrough
                case "deadline":
                    // fallthrough
                case "event":
                    try {
                        TaskList.addTask(input);
                        StorageFile.updateFile();
                        break;
                    } catch (EmptyInputException e) {
                        TextUi.printEmptyDescription();
                        break;
                    } catch (EmptyTimeException e) {
                        TextUi.printEmptyTime();
                        break;
                    }
                default:
                    TextUi.printCommandNotFound();
            }
        } while (!goToExit);
    }

    // TODO: Exceptions
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
