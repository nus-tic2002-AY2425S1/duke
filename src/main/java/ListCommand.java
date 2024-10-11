import java.io.IOException;

public class ListCommand extends Command{

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        System.out.println("Here are the tasks in your list:");
        tasks.printTasks();
    }
}
