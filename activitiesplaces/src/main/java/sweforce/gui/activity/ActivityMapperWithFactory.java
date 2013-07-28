package sweforce.gui.activity;

import sweforce.gui.place.Place;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/24/13
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityMapperWithFactory implements ActivityMapper {

    private final Map<PlaceMatch, Class<? extends Activity>> placeMatchClassMap;

    private final ActivityFactory activityFactory;

    public ActivityMapperWithFactory(Map<PlaceMatch, Class<? extends Activity>> placeMatchClassMap, ActivityFactory activityFactory) {
        this.placeMatchClassMap = placeMatchClassMap;
        this.activityFactory = activityFactory;
    }

    public ActivityMapperWithFactory(ActivityFactory activityFactory) {
        this(new LinkedHashMap<PlaceMatch, Class<? extends Activity>>(), activityFactory);
    }

    @Override
    public Activity getActivity(Place place) {
        return getActivityForTypedPlace(place);
    }

    private <P extends Place> Activity getActivityForTypedPlace(P place) {
        for (Map.Entry<PlaceMatch, Class<? extends Activity>> entry : placeMatchClassMap.entrySet()) {
            if (entry.getKey().matches(place)) {
                Activity activity = activityFactory.getActivity(entry.getValue());
                if (activity instanceof Activity.ConfigurableFromPlace) {
                    ((Activity.ConfigurableFromPlace<P>) activity).withPlace((P) place);
                }
                return activity;
            }
        }
        return null;
    }

    public void add(PlaceMatch placeMatch, Class<? extends Activity> activityClass) {
        placeMatchClassMap.put(placeMatch, activityClass);
    }

    public void add(PlaceMatchActivityMapping placeMatchActivityMapping){
        placeMatchClassMap.put(placeMatchActivityMapping.getPlaceMatch(),
                placeMatchActivityMapping.getActivityClass());
    }
}
