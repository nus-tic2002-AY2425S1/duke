package checkbot.Parser;

import checkbot.Exception.EmptyInputException;
import checkbot.Exception.EmptyTimeException;
import checkbot.Storage.StorageFile;
import checkbot.Task.TaskList;
import checkbot.Ui.TextUi;
import checkbot.Utils.Messages;

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
}
