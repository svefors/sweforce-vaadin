package sweforce.gui.activity.registry;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.ActivityManager;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.SingleThreadedActivityManager;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.activity.registry.ActivityFactoryRegistryImpl;
import sweforce.gui.activity.registry.ActivityMapperWithRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class NamedActivityModule extends BinderModule{

    private final Name name;

    public NamedActivityModule(Name name) {
        this.name = name;
    }

    public NamedActivityModule(String name){
        this(Name.named(name));
    }

    @Override
    protected void declare() {
        bind(name, SingleThreadedActivityManager.class).toConstructor();
        bind(name, ActivityManager.class)
                .to(name, SingleThreadedActivityManager.class);
        bind(name, ActivityMapperWithRegistry.class).toConstructor();
        injectingInto(name, SingleThreadedActivityManager.class)
                .bind(ActivityMapper.class).to(name, ActivityMapperWithRegistry.class);
        bind(name, ActivityMapper.class)
                .to(name, ActivityMapperWithRegistry.class);

        bind(name, ActivityFactoryRegistry.class).to(ActivityFactoryRegistryImpl.class);
        bind(name, ActivityFactoryRegistryWithConfiguration.class).to(ActivityFactoryRegistryImpl.class);
    }
}
