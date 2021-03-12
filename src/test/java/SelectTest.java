import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectTest {

    public Logger logger = LoggerFactory.getLogger(SelectTest.class);
    public static RandomAccessFile testFile;

    @TempDir
    public static Path tempDir;

    @BeforeAll()
    public void setUp() {
        try {
            File file = tempDir.resolve("test.dat").toFile();
            testFile = new RandomAccessFile(file, "rws");
            testFile.writeUTF("This cat, that cat, all the cats\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @ArgumentsSource(SelectionArgumentsProvider.class)
    public void selectAllOccurrencesOfTheWordCat(SelectionGroup expected) {
        TextFinder textFinder = new TextFinder(testFile);

        SelectionCriteria criteria = new SelectionCriteria();

        SelectionGroup actual = textFinder.findAll(criteria);

        // how do know that all occurrences were found?
        // the lists of occurrences need to match
        // how do we know that occurrences match?
        // if the value and position of the selection are the same
        Assertions.assertEquals(expected, actual);
    }

}
