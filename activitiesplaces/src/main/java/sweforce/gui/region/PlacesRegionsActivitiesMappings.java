package sweforce.gui.region;


import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import sweforce.gui.activity.Activity;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PlaceTokenizerUtil;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 3/3/13
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlacesRegionsActivitiesMappings {

    private final MapBinder<PlaceRegion, Activity> placeRegionActivityMapBinder;

    private final MapBinder<String, PlaceTokenizer<? extends Place>> stringPlaceTokenizerMapBinder;

    public PlacesRegionsActivitiesMappings(Binder binder, MapBinder<PlaceRegion, Activity> placeRegionActivityMapBinder) {
        this.placeRegionActivityMapBinder = MapBinder.newMapBinder(binder, PlaceRegion.class, Activity.class);
        this.stringPlaceTokenizerMapBinder = MapBinder.newMapBinder(binder,
                new TypeLiteral<String>() {
                },
                new TypeLiteral<PlaceTokenizer<? extends Place>>() {
                }
        );
    }

    public KnownPlace at(final Class<? extends Place> place) {
        PlaceTokenizerUtil.bind(place, stringPlaceTokenizerMapBinder);
        return new KnownPlace() {
            @Override
            public InRegion in(final Region region) {
                return new InRegion() {
                    @Override
                    public PlacesRegionsActivitiesMappings run(Activity activity) {
                        placeRegionActivityMapBinder.addBinding(new PlaceRegion(place, region)).toInstance(activity);
                        return PlacesRegionsActivitiesMappings.this;
                    }

                    @Override
                    public PlacesRegionsActivitiesMappings run(Class<? extends Activity> activity) {
                        placeRegionActivityMapBinder.addBinding(new PlaceRegion(place, region)).to(activity);
                        return PlacesRegionsActivitiesMappings.this;
                    }

                    @Override
                    public <A extends com.google.inject.Provider> PlacesRegionsActivitiesMappings useProvider(Class<? extends com.google.inject.Provider<? extends Activity>> provider) {
                        placeRegionActivityMapBinder.addBinding(new PlaceRegion(place, region)).toProvider(provider);
                        return PlacesRegionsActivitiesMappings.this;
                    }
                };
            }
        };
    }

    public interface InRegion {
        public PlacesRegionsActivitiesMappings run(Activity activity);

        public PlacesRegionsActivitiesMappings run(Class<? extends Activity> activity);

        public <A extends com.google.inject.Provider> PlacesRegionsActivitiesMappings useProvider(Class<? extends com.google.inject.Provider<? extends Activity>> provider);
    }

    public interface KnownPlace {
        public InRegion in(Region region);

    }

    /*
    Use stricter configuration wizard order.

    new ActivitiesPlacesWizard()
        .createPlaceTokenizersFromPlaceClassesUsingReflection()
        or
        .usePrefixPlaceTokenizerMap(Map<String, PlaceTokenizer>

        .useRegions
    usePlaceTokenizer
    x.in(TopLevel).runByDefault(MenuActivity.class)
    x.at(AccountsPlace.class).in(SubLevel).run(
     */


}
