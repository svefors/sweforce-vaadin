package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.PlaceController;
import sweforce.gui.place.PlaceControllerImpl;
import sweforce.vaadin.security.place.SecurePlaceController;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:01 AM
* To change this template use File | Settings | File Templates.
*/
public class SecurePlaceControllerModule extends BinderModule {
    @Override
    protected void declare() {
        injectingInto(SecurePlaceController.class).bind(PlaceController.class).to(PlaceControllerImpl.class);
        bind(PlaceController.class).to(SecurePlaceController.class);
    }
}
