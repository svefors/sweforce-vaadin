package sweforce.gui.guice;

import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import sweforce.gui.activity.Activity;
import sweforce.gui.place.Place;

import javax.inject.Provider;

/**
 * Use this binder if you do not have composite/region ui, i.e. multiple activities for each place
 */
public class PlaceActivityBinder {

    private final MapBinder<Class, Activity> placeClassActivityMapBinder;

    public PlaceActivityBinder(MapBinder<Class, Activity> placeClassActivityMapBinder) {
        this.placeClassActivityMapBinder = placeClassActivityMapBinder;
    }

    public static PlaceActivityBinder newPlaceActivityBinder(Binder binder) {
        return new PlaceActivityBinder(MapBinder.newMapBinder(binder, Class.class, Activity.class));
    }

    public ActivityBinder map(Class<? extends Place> placeClass) {
        return new ActivityBinder(placeClass);
    }

    public class ActivityBinder {
        private final Class<? extends Place> placeClass;


        public ActivityBinder(Class<? extends Place> placeClass) {
            this.placeClass = placeClass;
        }

        public void to(Class<? extends Activity> activityClass) {
            placeClassActivityMapBinder.addBinding(placeClass).to(activityClass);
        }

        public void toProvider(Class<? extends Provider<? extends Activity>> provider) {
            placeClassActivityMapBinder.addBinding(placeClass).toProvider(provider);
        }

        public <A extends Activity> void toInstance(A activity) {
            placeClassActivityMapBinder.addBinding(placeClass).toInstance(activity);
        }

    }
}
