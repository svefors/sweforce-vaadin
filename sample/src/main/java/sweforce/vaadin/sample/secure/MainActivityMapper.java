package sweforce.vaadin.sample.secure;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.login.LoginActivity;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutActivity;
import sweforce.vaadin.security.logout.LogoutPlace;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityMapper implements ActivityMapper {

    private final LoginActivity loginActivity;

    private final LogoutActivity logoutActivity;

    public MainActivityMapper(LoginActivity loginActivity, LogoutActivity logoutActivity) {
        this.loginActivity = loginActivity;
        this.logoutActivity = logoutActivity;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof Role1Place)
            return new Role1Activity();
        if (place instanceof Role2Place)
            return new Role2Activity();
        if (place instanceof NorolePlace)
            return new NoroleActivity();
        if(place instanceof LoginPlace)
            return loginActivity;
        if(place instanceof LogoutPlace)
            return logoutActivity;
        return null;
    }
}
