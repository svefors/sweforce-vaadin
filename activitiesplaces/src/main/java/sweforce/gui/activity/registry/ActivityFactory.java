package sweforce.gui.activity.registry;

import sweforce.gui.activity.Activity;
import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/10/13
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityFactory {

    /**
     * this may seem the same as ActivityMapper.getActivity(place), but it is not
     *
     * @param place
     * @return
     */
    public Activity create(Place place);


    public static abstract class TypeChecked<A extends Activity, P extends Place>
            implements ActivityFactory {

        @Override
        public Activity create(Place place) {
            return create((P) place);
        }

        protected abstract A createTypeCheckedPlace(P place);

    }

    public static class FromInstance implements ActivityFactory {
        private final Activity activity;

        public FromInstance(Activity activity) {
            this.activity = activity;
        }

        @Override
        public Activity create(Place place) {
            return activity;
        }
    }

    /**
     * A factory that always return null;
     */
    public static class NullActivityFactory implements ActivityFactory {
        @Override
        public Activity create(Place place) {
            return null;
        }
    }
}
