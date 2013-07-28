package sweforce.gui.place;

import se.jbee.inject.bind.BinderModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/21/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceControllerSilkModule extends BinderModule {

    @Override
    protected void declare() {
        bind(PlaceController.class).to(PlaceControllerImpl.class);
        bind(PlaceControllerImpl.class).toConstructor();
    }
}
