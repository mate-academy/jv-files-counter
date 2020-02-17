package core.mate.util;

import core.mate.model.DirectoryData;

import java.util.List;

public class DisplayResult {
    private static int counter = 0;
     
    public static void showResults(List<DirectoryData> results) {
        System.out.println("--------------------------------------------------------");
        System.out.format("%10s %20s %20s", "Id", "FilesQuantity", "Path");
        System.out.println();

        for (DirectoryData data : results) {
            counter++;
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.format("%10d %20d %20s", counter, data.getData().size(),
                    data.getDirectory());
            System.out.println();
        }
    }
}
