package core.mate.service;

import core.mate.model.DirectoryData;
import core.mate.model.DirectoryWalker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DirectoryWalkerService {

    public List<DirectoryData> traverseDirectory(List<String> directories) {
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
            e.printStackTrace();
            return Collections.emptyList();
        }

        List<DirectoryData> results = new ArrayList<>();
        for (Future<DirectoryData> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                //TODO: gracefully handle this. You are ignoring the result of execution in this case.
                // Need to restart the thread, but for now, we don't know what exact thread cause the exception (Which one 'folder' wasn't parsed)
            }
        }
        executorService.shutdown();
        return results;
    }
}
