package core.mate.model;

import java.util.List;

public class DirectoryData {

    private String directory;
    private List<String> data;

    private DirectoryData(String directory, List<String> data) {
        this.directory = directory;
        this.data = data;
    }

    public String getDirectory() {
        return directory;
    }

    public List<String> getData() {
        return data;
    }

    public static DirectoryData of(String directory, List<String> data) {
        return new DirectoryData(directory, data);
    }
}
