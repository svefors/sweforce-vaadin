package sweforce.gui.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.event.ResettableEventBus;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceChangeEvent;
import sweforce.gui.place.PlaceChangeRequestEvent;
import sweforce.gui.display.Display;
import sweforce.gui.display.NullView;

/**
 * Unlike GWT, in Vaadin:
 * "In short, the request handing is synchronized per application;
 * if a user sends two requests prefix once to the same application, they're handled one prefix a time. "
 * So this should work...
 */
public class SingleThreadedActivityManager implements PlaceChangeEvent.Handler, PlaceChangeRequestEvent.Handler, ActivityManager {

    private static Logger logger = LoggerFactory.getLogger(SingleThreadedActivityManager.class);

    private final ActivityMapper mapper;

    private final EventBus eventBus;

    /*
    * Note that we use the legacy class from com.google.gwt.event.shared, because
    * we can't change the Activity interface.
    */
    private final ResettableEventBus stopperedEventBus;

    private Activity currentActivity = NULL_ACTIVITY;

    private Display display;

    private HandlerRegistration handlerRegistration;

    public SingleThreadedActivityManager(ActivityMapper mapper, EventBus eventBus) {
        this.mapper = mapper;
        this.eventBus = eventBus;
        this.stopperedEventBus = new ResettableEventBus(eventBus);
    }

    /**
     * Sets the display for the receiver, and has the side effect of starting or
     * stopping its monitoring the event bus for place change events.
     * <p/>
     * If you are disposing of an ActivityManager, it is important to call
     * setDisplay(null) to get it to deregister from the event bus, so that it can
     * be garbage collected.
     *
     * @param display an instance of AcceptsOneWidget
     */
    @Override
    public void setDisplay(Display display) {
        boolean wasActive = (null != this.display);
        boolean willBeActive = (null != display);
        this.display = display;
        if (wasActive != willBeActive) {
            updateHandlers(willBeActive);
        }
    }

    private void updateHandlers(boolean activate) {
        if (activate) {
            final HandlerRegistration placeReg = eventBus.addHandler(PlaceChangeEvent.class, this);
            final HandlerRegistration placeRequestReg =
                    eventBus.addHandler(PlaceChangeRequestEvent.class, this);

            this.handlerRegistration = new HandlerRegistration() {
                public void removeHandler() {
                    placeReg.removeHandler();
                    placeRequestReg.removeHandler();
                }
            };
        } else {
            if (handlerRegistration != null) {
                handlerRegistration.removeHandler();
                handlerRegistration = null;
            }
        }
    }

    @Override
    public void onPlaceChange(PlaceChangeEvent event) {
        Place newPlace = event.getNewPlace();
        Activity nextActivity = mapper.getActivity(newPlace);


        if (currentActivity.equals(nextActivity)) {
            return;
        }

        stopperedEventBus.removeHandlers();

        currentActivity.onStop();

        if(nextActivity == null){
            nextActivity = NULL_ACTIVITY;
        }

        currentActivity = nextActivity;

        logger.debug("starting activity: {}", currentActivity);

        currentActivity.start(display, stopperedEventBus);

    }

    /**
     * Reject the place change if the current activity is not willing to stop.
     *
     * @see PlaceChangeRequestEvent.Handler#onPlaceChangeRequest(PlaceChangeRequestEvent)
     */
    public void onPlaceChangeRequest(PlaceChangeRequestEvent event) {
        event.setWarning(currentActivity.mayStop());
    }

    private static final Activity NULL_ACTIVITY = new AbstractActivity() {
        public void start(Display panel, EventBus eventBus) {
            panel.setView(NullView.getInstance());
        }
    };


}
