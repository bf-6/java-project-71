package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gen-diff",
        mixinStandardHelpOptions = true, // - атрибут добавляет к справке опции -h, --help и -V, --version
        description = "Compares two configuration files and shows a difference.",
        version = "gen-diff 1.0" // - указываем версию для опции -V, --version
)
class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "Soutput format [default: stylish]")
    static String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    static String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    static String filepath2;

    public static void main(String... args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {

        System.out.println(Differ.differ(filepath1, filepath2, format));

        return null;
    }
}
