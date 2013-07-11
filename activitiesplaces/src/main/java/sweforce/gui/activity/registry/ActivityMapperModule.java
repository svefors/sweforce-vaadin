package sweforce.gui.activity.registry;

import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.util.Scoped;

/**
 * Is scoped per Injection. Every consumer gets their own instance of the activitymapper and activityfactory
 *
 */
public class ActivityMapperModule extends BinderModule {

    @Override
    protected void declare() {
        per(Scoped.INJECTION).autobind(ActivityFactoryRegistryImpl.class);
        per(Scoped.INJECTION).autobind(ActivityMapperWithRegistry.class);
    }

}
