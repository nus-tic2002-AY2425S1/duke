public class ByeCommand extends Command {
    
    public static final String COMMAND_WORD = "bye";
    
    @Override
    public void execute(Ui ui) {
    // public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
