package sweforce.gui.region;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityManager;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.display.Display;
import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegionActivityManager implements ActivityManager {

    private final Region region;

    private final ActivityManager activityManager;

    public RegionActivityManager(Region region, ActivityManager activityManager) {
        this.region = region;
        this.activityManager = activityManager;
    }

    @Override
    public void setDisplay(Display display) {
        activityManager.setDisplay(display);
    }
}
