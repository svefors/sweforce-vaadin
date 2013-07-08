package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.CompositeActivityMapper;
import sweforce.gui.region.Region;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:00 AM
* To change this template use File | Settings | File Templates.
*/
public class RegionModule extends BinderModule {
    private final Region region;

    public RegionModule(Region region) {
        this.region = region;
    }

    @Override
    protected void declare() {
        bind(Name.named(region.toString()), ActivityMapper.class).to(CompositeActivityMapper.class);
    }


}
