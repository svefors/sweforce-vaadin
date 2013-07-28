/*
 * Copyright 2012 Mats Svefors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package sweforce.vaadin.security.activitiesandplaces.login;


import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.gui.activity.*;
import sweforce.gui.display.Display;
import sweforce.event.SystemThrowableEvent;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceChangeEvent;
import sweforce.gui.place.PlaceController;
import sweforce.vaadin.security.SecurityFacade;

import javax.inject.Inject;

/**
 *
 */
public class LoginActivity extends AbstractActivity implements LoginView.Presenter, ActivityMapper, UserLoginSuccessEvent.Handler, Activity.ConfigurableFromPlace<LoginPlace> {


    private EventBus eventBus;

    private final LoginView loginView;

    private final SecurityFacade securityFacade;

    private PlaceController placeController;

//    private HandlerRegistration loginSuccessHandlerRegistration;

    @Inject
    public LoginActivity(LoginView loginView, SecurityFacade securityFacade, PlaceController placeController) {
        this.loginView = loginView;
        this.securityFacade = securityFacade;
        this.placeController = placeController;
    }

    @Override
    public void start(Display panel, EventBus eventBus) {
        this.eventBus = eventBus;
//        this.loginSuccessHandlerRegistration = eventBus.addHandler(UserLoginSuccessEvent.class, this);
        panel.setView(loginView);
        loginView.setPresenter(this);
    }

    @Override
    public void withPlace(LoginPlace place) {
        this.secureWantedPlace = place.getWantedPlace();
    }

    @Override
    public void onStop() {
        this.secureWantedPlace = null;
//        this.loginSuccessHandlerRegistration.removeHandler();
    }

    private Place secureWantedPlace;

    public void setSecureWantedPlace(Place secureWantedPlace) {
        this.secureWantedPlace = secureWantedPlace;
    }

    @Override
    public void onAfterLogin() {
        if (secureWantedPlace != null)
            placeController.goTo(secureWantedPlace);
        else {
            eventBus.fireEvent(new PlaceChangeEvent(null));
        }
    }

    @Override
    public void login(String username, String password) {
        try {
            securityFacade.getSubject().login(username, password);
            eventBus.fireEvent(new UserLoginSuccessEvent());
        } catch (SecurityFacade.UnknownAccountException uae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.IncorrectCredentialsException ice) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.LockedAccountException lae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.ExcessiveAttemptsException eae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (SecurityFacade.AuthenticationException ae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (Exception ex) {
            ex.printStackTrace();
            eventBus.fireEvent(new SystemThrowableEvent(ex));
        }
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof LoginPlace)
            return this;
        return null;
    }

    public static PlaceMatchActivityMapping placeMatchActivityMapping = new PlaceMatchActivityMapping(
            PlaceMatch.clazz(LoginPlace.class),
            LoginActivity.class);

}
