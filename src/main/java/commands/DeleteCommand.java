package commands;

import root.*;
import tasks.Task;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;  // Import IOException

public class DeleteCommand extends Command {
    private String taskIndices;

    public DeleteCommand(String taskIndices) {
        this.taskIndices = taskIndices;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {  // Declare IOException here
        List<String> indices = Arrays.asList(taskIndices.split(" "));  // Split the input into task indices
        boolean found = false;

        // Sort the indices in descending order to avoid index shifting after deletion
        indices.sort((a, b) -> Integer.compare(Integer.parseInt(b), Integer.parseInt(a)));

        for (String indexStr : indices) {
            try {
                int taskIndex = Integer.parseInt(indexStr) - 1;  // Convert the string index to 0-based index
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    ui.showError("\tInvalid task number: " + (taskIndex + 1));
                    continue;
                }

                Task taskToDelete = tasks.get(taskIndex);
                tasks.deleteTask(taskIndex);  // Remove the task from the list
                storage.saveTasks(tasks.getTasks());  // Save updated task list
                ui.showLine();
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t" + taskToDelete);
                found = true;
            } catch (NumberFormatException e) {
                ui.showError("\tPlease provide valid task numbers.");
            }
        }

        if (!found) {
            System.out.println("\tNo tasks were deleted.");
        }

        ui.showLine();
    }
}
