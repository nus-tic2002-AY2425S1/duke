package checkbot;

import checkbot.parser.Parser;
import checkbot.storage.StorageFile;
import checkbot.ui.TextUi;

public class Checkbot {
    public static void main(String[] args) {
        TextUi.printHello();
        StorageFile.readFile();
        Parser.parse();
    }
}
