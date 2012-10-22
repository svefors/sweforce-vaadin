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
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sweforce.event.EventBus;
import sweforce.gui.ap.activity.ActivityManager;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.PlacesRunner;
import sweforce.gui.ap.place.controller.PlaceController;
import sweforce.gui.ap.place.history.PlaceHistoryModule;
import sweforce.gui.ap.vaadin.VaadinModule;
import sweforce.vaadin.layout.style2.Style2Layout;
import sweforce.vaadin.sample.secure.activitymapper.CenterActivityMapper;
import sweforce.vaadin.sample.secure.activitymapper.NorthActivityMapper;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.SecureMvpModule;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.login.UserLoginSuccessEvent;
import sweforce.vaadin.security.logout.LogoutPlace;
import sweforce.vaadin.security.shiro.ShiroSecurityModule;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/1/12
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecureApplication extends UI implements UserLoginSuccessEvent.Handler {

    private static Logger logger = LoggerFactory.getLogger(SecureApplication.class);


    private Injector injector;

    private class MyModule extends AbstractModule {
        @Override
        protected void configure() {
            //@Named("Default Place")
            bind(Place.class).annotatedWith(Names.named("Default Place")).toInstance(new NorolePlace());
            bind(NoroleActivity.class);
            bind(Role1Activity.class);
            bind(Role2Activity.class);
            bind(MenuActivity.class);
        }

        @Provides
        @Named(PlaceHistoryModule.NAMED_PLACE_CLASSES)
        Collection<Class<? extends Place>> providePlaceClasses() {
            //TODO write a classpath scanning mechanism
            List<Class<? extends Place>> places = new ArrayList<Class<? extends Place>>();
            places.add(LoginPlace.class);
            places.add(LogoutPlace.class);
            places.add(Role1Place.class);
            places.add(NorolePlace.class);
            places.add(Role2Place.class);
            return places;
        }


    }

    @Override
    protected void init(VaadinRequest request) {
//        this.getApplication().setRootPreserved(true);
        injector = Guice.createInjector(new VaadinModule(this),
                new ShiroSecurityModule(),
                new SecureMvpModule(),
                new PlaceHistoryModule(),
                new MyModule());

//        VaadinBrowserWindow vaadinBrowserWindow = injector.getInstance(VaadinBrowserWindow.class);

//        this.addWindow(vaadinBrowserWindow);

//        final PlaceHistoryMapper placeHistoryMapper = new PlaceHistoryMapperImpl(
//                LoginPlace.class, LogoutPlace.class, Role1Place.class, NorolePlace.class, Role2Place.class);
//        VaadinPageHistorian vaadinPageHistorian = new VaadinPageHistorian(this.getPage());
//        final PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper, vaadinPageHistorian);

//        final Place defaultPlace = new NorolePlace();

//        placeController = injector.getInstance(SecurePlaceController.class);
        EventBus eventBus = injector.getInstance(EventBus.class);
//        placeHistoryHandler.register(placeController,
//                eventBus, defaultPlace);

        eventBus.addHandler(UserLoginSuccessEvent.class, this);

//        LayoutContainer layoutContainer = new LayoutContainer();
//        layoutContainer.init();
//        this.setContent(layoutContainer);

        Style2Layout style2Layout = new Style2Layout();


        this.setContent(style2Layout );
        final ActivityManager centerActivityManager = new ActivityManager(
                injector.getInstance(CenterActivityMapper.class),
                injector.getInstance(EventBus.class)
        );

        centerActivityManager.setDisplay(style2Layout.getCenterDisplay());

        final ActivityManager northActivityManager = new ActivityManager(
                injector.getInstance(NorthActivityMapper.class),
                injector.getInstance(EventBus.class)
        );

        northActivityManager.setDisplay(style2Layout.getHeaderDisplay());

        PlacesRunner placesRunner = injector.getInstance(PlacesRunner.class);
        try {
            placesRunner.start();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void onAfterLogin(Place wantedPlace) {
        if (wantedPlace == null)
            wantedPlace = new NorolePlace();
        injector.getInstance(PlaceController.class).goTo(wantedPlace);

    }
}
