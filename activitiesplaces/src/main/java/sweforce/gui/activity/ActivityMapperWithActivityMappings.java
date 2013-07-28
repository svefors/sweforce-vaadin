package sweforce.gui.activity;

import sweforce.gui.place.Place;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
public class ActivityMapperWithActivityMappings implements ActivityMapper {

    private final Set<ActivityMapping> activityMappings;

    public ActivityMapperWithActivityMappings() {
        this(new LinkedHashSet<ActivityMapping>());
    }

    public ActivityMapperWithActivityMappings(ActivityMapping[] activityMappings) {
        this();
        for (ActivityMapping mapping : activityMappings) {
            addMapping(mapping);
        }
    }

    public ActivityMapperWithActivityMappings(Set<ActivityMapping> activityMappings) {
        this.activityMappings = activityMappings;
    }


    /**
     * Add an activity mapping for the mapper to use.
     *
     * @param activityMapping
     * @return
     */
    public boolean addMapping(ActivityMapping activityMapping) {
        return this.activityMappings.add(activityMapping);
    }

    @Override
    public Activity getActivity(Place place) {
        for (ActivityMapping mapping : activityMappings) {
            if (mapping.getPlaceMatch().matches(place))
                return mapping.getActivityFactory().create(place);
        }
        return null;
    }
}
