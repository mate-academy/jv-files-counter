package core.basesyntax.service;

import core.basesyntax.model.DirectoryData;
import core.basesyntax.model.DirectoryWalker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WriterService {

    public static void clearFile(String outputFile) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            System.out.println("File not found " + e);
        }
    }

    public static File writeToFile(String data, String outputFile) {
        File file = new File(outputFile);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            //TODO: handle the exception properly
        }
        return file;
    }

    public static List<DirectoryData> traverseDirectory(List<String> directories) {
        ExecutorService executorService = Executors.newFixedThreadPool(directories.size());
        List<Callable<DirectoryData>> threads = new ArrayList<>();

        for (String directory : directories) {
            DirectoryWalker directoryWalkerThread = new DirectoryWalker(directory);
            threads.add(directoryWalkerThread);
        }

        List<Future<DirectoryData>> futures;
        try {
            futures = executorService.invokeAll(threads);
        } catch (InterruptedException e) {
            return Collections.emptyList();
        }

        List<DirectoryData> results = new ArrayList<>();
        for (Future<DirectoryData> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        return results;
    }
}
