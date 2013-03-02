package sweforce.vaadin.layout.display;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;
import sweforce.gui.ap.activity.Activity;
import sweforce.gui.ap.activity.ActivityMapper;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.history.PlaceTokenizerStore;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.PlaceTokenizerUtil;
import sweforce.gui.ap.place.token.Prefix;
import sweforce.vaadin.layout.display.region.Region;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**

 */
public abstract class RegionPlacesModule extends AbstractModule {

    private MapBinder<String, PlaceTokenizer> placeTokenizerMapBinder =
            MapBinder.newMapBinder(binder(), String.class, PlaceTokenizer.class);

    private MapBinder<Region, ActivityMapper> regionActivityMapperMapBinder =
            MapBinder.newMapBinder(binder(), Region.class, ActivityMapper.class);

    private Map<String, PlaceTokenizer<? extends Place>> tokenizerMap =
            new HashMap<String, PlaceTokenizer<? extends Place>>();

    @Provides
    public PlaceTokenizerStore providePlaceTokenizerStore() {
        return new PlaceTokenizerStore() {
            @Override
            public PlaceTokenizer getTokenizer(String prefix) {
                return tokenizerMap.get(prefix);
            }
        };
    }

    private class RegionMapping{
        public final Map<Class<? extends Place>, Class<? extends Activity>> map =
                new HashMap<Class<? extends Place>, Class<? extends Activity>>();

    }

    private final Map<Region, RegionMapping> regionRegionMappingMap = new HashMap<Region, RegionMapping>();


    public final void register(final Class<? extends Place> placeClass,
                                  final Region region,
                                  final Class<? extends Activity> activityClass){
        //place tokenizer registration
        Class<? extends PlaceTokenizer> placeTokenizerClass =  PlaceTokenizerUtil.getDeclaredPlaceTokenizerClass(placeClass);
        String prefix = PlaceTokenizerUtil.getPrefixAnnotationValue(placeTokenizerClass);
        placeTokenizerMapBinder.addBinding(prefix).to(placeTokenizerClass);

        //region to place to activity registration.
        /*
        getActivityMapper(region) -> map.get(region.)

        or
        getActvity(place) -> map.get(region).

        region 1:N place N:1 activity

        bus <-- manager --> mapper


        layout <-> controller:region{} -> mapper


         */

        registerPlaceTokenizer(placeClass);
        bind(activityClass);
        bind(ActivityMapper.class).annotatedWith(Names.named(region.getClass().getName()))
                .toInstance(new RegionalActivityMapper(region));

    }



    private class RegionalActivityMapper implements ActivityMapper{
        private final Region region;

        private RegionalActivityMapper(Region region) {
            this.region = region;
        }

        @Override
        public Activity getActivity(Place place) {
            RegionMapping regionMapping = regionRegionMappingMap.get(region);
            if(regionMapping == null)
                return null;

            Class<? extends Activity> activityClass = regionMapping .map.get(place.getClass());
            return getProvider(activityClass).get();
//
//            //Should this really be here? NO, move it somewhere else.
//            if(activity != null && activity instanceof Activity.ConfigurableFromPlace<?>){
//                ((Activity.ConfigurableFromPlace<?>) activity).withPlace(place);
//            }
//            return activity;
        }

    }

    @Override
    protected final void configure() {
        configure(new Config());
    }



    /**
     * this method is called during the configure process
     * usage:
     * config
     *   .forPlace(LoginPlace.class).inRegion(CENTER).map(LoginActivity.class)
     *   .forPlace(Home.class).inRegion(Center).map(DashboardActivity.class)
     *
     * I will bind the activities each time you add them.
     * I will register a place tokenizer if it exists as a class on the Place class definition
     * I will add the region and place mapping to the regionalActivityMapper.
     * you can ge this regionalActivityMapper by
     *
     * @param config the configuration object to use
     * @throws IllegalArgumentException if the
     */
    protected abstract void configure(Config config);

    public class Config {

        public Config defaultPlaceInstance(Place place){
            bind(Place.class).annotatedWith(Names.named("Default Place")).toInstance(place);
            return this;
        }

        public PlaceConfig forPlace(Class<? extends Place> placeClass){
            return new PlaceConfig(placeClass);
        }

        public class PlaceConfig {
            private final Class<? extends Place> placeClass;

            public PlaceConfig(Class<? extends Place> placeClass) {
                this.placeClass = placeClass;
            }

            public <P extends Place> PlaceConfig useTokenizer(Class<? extends PlaceTokenizer<P>> placeTokenizerClass){
                addPlaceTokenizer(placeTokenizerClass);
                return this;
            }

            public PlaceConfig forPlace(Class<? extends Place> placeClass){
                return Config.this.forPlace(placeClass);
            }

            public RegionMapping inRegion(Region region){
                return new RegionMapping(region);
            }

            public class RegionMapping {
                private final Region region;

                public RegionMapping(Region region) {
                    this.region = region;
                }

                public PlaceConfig useActivity(Class<? extends Activity> activityClass){
                    register(PlaceConfig.this.placeClass, RegionMapping.this.region, activityClass);
                    return PlaceConfig.this;
                }
            }
        }
    }

    protected void registerPlaceTokenizer(String prefix, PlaceTokenizer<? extends Place> tokenizer) {
        tokenizerMap.put(prefix, (PlaceTokenizer<? extends Place>) tokenizer);
    }

    protected void registerPlaceTokenizer(String prefix, Class<? extends PlaceTokenizer<? extends Place>> tokenizer) {
        try {
            registerPlaceTokenizer(prefix, tokenizer.newInstance());
        } catch (InstantiationException ie) {
            throw new IllegalArgumentException(ie);
        } catch (IllegalAccessException ie) {
            throw new IllegalArgumentException(ie);
        }
    }

    protected <P extends Place> void addPlaceTokenizer(Class<? extends PlaceTokenizer<P>> placeTokenizerClass) {
        String prefix = PlaceTokenizerUtil.getPrefixAnnotationValue(placeTokenizerClass);
        registerPlaceTokenizer(prefix, placeTokenizerClass);
    }

    /**
     * @param placeClass
     */
    protected void registerPlaceTokenizer(Class<? extends Place> placeClass) {
        Class declaredClasses[] = placeClass.getDeclaredClasses();

        for (Class declaredClass : declaredClasses) {
            if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                addPlaceTokenizer(declaredClass);
            }
        }
    }





    /*
    public Builder addPlace(Class<? extends Place> placeClass) {
            Class declaredClasses[] = placeClass.getDeclaredClasses();
            for (Class declaredClass : declaredClasses) {
                if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                    return addTokenizer(declaredClass);
                }
            }
            throw new IllegalArgumentException("Place does not have tokenizer: " + placeClass.getName());
        }
     */


}
