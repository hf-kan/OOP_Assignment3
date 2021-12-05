import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";
    private static final File FILE = new File(FILE_LOGGER_NAME);

    static {

        /* create a new File object for FILE_LOGGER_NAME
         * if the file already exists, delete it first
         * use try/catch block
         */
        try {
            if (!FILE.createNewFile()) {
                FILE.delete();
                FILE.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void log(String message) {
        /* create a new FileWriter in append mode
         * write the message to file
         * check the ExpectedOutput files
         * use try-with-resources/catch block
         */
        try (FileWriter writer = new FileWriter(FILE, true)){
            for (int i = 0; i < message.length(); i++)
                writer.append(message.charAt(i));
            writer.append(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
