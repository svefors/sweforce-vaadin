package sweforce.gui.activity;

import sweforce.gui.place.Place;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;

/**
 *
 */
public class PlaceClassActivityProviderActivityMapper implements ActivityMapper {

    private Map<Class<? extends Place>, Provider<? extends Activity>> activityProviders;

    @Inject
    public PlaceClassActivityProviderActivityMapper(Map<Class<? extends Place>, Provider<? extends Activity>> activityProviders) {
        this.activityProviders = activityProviders;
    }


    @Override
    public Activity getActivity(Place place) {
        Provider<? extends Activity> provider = activityProviders.get(place);
        if (provider != null)
            return provider.get();
        return null;
    }
}

