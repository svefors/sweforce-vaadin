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
package sweforce.vaadin.sample.secure.activitymapper;

import sweforce.gui.ap.activity.AbstractActivity;
import sweforce.gui.ap.activity.Activity;
import sweforce.vaadin.layout.AbstractLayoutPlaceActivityMapper;
import sweforce.vaadin.layout.places.NorthPlace;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Place;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class NorthActivityMapper extends AbstractLayoutPlaceActivityMapper.North {

    private Map<Class<? extends NorthPlace>, AbstractActivity> placeActivityMap =
            new HashMap<Class<? extends NorthPlace>, AbstractActivity>();

    @Inject
    public NorthActivityMapper(MenuActivity menuActivity) {
        placeActivityMap.put(NorolePlace.class, menuActivity);
        placeActivityMap.put(Role1Place.class, menuActivity);
        placeActivityMap.put(Role2Place.class, menuActivity);

    }

    @Override
    protected Activity getInternalActivity(NorthPlace place) {
        return placeActivityMap.get(place.getClass());
    }

}
