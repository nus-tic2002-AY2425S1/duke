import java.io.IOException;

public class MarkCommand extends Command{

    public MarkCommand(int index, boolean bool){
        super(index, bool);
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task_mark = tasks.get(getIndex());
        task_mark.setDone(getBool());
        if (getBool())
            System.out.println("Nice! I've marked this task as done:)");
        else
            System.out.println("I've unmarked this task as done:)");
        System.out.println(task_mark);
    }
}
