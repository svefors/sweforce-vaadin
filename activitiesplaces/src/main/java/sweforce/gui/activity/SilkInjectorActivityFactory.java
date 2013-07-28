package sweforce.gui.activity;

import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/24/13
 * Time: 8:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class SilkInjectorActivityFactory implements ActivityFactory {

    private final Injector injector;

    public SilkInjectorActivityFactory(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <T extends Activity> T getActivity(Class<T> activityClass) {
        T activity = injector.resolve(Dependency.dependency(activityClass));
        return activity;
    }
}
