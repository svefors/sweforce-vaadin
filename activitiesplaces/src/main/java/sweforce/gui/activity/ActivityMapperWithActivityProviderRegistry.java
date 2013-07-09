package sweforce.gui.activity;

import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 10:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityMapperWithActivityProviderRegistry implements ActivityMapper {

    private final ActivityProviderRegistry activityProviderRegistry;

    public ActivityMapperWithActivityProviderRegistry(ActivityProviderRegistry activityProviderRegistry) {
        this.activityProviderRegistry = activityProviderRegistry;
    }

    @Override
    public Activity getActivity(Place place) {
        ActivityProvider<?> activityProvider = activityProviderRegistry.findProvider(place);
        if(activityProvider != null)
            return activityProvider.provide();
        return null;
    }
    /*
    bind(named, ActivityMapper.class).to(ActivityMapperWithActivityProviderRegistry.class)
    bind(named, ActivityProviderRegistry.class).to(ActivityProviderRegistry.Impl.class)

     */

    @Override
    public String toString() {
        return "ActivityMapperWithActivityProviderRegistry{" +
                "activityProviderRegistry=" + activityProviderRegistry +
                '}';
    }
}
