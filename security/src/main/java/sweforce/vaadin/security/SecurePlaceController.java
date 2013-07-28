package sweforce.vaadin.security;

import sweforce.event.EventBus;
import sweforce.gui.place.*;
import sweforce.vaadin.security.activitiesandplaces.login.LoginPlace;
import sweforce.vaadin.security.place.PlaceAuthenticationRequiredCheck;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/26/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurePlaceController implements PlaceController {


    private final EventBus eventBus;

    private Place where = Place.NOWHERE;

    private ConfirmationHandler confirmationHandler;

    private final SecurityFacade securityFacade;

    private final PlaceAuthenticationRequiredCheck placeAuthenticationRequiredCheck =
            new PlaceAuthenticationRequiredCheck();

    public SecurePlaceController(EventBus eventBus, ConfirmationHandler confirmationHandler, SecurityFacade securityFacade) {
        this.eventBus = eventBus;
        this.confirmationHandler = confirmationHandler;
        this.securityFacade = securityFacade;
    }


    public Place getWhere() {
        return where;
    }

    public void goTo(final Place newPlace) {
        if (getWhere().equals(newPlace)) {
            return;
        }
        String warning = maybeGoTo(newPlace);
        if (warning == null) {
            if (!securityFacade.getSubject().isAuthorized(newPlace)) {

                goToAfterConfirmed(new LoginPlace(newPlace));
            } else {
                goToAfterConfirmed(newPlace);
            }
        } else {
            confirmationHandler.askForConfirmation(warning, new ConfirmationHandler.Listener() {
                @Override
                public void onConfirm() {
                    if (placeAuthenticationRequiredCheck.isAuthenticationRequired(newPlace)) {
                        goToAfterConfirmed(new LoginPlace(newPlace));
                    } else if (!securityFacade.getSubject()
                            .isAuthorized(newPlace)) {
                        //if the user is not authorized... ?
                        throw new IllegalStateException("user is not authorized");
                    } else {
                        goToAfterConfirmed(newPlace);
                    }
                }

                @Override
                public void onCancel() {
                    //do nothing
                }
            });
        }
    }

    private String maybeGoTo(Place newPlace) {
        PlaceChangeRequestEvent willChange = new PlaceChangeRequestEvent(newPlace);
        eventBus.fireEvent(willChange);
        String warning = willChange.getWarning();
        return warning;
    }

    protected void goToAfterConfirmed(Place newPlace) {
        this.where = newPlace;
        eventBus.fireEvent(new PlaceChangeEvent(newPlace));
    }


}
