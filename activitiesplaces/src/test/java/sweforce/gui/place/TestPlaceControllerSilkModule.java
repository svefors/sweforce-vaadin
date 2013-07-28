package sweforce.gui.place;

import org.junit.Test;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.event.SimpleEventBusModule;


import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/21/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestPlaceControllerSilkModule {

    @Test
    public void testPlaceController_notnull(){
        Injector injector = Bootstrap.injector(MyBootStrap.class);
        PlaceController placeController =  injector.resolve(Dependency.dependency(PlaceController.class));
        assertNotNull(placeController);
    }

    private static class MyBootStrap extends BootstrapperBundle{
        @Override
        protected void bootstrap() {
            install(SimpleEventBusModule.class);
            install(PlaceControllerSilkModule.class);
            install(new BinderModule() {
                @Override
                protected void declare() {
                    bind(PlaceController.ConfirmationHandler.class).to(MockConfirmationHandler.class);
                }
            });
//            install(VaadinSilkModule.class);
        }
    }
}
