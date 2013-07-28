package sweforce.gui.activity.registry;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.registry.ActivityFactory;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 10:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityMapperWithRegistry implements ActivityMapper {

    private final ActivityFactoryRegistry activityFactoryRegistry;

    public ActivityMapperWithRegistry(ActivityFactoryRegistry activityFactoryRegistry) {
        this.activityFactoryRegistry = activityFactoryRegistry;
    }

    @Override
    public Activity getActivity(Place place) {
        return activityFactoryRegistry.findActivityFactory(place).create(place);
    }
    /*
    bindPrefixMapping(named, ActivityMapper.class).to(ActivityMapperWithActivityProviderRegistry.class)
    bindPrefixMapping(named, ActivityProviderRegistry.class).to(ActivityProviderRegistry.Impl.class)

     */

    @Override
    public String toString() {
        return "ActivityMapperWithActivityProviderRegistry{" +
                "activityProviderRegistry=" + activityFactoryRegistry +
                '}';
    }
}
