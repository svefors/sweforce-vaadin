package sweforce.gui.activity.registry;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
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
public interface ActivityFactoryRegistry  {

    public ActivityFactory findActivityFactory(Place place);

    public class Impl implements ActivityFactoryRegistry,
            ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration>, ActivityMapper {

        private Map<PlaceMatch, ActivityFactory> placeMatchActivityFactoryMap =
                new HashMap<PlaceMatch, ActivityFactory>();


        @Override
        public ActivityFactory findActivityFactory(Place place) {
            for (Map.Entry<PlaceMatch, ActivityFactory> entry : placeMatchActivityFactoryMap.entrySet()){
                if (entry.getKey().matches(place))
                    return entry.getValue();
            }
            return new ActivityFactory.NullActivityFactory();
        }



        @Override
        public UseActivityFactory<Configuration<Configuration>> match(final PlaceMatch placeMatch) {
            return new UseActivityFactory<Configuration<Configuration>>() {
                @Override
                public Configuration<Configuration> use(ActivityFactory activityFactory) {
                    Impl.this.placeMatchActivityFactoryMap.put(placeMatch, activityFactory);
                    return Impl.this;
                }
            };
        }

        @Override
        public Activity getActivity(Place place) {
            return findActivityFactory(place).create(place);
        }
    }


    public interface Configuration<T> {

        public UseActivityFactory<Configuration<T>> match(PlaceMatch placeMatch);

        public interface UseActivityFactory<T> {
            public T use(ActivityFactory activityFactory);
        }
    }
}








//    public class Impl implements ActivityFactoryRegistry {
//        private final HashMap<AppliesToPlace, ActivityProvider> appliesToPlaceActivityProviderHashMap =
//                new HashMap<AppliesToPlace, ActivityProvider>();
//
//        public Impl(Entry[] entries) {
//            for (Entry entry : entries){
//                appliesToPlaceActivityProviderHashMap.put(entry.appliesToPlace, entry.activityProvider);
//            }
//        }
//
//        public Impl() {
//
//        }
//
//        public ActivityProvider findProvider(Place place){
//            for (AppliesToPlace appliesToPlace : appliesToPlaceActivityProviderHashMap.keySet()){
//                if (appliesToPlace.appliesTo(place))
//                    return appliesToPlaceActivityProviderHashMap.get(appliesToPlace);
//            }
//            return null;
//        }
//
//        @Override
//        public boolean add(Entry entry) {
//            return this.appliesToPlaceActivityProviderHashMap.put(entry.getKey(), entry.getValue()) != null;
//        }
//
//        @Override
//        public String toString() {
//            return "Impl{" +
//                    "appliesToPlaceActivityProviderHashMap=" + appliesToPlaceActivityProviderHashMap +
//                    '}';
//        }
//    }
//
//
//

//
//        public static class PlaceClass implements AppliesToPlace{
//            private final Class<? extends Place> placeClass;
//
//            public PlaceClass(Class<? extends Place> placeClass) {
//                this.placeClass = placeClass;
//            }
//
//            @Override
//            public boolean appliesTo(Place place) {
//                if (placeClass.isAssignableFrom(place.getClass()))
//                    return true;
//                return false;
//            }
//
//            @Override
//            public String toString() {
//                return "PlaceClass{" +
//                        "placeClass=" + placeClass +
//                        '}';
//            }
//        }
//    }
//
//    public static class Entry implements Map.Entry<AppliesToPlace, ActivityProvider>{
//        public final AppliesToPlace appliesToPlace;
//        public final ActivityProvider activityProvider;
//
//        public Entry(AppliesToPlace appliesToPlace, ActivityProvider activityProvider) {
//            this.appliesToPlace = appliesToPlace;
//            this.activityProvider = activityProvider;
//        }
//
//        public  Entry(Class<? extends Place> placeClass, Activity activity){
//            this(new AppliesToPlace.PlaceClass(placeClass), new ActivityProvider.FromInstance(activity));
//        }
//
//        public Entry(Class<? extends Place> placeClass, ActivityProvider activityProvider){
//            this(new AppliesToPlace.PlaceClass(placeClass), activityProvider);
//        }
//
//        public  Entry(Place place, ActivityProvider activityProvider){
//            this(new AppliesToPlace.PlaceEquals(place), activityProvider);
//        }
//
//        public Entry(Place place, Activity activity){
//            this(new AppliesToPlace.PlaceEquals(place), new ActivityProvider.FromInstance(activity));
//        }
//
//        @Override
//        public AppliesToPlace getKey() {
//            return appliesToPlace;
//        }
//
//        @Override
//        public ActivityProvider getValue() {
//            return activityProvider;
//        }
//
//        @Override
//        public ActivityProvider setValue(ActivityProvider value) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public String toString() {
//            return "Entry{" +
//                    "appliesToPlace=" + appliesToPlace +
//                    ", activityProvider=" + activityProvider +
//                    '}';
//        }
//    }


//    }
