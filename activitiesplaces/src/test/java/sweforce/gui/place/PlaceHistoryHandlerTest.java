package sweforce.gui.place;

import org.junit.Before;
import org.junit.Test;
import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.gui.place.testplaces.Place1;
import sweforce.gui.place.testplaces.Place2;
import sweforce.event.SimpleEventBus;

import javax.inject.Provider;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryHandlerTest {

    private static class MockHistorian implements
            PlaceHistoryHandler.Historian {
        final HandlerRegistration registration = new Registration();

        PlaceHistoryHandler.HistoryChangedEvent.Handler handler;
        String token = "";

        public HandlerRegistration addValueChangeHandler(
                PlaceHistoryHandler.HistoryChangedEvent.Handler valueChangeHandler) {
            this.handler = valueChangeHandler;
            return registration;
        }

        public String getToken() {
            return token;
        }

        @Override
        public void newItem(String historyToken) {
            this.token = historyToken;
        }

//        public void newItem(String token, boolean issueEvent) {
//            assertFalse(issueEvent);
//            this.token = token;
//        }

        public void postToken(String string) {
            handler.onHistoryChange(new PlaceHistoryHandler.HistoryChangedEvent(string) {
            });
        }
    }

    private static class MockPlaceHistoryMapper implements PlaceHistoryMapper {

        public Place getPlace(String token) {
            if (TOKEN1.equals(token)) {
                return PLACE1;
            }
            if (TOKEN2.equals(token)) {
                return PLACE2;
            }

            return null;
        }

        public String getToken(Place place) {
            if (place == PLACE1) {
                return TOKEN1;
            }
            if (place == PLACE2) {
                return TOKEN2;
            }

            return null;
        }
    }

    private static class Registration implements HandlerRegistration {
        public void removeHandler() {
            throw new UnsupportedOperationException("Auto-generated method stub");
        }
    }

    private class Subject extends PlaceHistoryHandler {

        Subject(PlaceHistoryMapper mapper, Historian historian) {
            super(mapper, historian);
        }

//        @Override
//        Logger log() {
//            return deadLogger;
//        }
    }

    private static final String TOKEN1 = "t1";

    private static final String TOKEN2 = "token2";

    private static final Place1 PLACE1 = new Place1("able");

    private static final Place2 PLACE2 = new Place2("baker");

//    Logger deadLogger = new Logger("shut up", null) {
//    };

    PlaceController placeController;

    MockHistorian historian;

    Subject subject;

    final Place defaultPlace = new Place() {
    };

    @Test
    public void testEmptyToken() {
        historian.postToken("");
        assertEquals(defaultPlace, placeController.getWhere());
    }

    @Test
    public void testGoToDefaultPlace() {
        placeController.goTo(defaultPlace);
        assertEquals("", historian.token);
    }

    @Test
    public void testPlaceChange() {
        placeController.goTo(PLACE1);
        assertEquals(TOKEN1, historian.token);
        placeController.goTo(PLACE2);
        assertEquals(TOKEN2, historian.token);
    }

    @Test
    public void testProperToken() {
        historian.postToken(TOKEN1);
        assertEquals(PLACE1, placeController.getWhere());

        historian.postToken(TOKEN2);
        assertEquals(PLACE2, placeController.getWhere());
    }

    @Test
    public void testUnknownToken() {
        historian.postToken("abcdefghijklmnop");
        assertEquals(defaultPlace, placeController.getWhere());
    }

    @Before
    public void setUp() {
        EventBus eventBus = new SimpleEventBus();
        historian = new MockHistorian();
        placeController = new PlaceControllerImpl(eventBus,
                new MockConfirmationHandler());
        subject = new Subject(new MockPlaceHistoryMapper(), historian);
//        subject.register(placeController, eventBus, new Provider<Place>() {
//            @Override
//            public Place get() {
//                return defaultPlace;
//            }
//        });
    }

    ;
}
