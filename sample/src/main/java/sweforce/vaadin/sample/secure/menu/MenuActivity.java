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
package sweforce.vaadin.sample.secure.menu;

import com.vaadin.ui.Button;
import sweforce.gui.ap.activity.AbstractActivity;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.PlaceController;
import sweforce.gui.ap.web.vaadin.IsVaadinWidget;
import sweforce.gui.event.EventBus;
import sweforce.gui.view.AcceptsOneWidget;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Place;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuActivity extends AbstractActivity {

    MenuView menuView = new MenuViewImpl();

    private PlaceController placeController;

    @Inject
    public MenuActivity(PlaceController placeController) {
        this.placeController = placeController;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(menuView);

        menuView.getNoroleBtn().addListener(new GotoPlaceClickListener(new NorolePlace()));
        menuView.getRole1Btn().addListener(new GotoPlaceClickListener(new Role1Place()));
        menuView.getRole2Btn().addListener(new GotoPlaceClickListener(new Role2Place()));
    }

    public static interface MenuView extends IsVaadinWidget {

        public Button getNoroleBtn();

        public Button getRole2Btn();

        public Button getRole1Btn();

    }

    public class GotoPlaceClickListener implements Button.ClickListener{

        private Place place;

        public GotoPlaceClickListener(Place place) {
            this.place = place;
        }

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            placeController.goTo(place);
        }
    }
}