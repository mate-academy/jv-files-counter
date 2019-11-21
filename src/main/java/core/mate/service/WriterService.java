package core.mate.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriterService {

    public void clearFile(String outputFile) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            System.err.println("File not found " + outputFile);
            e.printStackTrace();
        }
    }

    public File writeToFile(String data, String outputFile) {
        File file = new File(outputFile);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO: handle the exception properly
        }
        return file;
    }
}
