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

    public interface Configuration<T extends Configuration> {

        public UseActivityFactory<Configuration<T>> match(PlaceMatch placeMatch);

        /**
         * convenience method
         * @param place
         * @return
         */
        public UseActivityFactory<Configuration<T>> match(Place place);

        /**
         * convenience method
         * @param placeClass
         * @return
         */
        public UseActivityFactory<Configuration<T>> match(Class<? extends Place> placeClass);

        public interface UseActivityFactory<T extends Configuration> {
            public T use(ActivityFactory activityFactory);

            public T use(Activity activity);
        }
    }

    public static interface Plugin {
        public void configure(Configuration<? extends Configuration> configuration);
    }
}
