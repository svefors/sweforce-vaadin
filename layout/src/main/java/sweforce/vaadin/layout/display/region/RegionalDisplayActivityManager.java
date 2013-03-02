package sweforce.vaadin.layout.display.region;

import com.vaadin.server.ClientConnector;
import com.vaadin.ui.HasComponents;
import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.gui.ap.activity.ActivityManagerFactory;
import sweforce.gui.ap.activity.ActivityMapper;
import sweforce.gui.ap.activity.SingleThreadedActivityManager;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.controller.PlaceController;
import sweforce.gui.ap.place.history.PlaceHistoryHandler;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class RegionalDisplayActivityManager {

    private final Place defaultPlace;

    private final PlaceController placeController;

    private final PlaceHistoryHandler placeHistoryHandler;

    private HandlerRegistration placeHistoryHandlerHandlerRegistration;

    private final EventBus eventBus;

    private Map<Region, SingleThreadedActivityManager> regionSingleThreadedActivityManagerMap =
            new HashMap<Region, SingleThreadedActivityManager>();

    @Inject
    public RegionalDisplayActivityManager(EventBus eventBus, PlaceController placeController,
                                          PlaceHistoryHandler placeHistoryHandler, Place defaultPlace,
                                          ActivityManagerFactory activityManagerFactory,
                                          Map<Region, ActivityMapper> regionActivityMapperMap) {
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.placeHistoryHandler = placeHistoryHandler;
        this.defaultPlace = defaultPlace;
        for (Map.Entry<Region, ActivityMapper> entry : regionActivityMapperMap.entrySet()){
            regionSingleThreadedActivityManagerMap.put(entry.getKey(),
                    activityManagerFactory.createActivityManager(entry.getValue()));
        }

    }

    public void removeRegionalDisplay(final RegionalDisplay display){
        onDetached(display);
        AttachDetachListener listener = new AttachDetachListener(display);
        display.removeComponentAttachListener(listener);
        display.removeComponentDetachListener(listener);
    }


    private class AttachDetachListener implements HasComponents.ComponentAttachListener, HasComponents.ComponentDetachListener{
        private final RegionalDisplay regionalDisplay;

        private AttachDetachListener(RegionalDisplay regionalDisplay) {
            this.regionalDisplay = regionalDisplay;
        }

        @Override
        public void componentAttachedToContainer(HasComponents.ComponentAttachEvent event) {
            onAttached(regionalDisplay);
        }

        @Override
        public void componentDetachedFromContainer(HasComponents.ComponentDetachEvent event) {
            onDetached(regionalDisplay);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AttachDetachListener that = (AttachDetachListener) o;

            if (!regionalDisplay.equals(that.regionalDisplay)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return regionalDisplay.hashCode();
        }
    }





    public void addRegionalDisplay(final RegionalDisplay display){
        AttachDetachListener listener = new AttachDetachListener(display);
        display.addComponentAttachListener(listener);
        display.addComponentDetachListener(listener);
    }


    /**
     * @param regionalDisplay
     */
    protected void onAttached(RegionalDisplay regionalDisplay) {
        //find all the activityManager and set the display

        //what is the life cycle of the activity manager?
        //it should not be destroyed.
        //it might be that the layout is changing.
        for (Region region : regionalDisplay.getRegions()){
            regionSingleThreadedActivityManagerMap.get(region).setDisplay(regionalDisplay.getDisplay(region));
        }
        placeHistoryHandlerHandlerRegistration = placeHistoryHandler.register(placeController, eventBus, defaultPlace);
        placeHistoryHandler.handleCurrentFragment();
    }

    protected void onDetached(RegionalDisplay regionalDisplay) {
        for (Region region : regionalDisplay.getRegions()){
            regionSingleThreadedActivityManagerMap.get(region).setDisplay(null);
        }
        placeHistoryHandlerHandlerRegistration.removeHandler();
    }

}
