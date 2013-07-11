package sweforce.vaadin.sample.secure.menu;

import se.jbee.inject.bind.BinderModule;

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
        bind(MenuActivity.class).toConstructor();
        bind(ToolbarPlugin.class).toConstructor();
    }
}
