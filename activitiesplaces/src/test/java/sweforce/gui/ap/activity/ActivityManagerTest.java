/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package sweforce.gui.ap.activity;


import com.vaadin.ui.Component;
import junit.framework.TestCase;
import org.junit.Test;
import sweforce.gui.ap.UmbrellaException;
import sweforce.gui.ap.event.CountingEventBus;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.PlaceChangeEvent;
import sweforce.gui.ap.place.PlaceChangeRequestEvent;
import sweforce.gui.display.Display;
import sweforce.gui.display.NullView;
import sweforce.gui.display.VaadinView;
import sweforce.gui.display.View;
import sweforce.gui.event.Event;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.EventHandler;

/**
 * Eponymous unit test.
 */
public class ActivityManagerTest extends TestCase {
    private static class AsyncActivity extends SyncActivity {
        AsyncActivity(MyView view) {
            super(view);
        }

        @Override
        public void start(Display display, EventBus eventBus) {
            this.display = display;
            this.bus = eventBus;
        }

        void finish() {
            display.setView(view);
        }
    }

    private static class Event implements sweforce.gui.event.Event<Handler> {
//    private static sweforce.gui.event.Event.Type<EventHandler> TYPE = new sweforce.gui.event.Event.Type<EventHandler>();
//
//    @Override
//    public sweforce.gui.event.Event.Type<Handler> getAssociatedType() {
//      throw new UnsupportedOperationException("Auto-generated method stub");
//    }

        @Override
        public void dispatch(Handler handler) {
            throw new UnsupportedOperationException("Auto-generated method stub");
        }
    }

    private static class Handler implements EventHandler {
    }

    ;

    private static class MyDisplay implements Display {
        View view = null;

        @Override
        public void setView(View view) {
            this.view = view;
        }


    }

    private static class MyPlace extends Place {
    }

    private static class MyView implements VaadinView {
        @Override
        public Component asComponent() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private static class SyncActivity implements Activity {
        boolean canceled = false;
        boolean stopped = false;
        Display display;
        String stopWarning;
        MyView view;
        EventBus bus;

        SyncActivity(MyView view) {
            this.view = view;
        }

        public String mayStop() {
            return stopWarning;
        }

        public void onCancel() {
            canceled = true;
        }

        public void onStop() {
            stopped = true;
        }

        public void start(Display display, EventBus eventBus) {
            this.display = display;
            this.bus = eventBus;
            display.setView(view);
        }
    }

    private final MyPlace place1 = new MyPlace();
    private final MyPlace place2 = new MyPlace();

    private SyncActivity activity1 = new SyncActivity(new MyView());

    private SyncActivity activity2 = new SyncActivity(new MyView());

    private final MyDisplay realDisplay = new MyDisplay();
    private final ActivityMapper myMap = new ActivityMapper() {
        public Activity getActivity(Place place) {
            if (place.equals(place1)) {
                return activity1;
            }
            if (place.equals(place2)) {
                return activity2;
            }

            return null;
        }
    };

    private CountingEventBus eventBus = new CountingEventBus();

    private ActivityManager manager = new ActivityManager(myMap, eventBus);

    @Test
    public void testActiveEventBus() {
        final AsyncActivity asyncActivity1 = new AsyncActivity(new MyView());
        final AsyncActivity asyncActivity2 = new AsyncActivity(new MyView());

        ActivityMapper map = new ActivityMapper() {
            public Activity getActivity(Place place) {
                if (place.equals(place1)) {
                    return asyncActivity1;
                }
                if (place.equals(place2)) {
                    return asyncActivity2;
                }

                return null;
            }
        };

        manager = new ActivityManager(map, eventBus);
        manager.setDisplay(realDisplay);

        eventBus.fireEvent(new PlaceChangeEvent(place1));
        EventBus activeEventBus =
                manager.getActiveEventBus();

        activeEventBus.addHandler(Event.class, new Handler());
        assertEquals(1, eventBus.getHandlerCount(Event.class));

        eventBus.fireEvent(new PlaceChangeEvent(place2));
        assertEquals(0, eventBus.getHandlerCount(Event.class));
    }

