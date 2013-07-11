package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.registry.ActivityMapperWithRegistry;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.activity.ActivityMapper;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class NamedActivityMapperModule extends BinderModule {

    private final String name;

    private final ActivityFactoryRegistry activityFactoryRegistry = new ActivityFactoryRegistry.Impl();

    private final ActivityMapper activityMapper = new ActivityMapperWithRegistry(activityFactoryRegistry);

    public NamedActivityMapperModule(String name) {
        this.name = name;
    }

    @Override
    protected void declare() {
        bind(Name.named(name), ActivityMapper.class).to(activityMapper);
    }

    protected final void add(ActivityFactoryRegistry.Entry entry){
        activityFactoryRegistry.add(entry);
    }


}
