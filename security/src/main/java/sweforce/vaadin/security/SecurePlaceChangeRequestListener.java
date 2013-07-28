package sweforce.vaadin.security;

import sweforce.gui.place.PlaceChangeEvent;
import sweforce.gui.place.PlaceChangeRequestEvent;
import sweforce.gui.place.PlaceController;
import sweforce.vaadin.security.activitiesandplaces.login.LoginPlace;
import sweforce.vaadin.security.place.PlaceAuthenticationRequiredCheck;

/**
 * Will listen for PlaceChangeRequestEvent and if the Place requested requires authentication,
 * it will ask PlaceController to go to the login place
 */
public class SecurePlaceChangeRequestListener implements PlaceChangeEvent.Handler {

    private final SecurityFacade securityFacade;

    private final PlaceController placeController;

    private final PlaceAuthenticationRequiredCheck placeAuthenticationRequiredCheck =
            new PlaceAuthenticationRequiredCheck();

    public SecurePlaceChangeRequestListener(SecurityFacade securityFacade, PlaceController placeController) {
        this.securityFacade = securityFacade;
        this.placeController = placeController;
    }

    @Override
    public void onPlaceChange(PlaceChangeEvent event) {
        if(placeAuthenticationRequiredCheck.isAuthenticationRequired(event.getNewPlace())){
            placeController.goTo(new LoginPlace(event.getNewPlace()));
        }else if(!securityFacade.getSubject()
                .isAuthorized(event.getNewPlace())){
            //if the user is not authorized... ?
            throw new IllegalStateException("user is not authorized");
        }
    }

}
