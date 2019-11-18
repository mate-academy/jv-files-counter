package core.basesyntax;

import core.basesyntax.model.DirectoryData;
import core.basesyntax.service.FileReaderService;
import core.basesyntax.service.WriterService;
import core.basesyntax.util.CsvUtil;
import core.basesyntax.util.DisplayResult;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Invalid input params. Expecting to receive: "
                    + "source_directories.txt result_file.txt (where the result will be written)");
            return;
        }

        String input = args[0];
        String output = args[1];

        if (!new File(input).exists()) {
            System.err.println("Invalid input params. Can't find file " + input);
            return;
        }

        List<String> folderPaths = FileReaderService.parseFile(input);
        WriterService.clearFile(output);
        List<DirectoryData> foldersData = WriterService.traverseDirectory(folderPaths);

        List<StringBuilder> dataRows = foldersData
                .stream()
                .map(CsvUtil::getRow)
                .collect(Collectors.toList());
        StringBuilder response = CsvUtil.getHeader();
        dataRows.forEach(response::append);
        WriterService.writeToFile(response.toString(), output);

        DisplayResult.showResults(foldersData);

    }
}
