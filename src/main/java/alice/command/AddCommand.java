package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;
import alice.task.*;
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
 * The AddCommand program implements an application that
 * allows the user to add tasks into the tasklist.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-10-16
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
    private static DateTimeFormatter buildFormatter() {
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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {

        if (getInstruction().isEmpty())
            throw new NoArgsException("arguments");

        switch (getAction()) {
            case "todo":
                tasks.add(new Todo(getInstruction()));
                System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
                ui.showSize(tasks.size());
                break;
            case "deadline":
                if (!getInstruction().contains(" /by "))
                    throw new NoArgsException("/by");

                ArrayList<String> deadlineInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /by ")));
                try {
                    tasks.add(new Deadline(deadlineInstruction.getFirst(), LocalDate.parse(deadlineInstruction.getLast(), buildFormatter())));
                    System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
                    ui.showSize(tasks.size());
                } catch (DateTimeParseException e){
                    ui.showError("Unidentifiable datetime format");
                }
                break;
            case "event":
                if (!getInstruction().contains(" /from "))
                    throw new NoArgsException("/from");
                else if (!getInstruction().contains(" /to "))
                    throw new NoArgsException("/to");

                ArrayList<String> eventInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /from ")));
                ArrayList<String> eventInstruction2 = new ArrayList<>(Arrays.asList(eventInstruction.getLast().split(" /to ")));

                tasks.add(new Event(eventInstruction.getFirst(), eventInstruction2.getFirst(), eventInstruction2.getLast()));
                System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
                ui.showSize(tasks.size());
                break;
        }


    }
}