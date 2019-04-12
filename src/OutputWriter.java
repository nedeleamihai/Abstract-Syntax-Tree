import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class OutputWriter {
    private PrintWriter writer;

    public OutputWriter(String fileName) {
        try {
            writer = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Could not write to the file " + fileName);
        }
    }

    public void writeStringToFile(String stringToBeWritten) {
        writer.print(stringToBeWritten);
        writer.flush();
    }

    public void stopWritingToFile() {
        writer.close();
    }
}