package sweforce.gui.activity;

import com.google.inject.Provider;
import sweforce.gui.place.Place;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Map;

/**
 * Will use a default activity provider if there is none found for the place
 */
public class PlaceClassActivityMapperWithDefaultActivity implements ActivityMapper {

    private Provider<? extends Activity> defaultActivityProvider;

    private Map<Class<? extends Place>, Provider<? extends Activity>> activityProviders;

    @Inject
    public PlaceClassActivityMapperWithDefaultActivity(Map<Class<? extends Place>, Provider<? extends Activity>> activityProviders, Provider<? extends Activity> defaultActivityProvider) {
        this.activityProviders = activityProviders;
        this.defaultActivityProvider = defaultActivityProvider;
    }

    @Override
    public Activity getActivity(Place place) {
        Provider<? extends Activity> provider = activityProviders.get(place);
        if (provider  != null) {
            return provider.get();
        }else{
            return defaultActivityProvider.get();
        }
    }


}
