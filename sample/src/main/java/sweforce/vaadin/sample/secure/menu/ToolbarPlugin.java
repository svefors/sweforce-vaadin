package sweforce.vaadin.sample.secure.menu;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.registry.ActivityFactory;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.place.Place;
import sweforce.gui.place.PrefixPlaceTokenizerRegistry;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutPlace;

/**
 * TODO: find a better name
 */
public class ToolbarPlugin implements ActivityFactoryRegistry.Plugin {

    private final MenuActivity menuActivity;

    public ToolbarPlugin(MenuActivity menuActivity) {
        this.menuActivity = menuActivity;
    }

    @Override
    public void configure(ActivityFactoryRegistry.Configuration<? extends ActivityFactoryRegistry.Configuration> configuration) {
        configuration.match(new ToolbarPlaceMatch()).use(menuActivity);
    }
}
