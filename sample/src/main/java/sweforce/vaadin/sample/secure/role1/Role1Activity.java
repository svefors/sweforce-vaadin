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
package sweforce.vaadin.sample.secure.role1;

import sweforce.gui.activity.*;
import sweforce.gui.display.Display;
import sweforce.event.EventBus;
import sweforce.gui.place.Place;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class Role1Activity extends AbstractActivity {

    @Override
    public void start(Display panel, EventBus eventBus) {
        panel.setView(new Role1View());
    }


    public static PlaceMatchActivityMapping placeMatchActivityMapping =
                new PlaceMatchActivityMapping(PlaceMatch.clazz(Role1Place.class), Role1Activity.class);
}
