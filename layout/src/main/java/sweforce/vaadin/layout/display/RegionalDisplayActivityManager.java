package sweforce.vaadin.layout.display;

import sweforce.event.EventBus;
import sweforce.gui.ap.activity.ActivityManager;
import sweforce.gui.ap.activity.ActivityMapper;
import sweforce.vaadin.layout.display.region.Region;
import sweforce.vaadin.layout.display.region.RegionalDisplay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/16/13
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegionalDisplayActivityManager {

    private final RegionalDisplay compositeDisplay;

    private final Map<Region, ActivityManager> regionActivityManagers = new HashMap<Region, ActivityManager>();


    public RegionalDisplayActivityManager(RegionalDisplay compositeDisplay) {
        this.compositeDisplay = compositeDisplay;
    }

    public void registerActivityMapper(EventBus eventBus, Region region, ActivityMapper activityMapper){
        if(regionActivityManagers.containsKey(region)){
            //deregisters the current activity manager from the event bus
            regionActivityManagers.get(region).setDisplay(null);
        }
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(compositeDisplay.getDisplay(region));
        regionActivityManagers.put(region, activityManager);
    }

}
