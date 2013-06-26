package sweforce.gui.guice;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.display.region.PlacesRegionsActivitiesMappings;
import sweforce.gui.display.region.RegionalActivityMapper;
import sweforce.gui.display.region.RegionActivityMapperFactory;


/**

 */
public abstract class RegionPlacesModule extends AbstractModule {





    @Override
    protected final void configure() {
        install(new FactoryModuleBuilder().implement(ActivityMapper.class, RegionalActivityMapper.class)
                .build(RegionActivityMapperFactory.class));

        configurePlaces();
    }

    /**
     * add/configure your places in here
     */
    protected abstract void configurePlaces();

//    /**
//     * usage:
//     * bindPlace(UserAdminPlace.class).in(Region.LEFT).to(MenuActivity.class)
//     */
//    protected PlaceRegionActivityBinder.PlaceRegionBindingBuilder bindPlace(Class<? extends Place> placeClass) {
//        return PlaceRegionActivityBinder.newPlaceRegionBinder(binder())
//        return getPlaceRegionBinder().bind(placeClass);
//    }

    protected final PlacesRegionsActivitiesMappings getPlaceRegionBinder() {
        return PlacesRegionsActivitiesMappings.newPlaceRegionBinder(binder());
    }

    /*

bind(place).to(


     */

}
