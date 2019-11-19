package core.mate.util;

import core.mate.exceptions.ArgumentsParseException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CmdArgsParser {
    private final CommandLine cmd;

    public CmdArgsParser(String[] args) {
        this.cmd = parse(args);
    }

    CommandLine parse(String[] args) throws ArgumentsParseException {
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLine cmd;
        try {
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Please pass correct params", options);

            throw new ArgumentsParseException(e.getMessage());
        }
        return cmd;
    }

    public String getValue(String argParam) {
        return cmd.getOptionValue(argParam);
    }

}
