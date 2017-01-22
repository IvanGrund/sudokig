package sk.valinor.sudokig.app;

import org.apache.commons.cli.*;
import sk.valinor.sudokig.api.domain.Playground;
import sk.valinor.sudokig.app.util.SudokigUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zaq on 29.12.2016.
 */
public final class SudokigApp {

    private static Logger logger = Logger.getLogger(SudokigApp.class.getName());
    private static Options options = new Options();
    private static CommandLineParser parser = new DefaultParser();
    private static HelpFormatter formatter = new HelpFormatter();

    static {
        options.addOption("f", "file", false, "File to process. Use '-' for stdin.");
        options.addOption("a", "algorithm", false, "Choose one of available algorithms: simple, own, jsr331");
        options.addOption("s", "Same as -a simple");
        options.addOption("o", "Same as -a own");
        options.addOption("j", "Same as -a jsr133");
        options.addOption("h", "help");

    }

    private static void printHelp() {
        formatter.printHelp(
                "sudokig",
                "Solve sudoku puzzle with initial playground setup in an input file.\n\n",
                options,
                "\nCreated by Ivan Grund [https://github.com/IvanGrund]",
                true);
    }

    public static void main(String[] args) {
        try {
            CommandLine cmd = parser.parse( options, args );

            if (cmd.hasOption('h') || (cmd.getArgList().size() == 0 && !cmd.hasOption("f")))
                printHelp();
            Playground unsolvedPlayground = SudokigUtils.parsePlayground(args);
//            Playground solvedPlayground = SudokigSolver.solveSudoku();
        } catch (ParseException e) {
            logger.log(Level.SEVERE,e.getMessage(),e);
            return;
        }
    }
}

