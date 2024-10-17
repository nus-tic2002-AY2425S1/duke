import java.io.FileNotFoundException;
import java.io.IOException;

import checkbot.Parser.Parser;
import checkbot.Storage.StorageFile;
import checkbot.Ui.*;

public class Main {
    public static void main(String[] args) {
        TextUi.printHello();

        // read file from existing file and add into tasks
        try {
            StorageFile.readFile(StorageFile.taskFile);
        } catch (FileNotFoundException e) {
            try {
                StorageFile.taskFile.getParentFile().mkdir();
                StorageFile.taskFile.createNewFile();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

        Parser.parse();
    }
}
