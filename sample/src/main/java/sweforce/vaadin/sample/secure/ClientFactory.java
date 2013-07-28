package sweforce.vaadin.sample.secure;

import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.event.EventBus;
import sweforce.event.SimpleEventBusModule;
import sweforce.gui.activity.ActivityFactory;
import sweforce.gui.activity.SilkActivityFactory;
import sweforce.gui.place.PlaceChangeEvent;
import sweforce.gui.place.PlaceChangeRequestEvent;
import sweforce.gui.place.PlaceController;
import sweforce.gui.place.PlaceControllerImpl;
import sweforce.gui.vaadin.CurrentRootConfirmationHandler;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.security.AuthenticationMVPSilkModule;
import sweforce.vaadin.security.SecurePlaceChangeRequestListener;
import sweforce.vaadin.security.SecurePlaceController;
import sweforce.vaadin.security.SecurityFacade;
import sweforce.vaadin.security.shiro.ShiroSilkModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/25/13
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientFactory {

    Injector injector = Bootstrap.injector(SecureApplicationBundle.class);

    private EventBus eventBus = injector.resolve(Dependency.dependency(EventBus.class));
    private PlaceController placeController = injector.resolve(Dependency.dependency(PlaceController.class));


//    {
//        eventBus.addHandler(PlaceChangeEvent.class,
//                new SecurePlaceChangeRequestListener(
//                        injector.resolve(Dependency.dependency(SecurityFacade.class)),
//                        getPlaceController()
//                ));
//    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public PlaceController getPlaceController() {
        return placeController;
    }

    public ActivityFactory getActivityFactory() {
        return new SilkActivityFactory(injector);
    }

    static class SecureApplicationBundle extends BootstrapperBundle {

        @Override
        protected void bootstrap() {
            install(SimpleEventBusModule.class);
            install(AuthenticationMVPSilkModule.class);
            install(ShiroSilkModule.class);

            install(new BinderModule() {
                @Override
                protected void declare() {
                    bind(PlaceController.ConfirmationHandler.class).to(CurrentRootConfirmationHandler.class);
//                    bind(PlaceControllerImpl.class).toConstructor();;
                    bind(PlaceController.class).to(SecurePlaceController.class);
                    bind(SecurePlaceController.class).toConstructor();
//                    injectingInto(SecurePlaceController.class).bind(PlaceController.class).to(PlaceControllerImpl.class);
                    autobind(Role1Activity.class).toConstructor();;
                    autobind(Role2Activity.class).toConstructor();;
                    autobind(NoroleActivity.class).toConstructor();;
                    autobind(MenuActivity.class).toConstructor();;
                }
            });

        }


    }
}
