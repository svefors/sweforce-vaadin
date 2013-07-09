package sweforce.gui.activity;

import sweforce.gui.place.Place;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityProviderRegistry {

    public class Impl implements ActivityProviderRegistry{
        private final HashMap<AppliesToPlace, ActivityProvider> appliesToPlaceActivityProviderHashMap =
                new HashMap<AppliesToPlace, ActivityProvider>();

        public Impl(Entry[] entries) {
            for (Entry entry : entries){
                appliesToPlaceActivityProviderHashMap.put(entry.appliesToPlace, entry.activityProvider);
            }
        }

        public Impl() {

        }

        public ActivityProvider findProvider(Place place){
            for (AppliesToPlace appliesToPlace : appliesToPlaceActivityProviderHashMap.keySet()){
                if (appliesToPlace.appliesTo(place))
                    return appliesToPlaceActivityProviderHashMap.get(appliesToPlace);
            }
            return null;
        }

        @Override
        public boolean add(Entry entry) {
            return this.appliesToPlaceActivityProviderHashMap.put(entry.getKey(), entry.getValue()) != null;
        }

        @Override
        public String toString() {
            return "Impl{" +
                    "appliesToPlaceActivityProviderHashMap=" + appliesToPlaceActivityProviderHashMap +
                    '}';
        }
    }

    public boolean add(Entry entry);

    public ActivityProvider findProvider(Place place);

    public interface AppliesToPlace {
        public boolean appliesTo(Place place);

        public static class PlaceEquals implements AppliesToPlace{
            private final Place place;

            public PlaceEquals(Place place) {
                this.place = place;
            }

            @Override
            public boolean appliesTo(Place place) {
                return this.place.equals(place);
            }

            @Override
            public String toString() {
                return "PlaceEquals{" +
                        "place=" + place +
                        '}';
            }
        }

        public static class PlaceClass implements AppliesToPlace{
            private final Class<? extends Place> placeClass;

            public PlaceClass(Class<? extends Place> placeClass) {
                this.placeClass = placeClass;
            }

            @Override
            public boolean appliesTo(Place place) {
                if (placeClass.isAssignableFrom(place.getClass()))
                    return true;
                return false;
            }

            @Override
            public String toString() {
                return "PlaceClass{" +
                        "placeClass=" + placeClass +
                        '}';
            }
        }
    }

    public static class Entry implements Map.Entry<AppliesToPlace, ActivityProvider>{
        public final AppliesToPlace appliesToPlace;
        public final ActivityProvider activityProvider;

        public Entry(AppliesToPlace appliesToPlace, ActivityProvider activityProvider) {
            this.appliesToPlace = appliesToPlace;
            this.activityProvider = activityProvider;
        }

        public  Entry(Class<? extends Place> placeClass, Activity activity){
            this(new AppliesToPlace.PlaceClass(placeClass), new ActivityProvider.FromInstance(activity));
        }

        public Entry(Class<? extends Place> placeClass, ActivityProvider activityProvider){
            this(new AppliesToPlace.PlaceClass(placeClass), activityProvider);
        }

        public  Entry(Place place, ActivityProvider activityProvider){
            this(new AppliesToPlace.PlaceEquals(place), activityProvider);
        }

        public Entry(Place place, Activity activity){
            this(new AppliesToPlace.PlaceEquals(place), new ActivityProvider.FromInstance(activity));
        }

        @Override
        public AppliesToPlace getKey() {
            return appliesToPlace;
        }

        @Override
        public ActivityProvider getValue() {
            return activityProvider;
        }

        @Override
        public ActivityProvider setValue(ActivityProvider value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "appliesToPlace=" + appliesToPlace +
                    ", activityProvider=" + activityProvider +
                    '}';
        }
    }


}
