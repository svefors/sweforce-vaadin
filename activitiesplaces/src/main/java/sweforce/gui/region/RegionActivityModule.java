package sweforce.gui.region;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.ActivityManager;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.SingleThreadedActivityManager;
import sweforce.gui.activity.registry.ActivityMapperWithRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegionActivityModule extends BinderModule{

    private final Region region;

    public RegionActivityModule(Region region) {
        this.region = region;
    }

    @Override
    protected void declare() {
        bind(Name.named(region.toString()), ActivityManager.class).to(SingleThreadedActivityManager.class);
        bind(Name.named(region.toString()), ActivityMapper.class).to(ActivityMapperWithRegistry.class);
    }
}
