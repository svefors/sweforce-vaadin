package sweforce.gui.ap.activity;

import org.junit.Test;
import sweforce.event.EventBus;
import sweforce.gui.display.Display;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractActivityTest {

    @Test
    public void mayStopShouldReturnNullByDefault(){
        Activity activity = new AbstractActivity() {
            @Override
            public void start(Display panel, EventBus eventBus) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        assertNotNull("May stop should be null by default", activity.mayStop());
    }
}
