package sweforce.gui.display.region;

import com.vaadin.ui.HasComponents;
import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.gui.activity.SingleThreadedActivityManager;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceController;
import sweforce.gui.place.PlaceHistoryHandler;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.*;

/**
 *
 */

public class RegionalDisplayPresenter {


    private final PlaceController placeController;

    private final PlaceHistoryHandler placeHistoryHandler;

    private HandlerRegistration placeHistoryHandlerHandlerRegistration;

    private final EventBus eventBus;

    private Map<Region, SingleThreadedActivityManager> regionSingleThreadedActivityManagerMap =
            new HashMap<Region, SingleThreadedActivityManager>();

    private final RegionActivityMapperFactory regionActivityMapperFactory;

    private RegionalDisplay display;

    private Provider<Place> defaultPlaceProvider = new Provider<Place>() {
        @Override
        public Place get() {
            return Place.NOWHERE;
        }
    };

    @Inject
    public RegionalDisplayPresenter(EventBus eventBus, PlaceController placeController,
                                    PlaceHistoryHandler placeHistoryHandler,
                                    RegionActivityMapperFactory regionActivityMapperFactory, Provider<Place> defaultPlace) {
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.placeHistoryHandler = placeHistoryHandler;
        this.regionActivityMapperFactory = regionActivityMapperFactory;
        this.defaultPlaceProvider = defaultPlace;

    }



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

        display.addComponentAttachListener(new HasComponents.ComponentAttachListener() {
            @Override
            public void componentAttachedToContainer(HasComponents.ComponentAttachEvent event) {
                //we only want this to happen once
                placeHistoryHandlerHandlerRegistration = placeHistoryHandler.register(placeController, eventBus, defaultPlaceProvider);
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
