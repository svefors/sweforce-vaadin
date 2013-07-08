package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.vaadin.sample.secure.norole.NoRoleModule;
import sweforce.vaadin.sample.secure.role1.Role1Module;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:00 AM
* To change this template use File | Settings | File Templates.
*/
public class SecureApplicationBundle extends BootstrapperBundle {
    @Override
    protected void bootstrap() {
        install(EventModule.class);
        install(HistorianModule.class);
        install(PlaceHistoryModule.class);
        install(SecurePlaceControllerModule.class);
        install(MainRegionModule.class);
        install(NoRoleModule.class);
        install(Role1Module.class);

    }
}
