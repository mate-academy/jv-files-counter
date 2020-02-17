package core.mate.util;

import core.mate.model.DirectoryData;

public class CsvUtil {

    public static StringBuilder getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Path");
        sb.append(',');
        sb.append("Quantity");
        sb.append('\n');
        return sb;
    }

    public static StringBuilder getRow(DirectoryData data) {
        StringBuilder sb = new StringBuilder("");
        sb.append(data.getDirectory());
        sb.append(',');
        sb.append(data.getData().size());
        sb.append('\n');
        return sb;
    }
}
