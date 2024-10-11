import java.io.IOException;

public class UnknownCommand extends Command{

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {
        ui.showError("Please put an instruction I can understand :(");
    }
}
