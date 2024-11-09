public class AddCommand implements Command {
    private final String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        if (fullCommand.startsWith("todo")) {
            String description = fullCommand.substring(5).trim();
            if (description.isEmpty()) {
                throw new SnitchException("Come on man!!! The description of a todo cannot be empty.");
            }
            Task newTask = new Todo(description);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } else if (fullCommand.startsWith("deadline")) {
            String[] parts = fullCommand.split("/by ");
            if (parts.length < 2) {
                throw new SnitchException("Come on man!!! The deadline description cannot be empty or the format is wrong. (Do it like this: deadline task /by xxxx)");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } else if (fullCommand.startsWith("event")) {
            String[] parts = fullCommand.split("/from ");
            if (parts.length < 2 || !parts[1].contains("/to ")) {
                throw new SnitchException("Come on man!!! The event format is incorrect.");
            }
            String description = parts[0].substring(6).trim();
            String[] timeParts = parts[1].split(" /to ");
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            Task newTask = new Event(description, from, to);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } else {
            throw new SnitchException("Invalid command for adding tasks.");
        }

        // Save tasks after adding
        storage.save(tasks);
    }
}
