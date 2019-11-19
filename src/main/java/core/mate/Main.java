package core.mate;

import core.mate.model.DirectoryData;
import core.mate.service.DirectoryWalkerService;
import core.mate.service.FileReaderService;
import core.mate.service.WriterService;
import core.mate.util.CmdArgsParser;
import core.mate.util.CsvUtil;
import core.mate.util.DisplayResult;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        CmdArgsParser argsParser = new CmdArgsParser(args);
        String input = argsParser.getValue("input");
        String output = argsParser.getValue("input");

        FileReaderService fileReaderService = new FileReaderService();
        List<String> folderPaths = fileReaderService.parseFile(input);

        DirectoryWalkerService directoryWalkerService = new DirectoryWalkerService();
        List<DirectoryData> foldersData = directoryWalkerService.traverseDirectory(folderPaths);

        List<StringBuilder> dataRows = foldersData
                .stream()
                .map(CsvUtil::getRow)
                .collect(Collectors.toList());
        StringBuilder response = CsvUtil.getHeader();
        dataRows.forEach(response::append);

        WriterService writerService = new WriterService();
        writerService.clearFile(output);
        writerService.writeToFile(response.toString(), output);

        DisplayResult.showResults(foldersData);
    }
}
