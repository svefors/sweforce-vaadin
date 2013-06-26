package sweforce.gui.activity;

import sweforce.gui.place.Place;

import javax.inject.Provider;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 6/24/13
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityMapperWithDefaultFallback implements ActivityMapper {

    private final ActivityMapper delegate;

    private final Provider<Activity> defaultActivity;

    public ActivityMapperWithDefaultFallback(@DefaultActivity Provider<Activity> defaultActivity, @Delegate ActivityMapper delegate) {
        this.defaultActivity = defaultActivity;
        this.delegate = delegate;
    }

    @Override
    public Activity getActivity(Place place) {
        Activity activity = delegate.getActivity(place);
        return activity == null ? defaultActivity.get() : activity;
    }

    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public static @interface Delegate {

    }

    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public static @interface DefaultActivity {

    }
}
