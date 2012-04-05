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
package sweforce.vaadin.security;

import com.google.inject.AbstractModule;
import com.vaadin.Application;
import sweforce.gui.ap.place.ConfirmationHandler;
import sweforce.gui.ap.place.DefaultPlaceController;
import sweforce.gui.ap.place.PlaceController;
import sweforce.gui.ap.web.BrowserWindow;
import sweforce.gui.ap.web.vaadin.VaadinBrowserWindow;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.SimpleEventBus;
import sweforce.vaadin.security.login.LoginActivity;
import sweforce.vaadin.security.login.LoginView;
import sweforce.vaadin.security.login.LoginViewImpl;
import sweforce.vaadin.security.logout.LogoutActivity;
import sweforce.vaadin.security.place.SecurePlaceController;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 2/23/12
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */

public class SecureMvpModule extends AbstractModule{

    private final Application application;

    public SecureMvpModule(Application application) {
        this.application = application;
    }

    @Override
    protected void configure() {
        bind(BrowserWindow.class).to(VaadinBrowserWindow.class);
        bind(EventBus.class).to(SimpleEventBus.class);
        bind(Application.class).toInstance(this.application);
        bind(ConfirmationHandler.class).to(VaadinBrowserWindow.class);
        bind(LoginView.class).to(LoginViewImpl.class);
        bind(LoginActivity.class);
        bind(LogoutActivity.class);
        bind(PlaceController.class).to(SecurePlaceController.class);

    }

}
