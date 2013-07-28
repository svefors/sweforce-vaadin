package sweforce.gui.activity.registry;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.Activity;
import sweforce.gui.activity.PlaceMatch;
import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/13/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityRegistryPluginSilkModule extends BinderModule {

    private final Name name;


    public ActivityRegistryPluginSilkModule(Name name) {
        this.name = name;
    }

    @Override
    protected void declare() {

    }

    public static class BinderConfiguration implements
            ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration> {

        @Override
        public UseActivityFactory<ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration>> match(final Place place) {
            return new UseActivityFactory<ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration>>() {
                @Override
                public ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration> use(ActivityFactory activityFactory) {

                    return null;  //To change body of implemented methods use File | Settings | File Templates.
                }

                @Override
                public ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration> use(Activity activity) {
                    return null;  //To change body of implemented methods use File | Settings | File Templates.
                }
            };
        }

        @Override
        public UseActivityFactory<ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration>> match(PlaceMatch placeMatch) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public UseActivityFactory<ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration>> match(Class<? extends Place> placeClass) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }


}
