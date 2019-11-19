package core.mate.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class DirectoryWalker implements Callable<DirectoryData> {
    private String dir;
    private List<String> result;

    public DirectoryWalker(String dir) {
        this.dir = dir;
        result = new ArrayList<>();
    }

    @Override
    public DirectoryData call() {
        try {
            result = Files.walk(Paths.get(dir))
                    .parallel()
                    .filter(p -> !p.toFile().isDirectory())
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return DirectoryData.of(dir, result);
    }
}
