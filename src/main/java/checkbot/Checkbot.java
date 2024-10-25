package checkbot;

import java.io.FileNotFoundException;
import java.io.IOException;

import checkbot.Parser.Parser;
import checkbot.Storage.StorageFile;
import checkbot.Ui.TextUi;

public class Checkbot {
    public static void main(String[] args) {
        TextUi.printHello();
        StorageFile.readFile();
        Parser.parse();
    }
}
