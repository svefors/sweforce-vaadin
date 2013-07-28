package sweforce.gui.activity.registry;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.PlaceMatch;
import sweforce.gui.place.Place;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ActivityFactoryRegistryImpl implements ActivityFactoryRegistry,
        ActivityFactoryRegistry.Configuration<ActivityFactoryRegistryImpl>, ActivityMapper,
        ActivityFactoryRegistryWithConfiguration<ActivityFactoryRegistryImpl> {

    private Map<PlaceMatch, ActivityFactory> placeMatchActivityFactoryMap =
            new HashMap<PlaceMatch, ActivityFactory>();

    public ActivityFactoryRegistryImpl(Plugin[] plugins) {
        for (Plugin plugin : plugins) {
            plugin.configure(this);
        }
    }

    public ActivityFactoryRegistryImpl(Set<Plugin> plugins) {
        for (Plugin plugin : plugins) {
            plugin.configure(this);
        }
    }

    public ActivityFactoryRegistryImpl() {
    }

    @Override
    public ActivityFactory findActivityFactory(Place place) {
        for (Map.Entry<PlaceMatch, ActivityFactory> entry : placeMatchActivityFactoryMap.entrySet()) {
            if (entry.getKey().matches(place))
                return entry.getValue();
        }
        return new ActivityFactory.NullActivityFactory();
    }


    @Override
    public UseActivityFactory<Configuration<ActivityFactoryRegistryImpl>> match(final PlaceMatch placeMatch) {
        return new UseActivityFactory<Configuration<ActivityFactoryRegistryImpl>>() {
            @Override
            public Configuration<ActivityFactoryRegistryImpl> use(ActivityFactory activityFactory) {
                ActivityFactoryRegistryImpl.this.placeMatchActivityFactoryMap.put(placeMatch, activityFactory);
                return ActivityFactoryRegistryImpl.this;
            }

            @Override
            public Configuration<ActivityFactoryRegistryImpl> use(Activity activity) {
                ActivityFactoryRegistryImpl.this.placeMatchActivityFactoryMap.put(placeMatch, new ActivityFactory.FromInstance(activity));
                return ActivityFactoryRegistryImpl.this;
            }
        };
    }

    @Override
    public UseActivityFactory<Configuration<ActivityFactoryRegistryImpl>> match(final Place place) {
        return new UseActivityFactory<Configuration<ActivityFactoryRegistryImpl>>() {
            @Override
            public Configuration<ActivityFactoryRegistryImpl> use(ActivityFactory activityFactory) {
                ActivityFactoryRegistryImpl.this.placeMatchActivityFactoryMap.put(PlaceMatch.eq(place), activityFactory);
                return ActivityFactoryRegistryImpl.this;
            }

            @Override
            public Configuration<ActivityFactoryRegistryImpl> use(Activity activity) {
                ActivityFactoryRegistryImpl.this.placeMatchActivityFactoryMap.put(PlaceMatch.eq(place), new ActivityFactory.FromInstance(activity));
                return ActivityFactoryRegistryImpl.this;
            }
        };
    }

    @Override
    public UseActivityFactory<Configuration<ActivityFactoryRegistryImpl>> match(final Class<? extends Place> placeClass) {
        return new UseActivityFactory<Configuration<ActivityFactoryRegistryImpl>>() {
            @Override
            public Configuration<ActivityFactoryRegistryImpl> use(ActivityFactory activityFactory) {
                ActivityFactoryRegistryImpl.this.placeMatchActivityFactoryMap.put(PlaceMatch.clazz(placeClass), activityFactory);
                return ActivityFactoryRegistryImpl.this;
            }

            @Override
            public Configuration<ActivityFactoryRegistryImpl> use(Activity activity) {
                ActivityFactoryRegistryImpl.this.placeMatchActivityFactoryMap.put(PlaceMatch.clazz(placeClass), new ActivityFactory.FromInstance(activity));
                return ActivityFactoryRegistryImpl.this;
            }
        };
    }

    @Override
    public Activity getActivity(Place place) {
        return findActivityFactory(place).create(place);
    }
}
