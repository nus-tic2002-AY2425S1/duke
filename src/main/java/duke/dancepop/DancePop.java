package duke.dancepop;

import duke.dancepop.utils.Storage;

public class DancePop {
    public static void main(String[] args) {
        Storage.loadFile();
        UI.start();
    }
}