    @Test
    public void testAsyncDispatch() {
        final AsyncActivity asyncActivity1 = new AsyncActivity(new MyView());
        final AsyncActivity asyncActivity2 = new AsyncActivity(new MyView());

        ActivityMapper map = new ActivityMapper() {
            public Activity getActivity(Place place) {
                if (place.equals(place1)) {
                    return asyncActivity1;
                }
                if (place.equals(place2)) {
                    return asyncActivity2;
                }

                return null;
            }
        };

        manager = new ActivityManager(map, eventBus);
        manager.setDisplay(realDisplay);

        PlaceChangeRequestEvent event = new PlaceChangeRequestEvent(
                place1);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertNull(realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);
        assertNull(asyncActivity1.display);

        eventBus.fireEvent(new PlaceChangeEvent(place1));
        assertNull(realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);
        assertNotNull(asyncActivity1.display);

        asyncActivity1.finish();
        assertEquals(asyncActivity1.view, realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);

        event = new PlaceChangeRequestEvent(place2);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertEquals(asyncActivity1.view, realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);
        assertFalse(asyncActivity2.stopped);
        assertFalse(asyncActivity2.canceled);
        assertNull(asyncActivity2.display);

        eventBus.fireEvent(new PlaceChangeEvent(place2));
        assertEquals(NullView.getInstance(), realDisplay.view);
        assertFalse(asyncActivity1.canceled);
        assertTrue(asyncActivity1.stopped);
        assertFalse(asyncActivity2.stopped);
        assertFalse(asyncActivity2.canceled);
        assertNotNull(asyncActivity2.display);

        asyncActivity2.finish();
        assertEquals(asyncActivity2.view, realDisplay.view);
    }

    @Test
    public void testCancel() {
        final AsyncActivity asyncActivity1 = new AsyncActivity(new MyView());
        final AsyncActivity ayncActivity2 = new AsyncActivity(new MyView());

        ActivityMapper map = new ActivityMapper() {
            public Activity getActivity(Place place) {
                if (place.equals(place1)) {
                    return asyncActivity1;
                }
                if (place.equals(place2)) {
                    return ayncActivity2;
                }

                return null;
            }
        };

        manager = new ActivityManager(map, eventBus);
        manager.setDisplay(realDisplay);

        PlaceChangeRequestEvent event = new PlaceChangeRequestEvent(
                place1);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertNull(realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);
        assertNull(asyncActivity1.display);

        eventBus.fireEvent(new PlaceChangeEvent(place1));
        assertNull(realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);
        assertNotNull(asyncActivity1.display);

        event = new PlaceChangeRequestEvent(place2);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertNull(realDisplay.view);
        assertFalse(asyncActivity1.stopped);
        assertFalse(asyncActivity1.canceled);

        eventBus.fireEvent(new PlaceChangeEvent(place2));
        assertNull(realDisplay.view);
        assertTrue(asyncActivity1.canceled);
        assertFalse(asyncActivity1.stopped);
        assertFalse(ayncActivity2.stopped);
        assertFalse(ayncActivity2.canceled);
        assertNotNull(ayncActivity2.display);

        ayncActivity2.finish();
        assertEquals(ayncActivity2.view, realDisplay.view);

        asyncActivity1.finish();
        assertEquals(ayncActivity2.view, realDisplay.view);
    }

    @Test
    public void testDropHandlersOnStop() {
        manager.setDisplay(realDisplay);

        assertEquals(0, eventBus.getHandlerCount(Event.class));

        activity1 = new SyncActivity(null) {
            @Override
            public void start(Display panel, EventBus eventBus) {
                super.start(panel, eventBus);
                bus.addHandler(Event.class, new Handler());
            }

            @Override
            public void onStop() {
                super.onStop();
                bus.addHandler(Event.class, new Handler());
            }
        };

        PlaceChangeEvent event = new PlaceChangeEvent(place1);
        eventBus.fireEvent(event);
        assertEquals(1, eventBus.getHandlerCount(Event.class));

        event = new PlaceChangeEvent(place2);
        eventBus.fireEvent(event);
        assertEquals(0, eventBus.getHandlerCount(Event.class));

        // Make sure we didn't nuke the ActivityManager's own handlers
        assertEquals(1, eventBus.getHandlerCount(PlaceChangeEvent.class));
        assertEquals(1, eventBus.getHandlerCount(PlaceChangeRequestEvent.class));
    }

