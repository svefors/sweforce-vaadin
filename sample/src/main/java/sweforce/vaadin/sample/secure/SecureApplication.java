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
import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import sweforce.event.EventBus;
import sweforce.event.SimpleEventBus;
import sweforce.gui.display.Display;
import sweforce.gui.place.*;
import sweforce.gui.vaadin.VaadinPageHistorian;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.bind.SecureApplicationBundle;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.security.place.SecurePlaceController;

/**
 * Sample Application
 */
public class SecureApplication extends UI {

    private static Logger logger = LoggerFactory.getLogger(SecureApplication.class);

    @Override
    protected void init(VaadinRequest request) {
        Injector injector = Bootstrap.injector(SecureApplicationBundle.class);

        Style1Layout style1Layout = new Style1Layout();
        this.setContent(style1Layout);

        Display mainDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.MAIN);
        Display leftDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.SPLIT_LEFT);
        Display rightDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.SPLIT_RIGHT);
        Display toolbarDisplay = style1Layout.getDisplay(Style1Layout.MyRegion.TOOLBAR);



    /*
    goal is to make the application
    - testable
    - no magic

I want a dsl to compose the app!


(Style1Layout.MyRegion.MAIN).placeClass


     */
        //move this to bind

//        PlaceHistoryHandler historyHandler = injector.resolve(Dependency.dependency(PlaceHistoryHandler.class));
//        historyHandler.handleCurrentFragment();

    }


}
