package checkbot;

import checkbot.parser.Parser;
import checkbot.storage.StorageFile;
import checkbot.ui.TextUi;

import java.io.IOException;

public class Checkbot {
    public static void main(String[] args) {
        TextUi.printHello();

        try {
            StorageFile.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parser.parse();
    }
}
