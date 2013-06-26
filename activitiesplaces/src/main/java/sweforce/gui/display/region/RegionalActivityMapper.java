package sweforce.gui.display.region;

import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 3/2/13
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegionalActivityMapper implements ActivityMapper {
    // NOTE, needs to be com.google.inject.Provider instead of javax.inject
    private final Map<PlaceRegion, Provider<Activity>> placeRegionActivityProviderMap;

    private final Region region;

    @Inject
    public RegionalActivityMapper(Map<PlaceRegion, Provider<Activity>> placeRegionActivityProviderMap, @Assisted Region region) {
        this.placeRegionActivityProviderMap = placeRegionActivityProviderMap;
        this.region = region;
    }

    @Override
    public Activity getActivity(Place place) {
        Provider<? extends Activity> provider =
                placeRegionActivityProviderMap.get(new PlaceRegion(place.getClass(), region));
        if(provider == null)
            return  null;
        return provider.get();
    }

}
