import com.groupseven.pdfproject.utilities.DrawingMode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Charles Witherspoon
 *
 * \brief This class tests the DrawingMode class methods
 */
public class DrawingModeTest {

    @Test
    public void penGetName() {
        assertEquals("Pen", DrawingMode.PEN.getName());
    }
}
