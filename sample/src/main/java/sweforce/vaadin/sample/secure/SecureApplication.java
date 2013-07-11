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
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;
import sweforce.gui.RegionalActivitiesAndPlacesContainer;
import sweforce.gui.display.Display;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.bind.SecureApplicationBundle;
import sweforce.vaadin.sample.secure.menu.ToolbarPlugin;
import sweforce.vaadin.sample.secure.menu.ToolbarPlaceMatch;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.SecurityActivitiesFactory;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutPlace;

/**
 * Sample Application
 */
public class SecureApplication extends UI {

    private static Logger logger = LoggerFactory.getLogger(SecureApplication.class);


    @Override
    protected void init(VaadinRequest request) {

        Injector injector = Bootstrap.injector(SecureApplicationBundle.class);

        RegionalActivitiesAndPlacesContainer container = injector.resolve(Dependency.dependency(
                RegionalActivitiesAndPlacesContainer.class));


//        container.getPrefixPlaceTokenizerConfiguration()
//                .prefix("role1").useTokenizer(new Role1Place.Tokenizer())
//                .prefix("role2").useTokenizer(new Role2Place.Tokenizer())
//                .prefix("norole").useTokenizer(new NorolePlace.Tokenizer())
//                .prefix("login").useTokenizer(new LoginPlace.Tokenizer())
//                .prefix("logout").useTokenizer(new LogoutPlace.Tokenizer());

//        container.configureActivities(Style1Layout.MyRegion.MAIN)
//                .match(Role1Place.class).use(new Role1Activity())
//                .match(Role2Place.class).use(new Role2Activity())
//                .match(NorolePlace.class).use(new NoroleActivity())
//                .match(LoginPlace.class).use(injector.resolve(Dependency.dependency(SecurityActivitiesFactory.class)))
//                .match(LogoutPlace.class).use(injector.resolve(Dependency.dependency(SecurityActivitiesFactory.class)));


        /**
         *
        who should know what?
         The layout knows what regions it has
         The "container" know what regions it has

         To make it possible to replace the layout,
           the views provided by the activities need to be placed in the new layout but the activities
           should not have to be restarted.
           to achieve reuse of activities between different types of UIs(mobile vs web)? don't let the framework handle this.

         The RootComponent could have a presenter that is registered to layoutChangesEvent and modifies something. This presenter is
         tightly coupled to the RootComponent, but can be injected into the RootComponent.
         The layout can't decide to support fewer/different regions then? The activities would have to be reconfigured. Which might not
         be such a big deal, but it should happen outside of the framework.




           the activity managers need to be disposed.
           the historyHandler needs to handle the currentHistory


         * What needs to happen now?
         * Component rootlayout = injector.resolve(ROOTLAYOUT)
         * this.setContent(rootLayout)
         *
         * attach() --> presenter.setDisplay(display)
         *  {
         *   --> display.getRegions() iterate over and: activityMapper.setDisplay(subDisplay)
         *   --> historyHandler.handleCurrentFragment();
         *  }
         * so, inject the presenter into the Layout
         * later, detach --> presenter.setDisplay(null) --> unregister listeners
         *
         * but... also need the activitymanager and activitymapper to gel...
         */
        Style1Layout style1Layout = new Style1Layout();


        Display mainDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.MAIN);
        Display leftDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.SPLIT_LEFT);
        Display rightDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.SPLIT_RIGHT);
        Display toolbarDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.TOOLBAR);

        /*
        bind(named("rootLayout), Component.class).to(Style1Layout.class);
         */
        this.setContent(style1Layout);

    }


}