    @Test
    public void testEventSetupAndTeardown() {
        assertEquals(0, eventBus.getHandlerCount(PlaceChangeEvent.class));
        assertEquals(0, eventBus.getHandlerCount(PlaceChangeRequestEvent.class));

        manager.setDisplay(realDisplay);

        assertEquals(1, eventBus.getHandlerCount(PlaceChangeEvent.class));
        assertEquals(1, eventBus.getHandlerCount(PlaceChangeRequestEvent.class));

        manager.setDisplay(null);

        assertEquals(0, eventBus.getHandlerCount(PlaceChangeEvent.class));
        assertEquals(0, eventBus.getHandlerCount(PlaceChangeRequestEvent.class));
    }

    @Test
    public void testExceptionsOnStartAndCancel() {
        activity1 = new AsyncActivity(null) {
            @Override
            public void start(Display panel, EventBus eventBus) {
                super.start(panel, eventBus);
                bus.addHandler(Event.class, new Handler());
            }

            @Override
            public void onCancel() {
                super.onCancel();
                bus.addHandler(Event.class, new Handler());
                throw new UnsupportedOperationException("Exception on cancel");
            }
        };

        activity2 = new SyncActivity(null) {
            @Override
            public void start(Display panel, EventBus eventBus) {
                super.start(panel, eventBus);
                throw new UnsupportedOperationException("Exception on start");
            }
        };

        manager.setDisplay(realDisplay);

        try {
            PlaceChangeEvent event = new PlaceChangeEvent(place1);
            eventBus.fireEvent(event);
            assertEquals(1, eventBus.getHandlerCount(Event.class));

            event = new PlaceChangeEvent(place2);
            eventBus.fireEvent(event);

            fail("Expected exception");
        } catch (UmbrellaException e) {
            // EventBus throws this one
            assertEquals(2, e.getCauses().size());
            // And this is the one thrown by ActivityManager
//            UmbrellaException nested = (UmbrellaException) e.getCause();
//            assertEquals(2, nested.getCauses().size());
        }

        assertTrue(activity1.canceled);
        assertNotNull(activity2.display);
        assertEquals(0, eventBus.getHandlerCount(Event.class));
    }

    @Test
    public void testExceptionsOnStopAndStart() {
        activity1 = new SyncActivity(null) {
            @Override
            public void start(Display panel, EventBus eventBus) {
                super.start(panel, eventBus);
                bus.addHandler(Event.class, new Handler());
            }

            @Override
            public void onStop() {
                super.onStop();
                bus.addHandler(Event.class, new Handler());
                throw new UnsupportedOperationException("Exception on stop");
            }
        };

        activity2 = new SyncActivity(null) {
            @Override
            public void start(Display panel, EventBus eventBus) {
                super.start(panel, eventBus);
                throw new UnsupportedOperationException("Exception on start");
            }
        };

        manager.setDisplay(realDisplay);

        try {
            PlaceChangeEvent event = new PlaceChangeEvent(place1);
            eventBus.fireEvent(event);
            assertEquals(1, eventBus.getHandlerCount(Event.class));

            event = new PlaceChangeEvent(place2);
            eventBus.fireEvent(event);

            fail("Expected exception");
        } catch (UmbrellaException e) {
            // EventBus throws this one
            assertEquals(2, e.getCauses().size());
//            // And this is the one thrown by ActivityManager
//            UmbrellaException nested = (UmbrellaException) e.getCause();
//            assertEquals(2, nested.getCauses().size());
        }

        assertTrue(activity1.stopped);
        assertNotNull(activity2.display);
        assertEquals(0, eventBus.getHandlerCount(Event.class));
    }

    /**
     * see http://code.google.com/p/google-web-toolkit/issues/detail?id=5375
     */
    @Test
    public void testNullDisplayOnPlaceChange() {
        manager.setDisplay(realDisplay);

        // Start an activity
        manager.onPlaceChange(new PlaceChangeEvent(place1));

        /*
        * Now we're going to place2. During PlaceChangeEvent dispatch,
        * someone kills the manager's display.
        */
        manager.setDisplay(null);

        // Now the place change event reaches the manager
        manager.onPlaceChange(new PlaceChangeEvent(place2));

        assertNull(activity2.display);
        assertTrue(activity1.stopped);
    }

