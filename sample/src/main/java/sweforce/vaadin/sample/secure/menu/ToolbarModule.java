package sweforce.vaadin.sample.secure.menu;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.vaadin.sample.secure.MainActivityMapper;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToolbarModule extends BinderModule {

    @Override
    protected void declare() {
        autobind(MenuActivity.class).toConstructor();
        bind(Name.named("Toolbar"), ActivityFactoryRegistry.Plugin.class).to(new MainActivityMapper.Plugin() {
            @Override
            public void configure(ActivityFactoryRegistry.Configuration<? extends ActivityFactoryRegistry.Configuration> configuration) {
                configuration.match()
            }
        });
    }
}
