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

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sweforce.event.EventBus;
import sweforce.gui.activity.ActivityMapperWithFactory;
import sweforce.gui.activity.SingleThreadedActivityManager;

import sweforce.gui.place.PlaceHistoryHandler;
import sweforce.gui.place.PlaceHistoryMapper;
import sweforce.gui.place.PlaceHistoryMapperBuilder;
import sweforce.gui.vaadin.VaadinPageHistorian;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;

import sweforce.vaadin.security.activitiesandplaces.login.LoginActivity;
import sweforce.vaadin.security.activitiesandplaces.login.LoginPlace;
import sweforce.vaadin.security.activitiesandplaces.login.UserLoginSuccessEvent;
import sweforce.vaadin.security.activitiesandplaces.logout.LogoutPlace;


/**
 * Sample Application
 */
public class SecureApplication extends UI {

    private static Logger logger = LoggerFactory.getLogger(SecureApplication.class);


    @Override
    protected void init(VaadinRequest request) {

        Style1Layout style1Layout = new Style1Layout();
        this.setContent(style1Layout);
        final ClientFactory clientFactory = new ClientFactory();

        EventBus eventBus = clientFactory.getEventBus();

        /*
        Main section
         */
        ActivityMapperWithFactory mainActivityMapper = new ActivityMapperWithFactory(clientFactory.getActivityFactory());
        mainActivityMapper.add(NoroleActivity.placeMatchActivityMapping);
        mainActivityMapper.add(Role1Activity.placeMatchActivityMapping);
        mainActivityMapper.add(Role2Activity.placeMatchActivityMapping);
        mainActivityMapper.add(LoginActivity.placeMatchActivityMapping);
        mainActivityMapper.add(LoginActivity.placeMatchActivityMapping);
        SingleThreadedActivityManager mainActivityManager =
                new SingleThreadedActivityManager(mainActivityMapper, eventBus);
        mainActivityManager.setDisplay(style1Layout.getDisplay(Style1Layout.MyRegion.MAIN));

        /*
        Toolbar/Menu section
        */
        ActivityMapperWithFactory toolbarActivityMapper = new ActivityMapperWithFactory(clientFactory.getActivityFactory());
        toolbarActivityMapper.add(MenuActivity.placeMatchActivityMapping);
        SingleThreadedActivityManager toolbarActivityManager =
                new SingleThreadedActivityManager(toolbarActivityMapper, eventBus);
        toolbarActivityManager.setDisplay(style1Layout.getDisplay(Style1Layout.MyRegion.TOOLBAR));

        PlaceHistoryMapper placeHistoryMapper = new PlaceHistoryMapperBuilder()
                .add(Role1Place.class)
                .add(Role2Place.class)
                .add(LoginPlace.class)
                .add(LogoutPlace.class)
                .add(NorolePlace.class).build();
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper,
                new VaadinPageHistorian(this.getPage()));

        placeHistoryHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new Role1Place());

        eventBus.addHandler(UserLoginSuccessEvent.class, new UserLoginSuccessEvent.Handler() {
            @Override
            public void onAfterLogin() {
                clientFactory.getPlaceController().goTo(new Role1Place());
            }
        });
        placeHistoryHandler.handleCurrentFragment();
    }


}
