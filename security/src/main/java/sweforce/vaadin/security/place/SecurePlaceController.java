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
package sweforce.vaadin.security.place;


import sweforce.event.EventBus;
import sweforce.event.HandlerRegistration;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.controller.PlaceController;
import sweforce.vaadin.security.SecurityFacade;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.login.UserLoginSuccessEvent;

import javax.inject.Named;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/31/12
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.inject.Singleton
public class SecurePlaceController implements PlaceController, UserLoginSuccessEvent.Handler {

    private final SecurityFacade securityFacade;

    private Place defaultSecurePlace;

    private final PlaceController delegate;

    public static final String DELEGATE_NAME = "SecurePlaceController.DELEGATE";

    private HandlerRegistration loginSuccessHandlerRegistration;


    @javax.inject.Inject
    public SecurePlaceController(SecurityFacade securityFacade,
                                 @Named(SecurePlaceController.DELEGATE_NAME) PlaceController delegate,
                                 EventBus eventBus) {
        this.loginSuccessHandlerRegistration = eventBus.addHandler(UserLoginSuccessEvent.class, this);
        this.securityFacade = securityFacade;
        this.delegate = delegate;
    }

    public void setDefaultSecurePlace(Place defaultSecurePlace) {
        this.defaultSecurePlace = defaultSecurePlace;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        loginSuccessHandlerRegistration.removeHandler();
    }

    public void goTo(LoginPlace loginPlace){
        secureWantedPlace = loginPlace.getWantedPlace();
        delegate.goTo(loginPlace);
    }

    @Override
    public void goTo(Place wantedPlace) {
        if(wantedPlace instanceof LoginPlace){
            goTo((LoginPlace) wantedPlace);
        }else if (!securityFacade.isAuthenticationRequired(wantedPlace)){
            delegate.goTo(wantedPlace);
        }else if (!securityFacade.getSubject().isAuthenticated()){
            goTo(new LoginPlace(wantedPlace));
        }else if (securityFacade.getSubject().isAuthorized(wantedPlace)){
            delegate.goTo(wantedPlace);
        }else{
            //display not authorized information
        }
    }

    private Place secureWantedPlace;

    @Override
    public void setDefaultPlace(Place defaultPlace) {
        delegate.setDefaultPlace(defaultPlace);
    }

    @Override
    public void onAfterLogin() {
//        if(secureWantedPlace != null)
        Place wantedPlace = secureWantedPlace;
        secureWantedPlace = null;
        delegate.goTo(wantedPlace);
//        else{
//            delegate.goTo(defaultSecurePlace);
//        }

    }

    @Override
    public Place getWhere() {
        return delegate.getWhere();
    }
}
