package sweforce.gui.guice;


import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import sweforce.gui.activity.Activity;
import sweforce.gui.display.region.PlaceRegion;
import sweforce.gui.display.region.Region;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;

import javax.inject.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 3/3/13
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceRegionActivityBinder {

    private final MapBinder<PlaceRegion, Activity> placeRegionActivityMapBinder;

    public PlaceRegionActivityBinder(MapBinder<PlaceRegion, Activity> placeRegionActivityMapBinder) {
        this.placeRegionActivityMapBinder = placeRegionActivityMapBinder;
    }

    public class PlaceRegionBindingBuilder {
        public final Class<? extends Place> placeClass;

        public PlaceRegionBindingBuilder(Class<? extends Place> placeClass) {
            this.placeClass = placeClass;
        }

        public PlaceRegionBindingBuilder.ActivityBinder in(Region region) {
            return new PlaceRegionBindingBuilder.ActivityBinder(region);
        }

        public class ActivityBinder {
            public final Region region;

            public ActivityBinder(Region region) {
                this.region = region;
            }

            public void to(Class<? extends Activity> activityClass) {
                placeRegionActivityMapBinder.addBinding(new PlaceRegion(placeClass, region)).to(activityClass);
            }

            public void toProvider(Class<? extends Provider<? extends Activity>> provider) {
                placeRegionActivityMapBinder.addBinding(new PlaceRegion(placeClass, region)).toProvider(provider);
            }
        }
    }

    public static PlaceRegionActivityBinder newPlaceRegionBinder(Binder binder) {
        MapBinder<PlaceRegion, Activity> placeRegionActivityMapBinder =
                MapBinder.newMapBinder(binder, PlaceRegion.class, Activity.class);

//        final MapBinder<String, PlaceTokenizer> placeTokenizerMapBinder =
//                MapBinder.newMapBinder(binder, String.class, PlaceTokenizer.class).permitDuplicates();
        return new PlaceRegionActivityBinder(placeRegionActivityMapBinder);
    }
}
