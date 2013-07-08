package sweforce.gui.activity;

import sweforce.gui.place.Place;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/4/13
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompositeActivityMapper implements ActivityMapper {


    private final Set<ActivityMapper> activityMapperSet;


    public CompositeActivityMapper(ActivityMapper[] activityMappers) {
        this.activityMapperSet  = new HashSet<ActivityMapper>();
        for(ActivityMapper activityMapper : activityMappers){
            registerActivityMapper(activityMapper);
        }
    }



    public boolean registerActivityMapper(ActivityMapper activityMapper) {
        if (activityMapper == this)
            throw new IllegalArgumentException("Can't add itself to CompositeActivityMapper!");
        return activityMapperSet.add(activityMapper);
    }

    public boolean removeActivityMapper(ActivityMapper activityMapper) {
        return activityMapperSet.remove(activityMapper);
    }


    @Override
    public Activity getActivity(Place place) {
        for (ActivityMapper activityMapper : activityMapperSet) {
            Activity activity = activityMapper.getActivity(place);
            if (activity != null)
                return activity;
        }
        return null;
    }
}
