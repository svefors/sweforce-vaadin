package sweforce.gui.ap.place;

import com.google.gwt.place.shared.*;
import com.google.gwt.place.shared.Place;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceChangeRequestEventTest {

    private static final String W1 = "foo";

    @Test
    public void testNoClobberWarning() {
        com.google.gwt.place.shared.PlaceChangeRequestEvent e = new com.google.gwt.place.shared.PlaceChangeRequestEvent(new Place() {
        });

        assertNull(e.getWarning());
        e.setWarning(W1);
        assertEquals(W1, e.getWarning());
        e.setWarning("bar");
        assertEquals(W1, e.getWarning());
        e.setWarning(null);
        assertEquals(W1, e.getWarning());
    }
}
