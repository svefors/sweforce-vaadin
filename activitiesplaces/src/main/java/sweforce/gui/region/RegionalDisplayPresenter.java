package sweforce.gui.region;

import com.vaadin.ui.HasComponents;
import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.gui.activity.SingleThreadedActivityManager;
import sweforce.gui.place.PlaceController;
import sweforce.gui.place.PlaceHistoryHandler;

import javax.inject.Inject;
import java.util.*;

/**
 * Revisit this class later. Is it really a Presenter if there is no Model?
 */

public class RegionalDisplayPresenter {


    private final PlaceController placeController;

    private final PlaceHistoryHandler placeHistoryHandler;

    private HandlerRegistration placeHistoryHandlerHandlerRegistration;

    private final EventBus eventBus;

    private Map<Region, SingleThreadedActivityManager> regionSingleThreadedActivityManagerMap =
            new HashMap<Region, SingleThreadedActivityManager>();


    private RegionalDisplay display;

    public RegionalDisplayPresenter(EventBus eventBus, PlaceController placeController,
                                    PlaceHistoryHandler placeHistoryHandler) {
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.placeHistoryHandler = placeHistoryHandler;

    }

    /*
    x
     */

    /**
     * @param display
     */
    public void bindRegionalDisplay(final RegionalDisplay display) {
        if (display == this.display)
            return;
        for (Region region : this.display.getRegions()) {
            regionSingleThreadedActivityManagerMap.get(region).setDisplay(display.getDisplay(region));
            regionSingleThreadedActivityManagerMap.remove(region);
        }
        for (Region region : display.getRegions() == null ? new HashSet<Region>() : display.getRegions()) {
            SingleThreadedActivityManager activityManager =
                    new SingleThreadedActivityManager(regionActivityMapperFactory.create(region), eventBus);
            activityManager.setDisplay(display.getDisplay(region));
            regionSingleThreadedActivityManagerMap.put(region, activityManager);
        }
        //this doesn;t feel right
        display.addComponentAttachListener(new HasComponents.ComponentAttachListener() {
            @Override
            public void componentAttachedToContainer(HasComponents.ComponentAttachEvent event) {
                //we only want this to happen once
                placeHistoryHandlerHandlerRegistration = placeHistoryHandler.register(placeController, eventBus);
                placeHistoryHandler.handleCurrentFragment();
            }
        });
        display.addComponentDetachListener(new HasComponents.ComponentDetachListener() {
            @Override
            public void componentDetachedFromContainer(HasComponents.ComponentDetachEvent event) {
                placeHistoryHandlerHandlerRegistration.removeHandler();
                placeHistoryHandlerHandlerRegistration = null;
            }
        });
        this.display = display;

    }




}
