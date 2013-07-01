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
package sweforce.vaadin.sample.secure;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sweforce.gui.activity.ActivityManagerModule;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.PlaceControllerModule;
import sweforce.gui.place.PlaceHistoryModule;
import sweforce.gui.vaadin.VaadinModule;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.security.SecureMvpModule;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutPlace;
import sweforce.vaadin.security.shiro.ShiroSecurityModule;

/**
 * Sample Application
 */
public class SecureApplication extends UI {

    private static Logger logger = LoggerFactory.getLogger(SecureApplication.class);

//    @Override
//    public Module[] getModules() {
//        return new Module[]{
//                new VaadinModule(this),
//                new ShiroSecurityModule(),
//                new SecureMvpModule(),
//                new PlaceHistoryModule(),
//                //in(region).at(place).invoke(Activity)
//                //at(RegistrationPlace.class).in(MenuRegion).do(Activity.class)
//                new RegionPlacesModule() {
//                    @Override
//                    protected void configurePlaces() {
//                        bindPlace(NorolePlace.class).in(Style1Layout.Region.MAIN).to(NoroleActivity.class);
//                        bindPlace(NorolePlace.class).in(Style1Layout.Region.TOOLBAR).to(MenuActivity.class);
//
//                        bindPlace(Role1Place.class).in(Style1Layout.Region.MAIN).to(Role1Activity.class);
//                        bindPlace(Role1Place.class).in(Style1Layout.Region.TOOLBAR).to(MenuActivity.class);
//
//                        bindPlace(Role2Place.class).in(Style1Layout.Region.MAIN).to(Role2Activity.class);
//                        bindPlace(Role2Place.class).in(Style1Layout.Region.TOOLBAR).to(MenuActivity.class);
//
//                        bind(Component.class).annotatedWith(GuicedUI.RootComponent.class).to(Style1Layout.class);
//                        bind(Place.class).annotatedWith(DefaultPlace.class).toInstance(new NorolePlace());
//
//                    }
//                }
//        };
//    }


    @Override
    protected void init(VaadinRequest request) {
        Injector uiRootInjector = Guice.createInjector(
                new VaadinModule(SecureApplication.this),
                new ShiroSecurityModule(),
                new SecureMvpModule(),
                new PlaceHistoryModule(NorolePlace.class, Role1Place.class, Role1Place.class, LoginPlace.class,
                        LogoutPlace.class),
                new PlaceControllerModule(),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                }
        );
        Injector mainRegionInjector = uiRootInjector.createChildInjector(new ActivityManagerModule(),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(ActivityMapper.class)
                    }
                }
        );
        //To change body of implemented methods use File | Settings | File Templates.
    }






}
