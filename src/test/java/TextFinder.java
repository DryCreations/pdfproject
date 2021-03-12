import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class TextFinder {

    private RandomAccessFile _file;

    public TextFinder(RandomAccessFile file) {

    }

    public SelectionGroup findAll(SelectionCriteria criteria) {
        return new SelectionGroup();
    }
}
