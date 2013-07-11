package sweforce.vaadin.security;

import sweforce.gui.activity.Activity;
import sweforce.gui.place.Place;
import sweforce.vaadin.security.login.LoginActivity;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutActivity;
import sweforce.vaadin.security.logout.LogoutPlace;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityActivitiesFactory implements sweforce.gui.activity.registry.ActivityFactory {

    private LoginActivity loginActivity;

    private LogoutActivity logoutActivity;

    public SecurityActivitiesFactory(LoginActivity loginActivity, LogoutActivity logoutActivity) {
        this.loginActivity = loginActivity;
        this.logoutActivity = logoutActivity;
    }

    @Override
    public Activity create(Place place) {
        if (place instanceof LoginPlace)
            return loginActivity;
        if(place instanceof LogoutPlace)
            return logoutActivity;
        return null;
    }
}
