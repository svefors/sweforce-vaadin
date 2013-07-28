package sweforce.vaadin.security;

import se.jbee.inject.bind.BinderModule;
import sweforce.vaadin.security.activitiesandplaces.login.LoginActivity;
import sweforce.vaadin.security.activitiesandplaces.login.LoginView;
import sweforce.vaadin.security.activitiesandplaces.login.LoginViewImpl;
import sweforce.vaadin.security.activitiesandplaces.logout.LogoutActivity;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/21/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationMVPSilkModule extends BinderModule {



    @Override
    protected void declare() {
        bind(LoginView.class).to(LoginViewImpl.class);
        autobind(LoginActivity.class).toConstructor();
        autobind(LogoutActivity.class).toConstructor();;
//        bind(SecurePlaceChangeRequestListener.class).toConstructor();

    }


}
