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
package sweforce.vaadin.security.login;


import org.apache.shiro.authc.*;
import sweforce.gui.ap.activity.AbstractActivity;
import sweforce.gui.ap.place.Place;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.SystemThrowableEvent;
import sweforce.gui.view.AcceptsOneWidget;
import sweforce.vaadin.security.SecurityFacade;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 2/21/12
 * Time: 8:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends AbstractActivity implements LoginView.Presenter {


    private EventBus eventBus;

    private final LoginView loginView;

    private final SecurityFacade securityFacade;

    private Place wantedPlace;

    @Inject
    public LoginActivity(LoginView loginView, SecurityFacade securityFacade) {
        this.loginView = loginView;
        this.securityFacade = securityFacade;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        this.eventBus = eventBus;
        panel.setWidget(loginView);
        loginView.setPresenter(this);
    }

    public void setWantedPlace(Place wantedPlace) {
        this.wantedPlace = wantedPlace;
    }

    @Override
    public void login(String username, String password) {
        try {
            securityFacade.getSubject().login(username, password);
            eventBus.fireEvent(new UserLoginSuccessEvent(wantedPlace));
        } catch (UnknownAccountException uae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (IncorrectCredentialsException ice) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (LockedAccountException lae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (ExcessiveAttemptsException eae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (AuthenticationException ae) {
            eventBus.fireEvent(new UserLoginFailedEvent());
        } catch (Exception ex) {
            eventBus.fireEvent(new SystemThrowableEvent(ex));
        }
    }
}