    @Test
    public void testNullDisplayBeforeAsyncStart() {
        final AsyncActivity asyncActivity1 = new AsyncActivity(new MyView());
        final AsyncActivity asyncActivity2 = new AsyncActivity(new MyView());

        ActivityMapper map = new ActivityMapper() {
            public Activity getActivity(Place place) {
                if (place.equals(place1)) {
                    return asyncActivity1;
                }
                if (place.equals(place2)) {
                    return asyncActivity2;
                }

                return null;
            }
        };

        manager = new ActivityManager(map, eventBus);
        manager.setDisplay(realDisplay);

        // Start an activity
        manager.onPlaceChange(new PlaceChangeEvent(place1));

        // Kill the manager
        manager.setDisplay(null);

        // The activity is ready to play
        asyncActivity1.finish();

        // Ta da, no NPE
    }

    @Test
    public void testRejected() {
        manager.setDisplay(realDisplay);

        activity1.stopWarning = "Stop fool!";

        PlaceChangeRequestEvent event = new PlaceChangeRequestEvent(
                place1);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertNull(realDisplay.view);

        eventBus.fireEvent(new PlaceChangeEvent(place1));
        assertEquals(activity1.view, realDisplay.view);

        event = new PlaceChangeRequestEvent(place2);
        eventBus.fireEvent(event);
        assertEquals(activity1.stopWarning, event.getWarning());
        assertEquals(activity1.view, realDisplay.view);
        assertFalse(activity1.stopped);
        assertFalse(activity1.canceled);
    }

    @Test
    public void testSyncDispatch() {
        manager.setDisplay(realDisplay);

        PlaceChangeRequestEvent event = new PlaceChangeRequestEvent(
                place1);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertNull(realDisplay.view);
        assertFalse(activity1.stopped);
        assertFalse(activity1.canceled);

        eventBus.fireEvent(new PlaceChangeEvent(place1));
        assertEquals(activity1.view, realDisplay.view);
        assertFalse(activity1.stopped);
        assertFalse(activity1.canceled);

        event = new PlaceChangeRequestEvent(place2);
        eventBus.fireEvent(event);
        assertNull(event.getWarning());
        assertEquals(activity1.view, realDisplay.view);
        assertFalse(activity1.stopped);
        assertFalse(activity1.canceled);

        eventBus.fireEvent(new PlaceChangeEvent(place2));
        assertEquals(activity2.view, realDisplay.view);
        assertTrue(activity1.stopped);
        assertFalse(activity1.canceled);
    }

    /**
     * Non-regression test: make sure an activity can call {@link com.google.gwt.user.client.ui.AcceptsOneWidget#setWidget(com.google.gwt.user.client.ui.IsWidget)} several times to switch views.
     */
    @Test
    public void testSetWidgetSeveralTimesPerActivity() {
        class TwoViewActivity extends SyncActivity {
            MyView view2;

            public TwoViewActivity(MyView view1, MyView view2) {
                super(view1);
                this.view2 = view2;
            }

            void secondView() {
                display.setView(view2);
            }

            void firstView() {
                display.setView(view);
            }
        }
        final TwoViewActivity activity = new TwoViewActivity(new MyView(), new MyView());

        ActivityMapper map = new ActivityMapper() {
            public Activity getActivity(Place place) {
                return activity;
            }
        };

        manager = new ActivityManager(map, eventBus);
        manager.setDisplay(realDisplay);

        // Start an activity
        manager.onPlaceChange(new PlaceChangeEvent(place1));

        assertEquals(activity.view, realDisplay.view);

        // Call setWidget on the display several times, just to make sure it's possible
        activity.secondView();
        assertEquals(activity.view2, realDisplay.view);

        activity.firstView();
        assertEquals(activity.view, realDisplay.view);

        activity.secondView();
        assertEquals(activity.view2, realDisplay.view);
    }
}
