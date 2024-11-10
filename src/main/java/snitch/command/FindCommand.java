package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes the FindCommand by searching for tasks containing the keyword.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance (not used in this command).
     * @throws SnitchException If the keyword is empty or no tasks match the search.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        if (keyword.isEmpty()) {
            throw new SnitchException("Meow. Keyword cannot be empty. Please provide a valid keyword.");
        }

        TaskList matchingTasks = tasks.findTasksContaining(keyword);
        if (matchingTasks.isEmpty()) {
            throw new SnitchException("Meow. No matching tasks found for keyword: " + keyword);
        }

        ui.showMatchingTasks(matchingTasks);
    }
}