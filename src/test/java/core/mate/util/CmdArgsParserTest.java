package core.mate.util;

import core.mate.exceptions.ArgumentsParseException;
import org.junit.Test;

public class CmdArgsParserTest {
    private CmdArgsParser cmdArgsParser;

    @Test(expected = ArgumentsParseException.class)
    public void parseArgsWithException() {
        String[] args = {"input.txt", "output.txt"};
        cmdArgsParser = new CmdArgsParser(args);
        cmdArgsParser.parse(args);
    }

    @Test
    public void parseArgsOk() {
        String[] args = {"--input=input.txt", "--output=output.txt"};
        cmdArgsParser = new CmdArgsParser(args);
        cmdArgsParser.parse(args);
    }
}
