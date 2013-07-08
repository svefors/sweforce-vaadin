package sweforce.vaadin.sample.secure.role1;

import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;
import sweforce.gui.region.Region;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.bind.RegionPluginModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/13
 * Time: 2:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Role1Module extends RegionPluginModule {

    public Role1Module() {
        super(Style1Layout.MyRegion.MAIN, Role1Place.class, Role1Activity.MyActivityMapper.class);
    }
}
