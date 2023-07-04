package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
class App implements Runnable {

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "Soutput format [default: stylish]")
    private String format;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean help;

    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    private boolean version;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private File filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private File filepath2;

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

    }
}
