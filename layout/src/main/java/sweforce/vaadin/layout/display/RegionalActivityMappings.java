package sweforce.vaadin.layout.display;

import com.google.inject.Injector;
import sweforce.gui.ap.activity.Activity;
import sweforce.gui.ap.activity.ActivityMapper;
import sweforce.gui.ap.place.Place;
import sweforce.vaadin.layout.display.region.Region;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/18/13
 * Time: 6:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegionalActivityMappings {

    private final Injector injector;

    private final Map<Region, RegionMapping> regionRegionMappingMap = new HashMap<Region, RegionMapping>();

    public RegionalActivityMappings(Injector injector) {
        this.injector = injector;
    }

    public ActivityMapper getActivityMapper(final Region region){
        return new ActivityMapper() {
            @Override
            public Activity getActivity(Place place) {
                RegionMapping regionMapping = regionRegionMappingMap.get(region);
                if(regionMapping == null)
                    return null;

                Class<? extends Activity> activityClass = regionMapping .map.get(place.getClass());
                Activity activity = injector.getInstance(activityClass);
                //Should this really be here? NO, move it somewhere else.
//                if(activity != null && activity instanceof Activity.ConfigurableFromPlace<?>){
//                    ((Activity.ConfigurableFromPlace<?>) activity).withPlace(place);
//                }
                return activity;
            }
        };
    }

    private class RegionMapping{
        public final Map<Class<? extends Place>, Class<? extends Activity>> map =
                new HashMap<Class<? extends Place>, Class<? extends Activity>>();

    }



    public PlaceRegionMapping forPlace(Class<? extends Place> placeClass){
        return new PlaceRegionMapping(placeClass);
    }

    public class PlaceRegionMapping {
        private final Class<? extends Place> placeClass;

        public PlaceRegionMapping(Class<? extends Place> placeClass) {
            this.placeClass = placeClass;
        }

        public PlaceActivityMapping inRegion(Region region){
            return new PlaceActivityMapping(region);
        }

        public class PlaceActivityMapping {
            private final Region region;


            public PlaceActivityMapping(Region region) {
                this.region = region;
            }


            public void useActivity(Class<? extends Activity> actvityClass){

            }
        }

    }

}
