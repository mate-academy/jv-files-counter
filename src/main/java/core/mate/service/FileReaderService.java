package core.mate.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {

    public List<String> parseFile(String file) {
        try {
            File sourceFile = new File(file);
            return Files.readAllLines(sourceFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
