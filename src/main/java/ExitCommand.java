import java.io.IOException;

public class ExitCommand extends Command{

    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeToFile("data/tasks.txt", tasks.toString());
        ui.showEnding();
    }

}
