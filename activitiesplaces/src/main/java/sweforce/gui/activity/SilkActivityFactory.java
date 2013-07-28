package sweforce.gui.activity;

import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/25/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class SilkActivityFactory implements ActivityFactory {

    private final Injector injector;

    public SilkActivityFactory(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <T extends Activity> T getActivity(Class<T> activityClass) {
        return injector.resolve(Dependency.dependency(activityClass));
    }
}
