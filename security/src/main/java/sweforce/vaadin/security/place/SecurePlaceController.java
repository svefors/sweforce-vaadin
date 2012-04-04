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


import sweforce.gui.ap.place.ConfirmationHandler;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.PlaceController;
import sweforce.gui.event.EventBus;
import sweforce.vaadin.security.SecurityFacade;
import sweforce.vaadin.security.login.LoginPlace;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/31/12
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.inject.Singleton
public class SecurePlaceController extends PlaceController {

    private final SecurityFacade securityFacade;

    @javax.inject.Inject
    public SecurePlaceController(EventBus eventBus, ConfirmationHandler delegate, SecurityFacade securityFacade) {
        super(eventBus, delegate);
        this.securityFacade = securityFacade;
    }

    @Override
    public void goTo(Place newPlace) {
        //check if the place is secure
        if (!securityFacade.isAuthenticationRequired(newPlace)){
            super.goTo(newPlace);
        }else{
            if (!securityFacade.getSubject().isAuthenticated()){
                super.goTo(new LoginPlace(newPlace));
            }else{
                if (securityFacade.getSubject().isAuthorized(newPlace)){
                    super.goTo(newPlace);
                }else{
                    //display not authorized information
                }
            }
        }
    }

}
