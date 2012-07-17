package sweforce.gui.ap.place;

import org.junit.Test;
import sweforce.gui.ap.place.controller.DefaultPlaceController;
import sweforce.gui.ap.place.controller.PlaceController;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.SimpleEventBus;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceControllerTest {

    private final class Canceler implements
            PlaceChangeRequestEvent.Handler {
        Place calledWith = null;
        String warning = "Stop fool!";

        public void onPlaceChangeRequest(PlaceChangeRequestEvent event) {
            calledWith = event.getNewPlace();
            event.setWarning(warning);
        }
    }

    private static class MyPlace extends Place {
    }

    private class SimpleHandler implements PlaceChangeEvent.Handler {
        MyPlace calledWith = null;

        public void onPlaceChange(PlaceChangeEvent event) {
            calledWith = (MyPlace) event.getNewPlace();
        }
    }

//    private Logger deadLogger = new Logger("shut up", null) {
//    };

    private EventBus eventBus = new SimpleEventBus();
    private MockConfirmationHandler delegate = new MockConfirmationHandler();
    private PlaceController placeController = new DefaultPlaceController(eventBus, delegate) ;

    @Test
    public void testConfirmCancelOnUserNav() {
        SimpleHandler handler = new SimpleHandler();
        eventBus.addHandler(PlaceChangeEvent.class, handler);

        Canceler canceler = new Canceler();
        eventBus.addHandler(PlaceChangeRequestEvent.class, canceler);

        MyPlace place = new MyPlace();

        placeController.goTo(place);
        assertNull(handler.calledWith);
        assertEquals(place, canceler.calledWith);
        assertEquals(canceler.warning, delegate.message);

        delegate.confirm = true;

        placeController.goTo(place);
        assertEquals(place, canceler.calledWith);
    }

//    public void testConfirmCancelOnWindowClose() {
//        SimpleHandler handler = new SimpleHandler();
//        eventBus.addHandler(PlaceChangeEvent.class, handler);
//
//        Canceler canceler = new Canceler();
//        eventBus.addHandler(PlaceChangeRequestEvent.class, canceler);
//
//        assertNull(handler.calledWith);
//        assertNull(delegate.message);
////        delegate.close();
//        assertEquals(canceler.warning, delegate.message);
//        assertNull(handler.calledWith);
//    }

    @Test
    public void testSimple() {
        SimpleHandler handler = new SimpleHandler();
        eventBus.addHandler(PlaceChangeEvent.class, handler);
        MyPlace place1 = new MyPlace();
        MyPlace place2 = new MyPlace();
        placeController.goTo(place1);
        assertEquals(place1, handler.calledWith);
        placeController.goTo(place2);
        assertEquals(place2, handler.calledWith);
    }

}
