package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;

import alice.task.Deadline;
import alice.task.Event;
import alice.task.TaskList;
import alice.task.Todo;
import alice.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * <h1>Add Command</h1>
 * The AddCommand class allows the user to add
 * tasks into the tasklist.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class AddCommand extends Command {

    private static final String[] DATE_PATTERNS = {
            "yyyy-MM-dd",
            "yyyy-M-d",
            "dd-MM-yyyy",
            "d-M-yyyy",
            "MM/dd/yyyy",
            "M/d/yyyy",
            "yyyy/MM/dd",
            "yyyy/M/d",
            "yyyy-MM-dd",
            "yyyy-M-d",
            "dd MMM yyyy",
            "d MMM yyyy",
            "dd/MM/yyyy",
            "d/M/yyyy",
            "dd-MM-yyyy",
            "d-M-yyyy",
            "MMM dd yyyy",
            "MMM d yyyy"
    };

    public AddCommand(String action, String instruction) {
        super(action, instruction);
    }

    /**
     * This method is set to false as this command does not trigger the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * This method is used to create acceptable datetime format for the /by
     * parameter in deadline
     * @return DateTimeFormatter This returns the format that the parameter will
     * be processed in.
     */
    //Solution below adapted from https://stackoverflow.com/questions/57553322/handling-multiple-formats-in-datetimeformatter
    // and https://coderanch.com/t/677142/java/DateTimeParseException-Text-parsed-unparsed-text
    public static DateTimeFormatter buildFormatter() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        for (String pattern : DATE_PATTERNS) {
            builder.parseCaseInsensitive()
                    .appendOptional(DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)).optionalStart()
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .optionalEnd();
        }
        return builder.toFormatter().withResolverStyle(ResolverStyle.SMART);
    }

    /**
     * This method is used to add tasks into the tasklist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {

        if (getInstruction().isEmpty()) {
            throw new NoArgsException("arguments");
        }

        switch (getAction()) {
            case "todo":
                assert !getInstruction().isEmpty() : "Instruction should have at least one word to describe the task";

                tasks.add(new Todo(getInstruction()));
                System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
                ui.showSize(tasks.size());
                break;
            case "deadline":
                if (!getInstruction().contains(" /by ")) {
                    throw new NoArgsException("/by");
                }

                assert getInstruction().length() > 1 : "Instructions should have more than 1 word (the parameter)";

                ArrayList<String> deadlineInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /by ")));
                try {
                    tasks.add(new Deadline(deadlineInstruction.get(0), LocalDate.parse(deadlineInstruction.get(deadlineInstruction.size()-1), buildFormatter())));
                    System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
                    ui.showSize(tasks.size());
                } catch (DateTimeParseException e){
                    ui.showError("Unidentifiable datetime format");
                }
                break;
            case "event":
                if (!getInstruction().contains(" /from ")) {
                    throw new NoArgsException("/from");
                } else if (!getInstruction().contains(" /to ")) {
                    throw new NoArgsException("/to");
                }

                assert getInstruction().length() > 2 : "Instructions should have more than 2 words (the parameters)";

                ArrayList<String> eventInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /from ")));
                ArrayList<String> eventInstruction2 = new ArrayList<>(Arrays.asList(eventInstruction.get(eventInstruction.size()-1).split(" /to ")));

                tasks.add(new Event(eventInstruction.get(0), LocalDate.parse(eventInstruction2.get(0),buildFormatter()), LocalDate.parse(eventInstruction2.get(eventInstruction.size()-1),buildFormatter())));
                System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
                ui.showSize(tasks.size());
                break;
            default:
                throw new AssertionError("No such tasks");
        }


    }
}