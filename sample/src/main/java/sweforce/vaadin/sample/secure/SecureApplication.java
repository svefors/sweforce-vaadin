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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.vaadin.Application;
import sweforce.gui.VaadinGuiModule;
import sweforce.gui.ap.activity.ActivityManager;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.DefaultPlaceController;
import sweforce.gui.ap.place.PlaceFragmentHandler;
import sweforce.gui.ap.place.mapper.PlaceFragmentMapper;
import sweforce.gui.ap.place.mapper.PlaceFragmentMapperFactory;
import sweforce.gui.ap.web.vaadin.VaadinBrowserWindow;
import sweforce.gui.event.EventBus;
import sweforce.vaadin.layout.LayoutContainer;
import sweforce.vaadin.sample.secure.activitymapper.CenterActivityMapper;
import sweforce.vaadin.sample.secure.activitymapper.NorthActivityMapper;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.SecureMvpModule;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.login.UserLoginSuccessEvent;
import sweforce.vaadin.security.logout.LogoutPlace;
import sweforce.vaadin.security.place.SecurePlaceController;
import sweforce.vaadin.security.shiro.ShiroSecurityModule;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/1/12
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecureApplication extends Application implements UserLoginSuccessEvent.Handler {

    @Override
    public void init() {
        Injector injector = Guice.createInjector(new ShiroSecurityModule(), new SecureMvpModule(this));

        VaadinBrowserWindow vaadinBrowserWindow = injector.getInstance(VaadinBrowserWindow.class);
        this.addWindow(vaadinBrowserWindow);
        final PlaceFragmentMapper placeFragmentMapper = new PlaceFragmentMapperFactory().create(
                LoginPlace.class, LogoutPlace.class, Role1Place.class, NorolePlace.class, Role2Place.class);
        final PlaceFragmentHandler placeFragmentHandler = new PlaceFragmentHandler(placeFragmentMapper, vaadinBrowserWindow);
        final Place defaultPlace = new NorolePlace();
        placeController = injector.getInstance(SecurePlaceController.class);
        eventBus = injector.getInstance(EventBus.class);
        placeFragmentHandler.register(placeController,
                eventBus, defaultPlace);

        eventBus.addHandler(UserLoginSuccessEvent.class, this);

        LayoutContainer layoutContainer = new LayoutContainer();
        layoutContainer.init();
        vaadinBrowserWindow.setContent(layoutContainer);

        final ActivityManager centerActivityManager = new ActivityManager(
                injector.getInstance(CenterActivityMapper.class),
                injector.getInstance(EventBus.class)
                );
        centerActivityManager.setDisplay(layoutContainer.getCenterAcceptsOneWidget());

        final ActivityManager northActivityManager = new ActivityManager(
                injector.getInstance(NorthActivityMapper.class),
                injector.getInstance(EventBus.class)
                );
        northActivityManager.setDisplay(layoutContainer.getNorthAcceptsOneWidget());
        placeFragmentHandler.handleCurrentFragment();



    }

    private EventBus eventBus;

    private DefaultPlaceController placeController;

    @Override
    public void onAfterLogin(Place wantedPlace) {
        if (wantedPlace == null)
            wantedPlace = new NorolePlace();
        placeController.goTo(wantedPlace);
    }
}
