package sweforce.vaadin.sample.secure.menu;

import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;
import sweforce.gui.region.Region;
import sweforce.vaadin.sample.secure.bind.RegionPluginModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/13
 * Time: 2:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuModule extends RegionPluginModule {

    public MenuModule() {
        super(region, placeClass, activityMapper);
    }
}
