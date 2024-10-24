public class WKDuke {
    private final Ui ui;
    private Storage storage;
    private TaskList taskList;

    public WKDuke() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskList = storage.load();
        } catch (StorageOperationException | FileContentException e) {
            ui.showInitError(e);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        new WKDuke().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parseCommand(userInput);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (StorageOperationException e) {
                ui.showError(e);
                System.exit(-1);
            } catch (WKDukeException e) {
                ui.showError(e);
            }
        }
    }
}
