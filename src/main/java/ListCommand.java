import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list: ";
    public static final String MESSAGE_FAILED = "Your task list is currently empty.";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.printMessages(MESSAGE_FAILED);
            return;
        }

        List<String> messages = new ArrayList<>();
        messages.add(MESSAGE_SUCCESS);
        for (int i = 0; i < taskList.size(); i++) {
            messages.add(String.format("%d.%s", i + 1, taskList.getTask(i)));
        }
        ui.printMessages(messages.toArray(new String[0]));
    }
}
