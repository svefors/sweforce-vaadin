package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.ActivityMapperWithActivityProviderRegistry;
import sweforce.gui.activity.ActivityProviderRegistry;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class NamedActivityMapperModule extends BinderModule {

    private final String name;

    private final ActivityProviderRegistry activityProviderRegistry = new ActivityProviderRegistry.Impl();

    private final ActivityMapper activityMapper = new ActivityMapperWithActivityProviderRegistry(activityProviderRegistry);

    public NamedActivityMapperModule(String name) {
        this.name = name;
    }

    @Override
    protected void declare() {
        bind(Name.named(name), ActivityMapper.class).to(activityMapper);
    }

    protected final void add(ActivityProviderRegistry.Entry entry){
        activityProviderRegistry.add(entry);
    }


}
