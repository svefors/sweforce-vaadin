package sweforce.vaadin.security.place;

import sweforce.gui.place.Place;
import sweforce.vaadin.security.place.annotation.PlaceRequiresAuthentication;
import sweforce.vaadin.security.place.annotation.PlaceRequiresRoles;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/23/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceAuthenticationRequiredCheck {

    /**
     * return true if requires authentication or roles
     *
     * @param place
     * @return
     */
    public boolean isAuthenticationRequired(Place place) {
        boolean rolesRequired = false;
        boolean loginRequired = false;
        if (place.getClass().isAnnotationPresent(PlaceRequiresAuthentication.class)) {
            PlaceRequiresAuthentication placeRequiresAuthentication =
                    place.getClass().getAnnotation(PlaceRequiresAuthentication.class);
            loginRequired = placeRequiresAuthentication != null ? placeRequiresAuthentication.value() : false;
        }
        if (place.getClass().isAnnotationPresent(PlaceRequiresRoles.class)) {
            PlaceRequiresRoles placeRequiresRoles = place.getClass().getAnnotation(PlaceRequiresRoles.class);
            if (placeRequiresRoles.value() != null && placeRequiresRoles.value().length > 0)
                rolesRequired = true;
        }
        return rolesRequired || loginRequired;
    }

}
