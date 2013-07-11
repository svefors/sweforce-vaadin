package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.PlaceController;
import sweforce.gui.place.PlaceControllerImpl;
import sweforce.vaadin.security.SecurityActivitiesFactory;
import sweforce.vaadin.security.SecurityFacade;
import sweforce.vaadin.security.place.SecurePlaceController;
import sweforce.vaadin.security.shiro.ShiroSecurityFacade;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:01 AM
* To change this template use File | Settings | File Templates.
*/
public class SecurityModule extends BinderModule {
    @Override
    protected void declare() {
        autobind(SecurityActivitiesFactory.class);
        injectingInto(SecurePlaceController.class).bind(PlaceController.class).to(PlaceControllerImpl.class);
        bind(PlaceController.class).to(SecurePlaceController.class);
        bind(SecurityFacade.class).to(ShiroSecurityFacade.class);
    }
}
