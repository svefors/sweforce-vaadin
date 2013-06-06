package sweforce.gui.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;
import sweforce.gui.display.region.PlaceRegionActivityMapper;
import sweforce.gui.display.region.RegionActivityMapperFactory;
import sweforce.gui.display.region.Region;




/**

 */
public abstract class RegionPlacesModule extends AbstractModule {

    /**
     * convenience method to
     *
     * @param placeClass
     * @param region
     * @param activityClass
     */
    protected final void configurePlaceRegionActivity(final Class<? extends Place> placeClass,
                                                      final Region region,
                                                      final Class<? extends Activity> activityClass) {
        bindPlace(placeClass).in(region).to(activityClass);
    }

    protected final <P extends Place> void bindDefaultPlaceToProvider(Provider<P> defaultPlaceProvider) {
        bind(Place.class).annotatedWith(Names.named("default-place")).toProvider(defaultPlaceProvider);
    }

    protected final <P extends Place> void bindDefaultPlaceInstance(P defaultPlace) {
        bind(Place.class).annotatedWith(Names.named("default-place")).toInstance(defaultPlace);
    }

    @Override
    protected final void configure() {
        install(new FactoryModuleBuilder().implement(ActivityMapper.class, PlaceRegionActivityMapper.class)
                .build(RegionActivityMapperFactory.class));

        configurePlaces();
    }

    /**
     * add/configure your places in here
     */
    protected abstract void configurePlaces();

    /**
     * usage:
     * bindPlace(UserAdminPlace.class).in(Region.LEFT).to(MenuActivity.class)
     */
    protected PlaceRegionActivityBinder.PlaceRegionBindingBuilder bindPlace(Class<? extends Place> placeClass) {
        return getPlaceRegionBinder().bind(placeClass);
    }

    protected final PlaceRegionActivityBinder getPlaceRegionBinder() {
        return PlaceRegionActivityBinder.newPlaceRegionBinder(binder());
    }

    /*

bind(place).to(


     */

}
