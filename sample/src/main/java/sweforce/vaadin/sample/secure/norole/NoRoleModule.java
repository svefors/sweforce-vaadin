package sweforce.vaadin.sample.secure.norole;

import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.bind.RegionPluginModule;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:00 AM
* To change this template use File | Settings | File Templates.
*/
public class NoRoleModule extends RegionPluginModule {
    public NoRoleModule() {
        super(Style1Layout.MyRegion.MAIN, NorolePlace.class, NoroleActivity.MyActivityMapper.class);
    }
}
