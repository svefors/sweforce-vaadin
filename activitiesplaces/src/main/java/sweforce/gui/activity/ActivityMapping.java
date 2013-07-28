package sweforce.gui.activity;

import sweforce.gui.activity.registry.ActivityFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/13/13
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityMapping {

    public PlaceMatch getPlaceMatch();

    public ActivityFactory getActivityFactory();


    public class Impl implements ActivityMapping {
        public PlaceMatch getPlaceMatch() {
            return placeMatch;
        }

        public ActivityFactory getActivityFactory() {
            return activityFactory;
        }

        private final PlaceMatch placeMatch;
        private final ActivityFactory activityFactory;

        public Impl() {
            this(PlaceMatch.nullz(), new ActivityFactory.NullActivityFactory());
        }

        public Impl(PlaceMatch placeMatch, ActivityFactory activityFactory) {
            this.placeMatch = placeMatch;
            this.activityFactory = activityFactory;
        }
    }
//
//    public static interface WithActivityMappings {
//        public boolean addActivityMapping(final ActivityMapping activityMapping);
//        public int removeActivityMappingsFor(final PlaceMatch placeMatch);
//    }
}
