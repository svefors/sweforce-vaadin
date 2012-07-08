package sweforce.gui.ap.place;

import sweforce.gui.ap.place.controller.PlaceController;
import sweforce.gui.ap.place.history.PlaceHistoryHandler;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.HandlerRegistration;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Convenience class
 */

public class PlacesRunner {

    private final Place defaultPlace;

    private final PlaceController placeController;

    private final PlaceHistoryHandler placeHistoryHandler;

    private HandlerRegistration placeHistoryHandlerHandlerRegistration;

    private final EventBus eventBus;

    @Inject
    public PlacesRunner(PlaceHistoryHandler placeHistoryHandler, PlaceController placeController, @Named("Default Place") Place defaultPlace, EventBus eventBus) {
        this.placeHistoryHandler = placeHistoryHandler;
        this.defaultPlace = defaultPlace;
        this.placeController = placeController;
        this.eventBus = eventBus;
    }

    public void start() {
        placeHistoryHandlerHandlerRegistration = placeHistoryHandler.register(placeController, eventBus, defaultPlace);
        placeHistoryHandler.handleCurrentFragment();
    }

    /**
     * TODO figure out if this is a good idea
     */
    public void end(){
        placeHistoryHandlerHandlerRegistration.removeHandler();
    }

}
