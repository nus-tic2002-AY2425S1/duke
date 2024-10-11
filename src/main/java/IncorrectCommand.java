import java.io.IOException;

public class IncorrectCommand extends Command{

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showError("Incorrect number of args!");
    }
}
