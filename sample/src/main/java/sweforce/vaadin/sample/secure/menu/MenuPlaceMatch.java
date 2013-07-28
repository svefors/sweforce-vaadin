package sweforce.vaadin.sample.secure.menu;

import sweforce.gui.activity.PlaceMatch;
import sweforce.gui.place.Place;
import sweforce.vaadin.security.activitiesandplaces.login.LoginPlace;
import sweforce.vaadin.security.activitiesandplaces.logout.LogoutPlace;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuPlaceMatch extends PlaceMatch {

    @Override
    public boolean matches(Place place) {
        return !(
                (place instanceof LoginPlace) ||
                        (place instanceof LogoutPlace)
        );
    }
}
