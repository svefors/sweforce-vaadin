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
import sweforce.gui.ap.place.Place;
import sweforce.vaadin.layout.AbstractLayoutPlaceActivityMapper;
import sweforce.vaadin.layout.places.CenterPlace;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.login.LoginActivity;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutActivity;
import sweforce.vaadin.security.logout.LogoutPlace;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenterActivityMapper extends AbstractLayoutPlaceActivityMapper.Center{

    private LoginActivity loginActivity;

    private NoroleActivity noroleActivity;

    private Role1Activity role1Activity;

    private Role2Activity role2Activity;

    private LogoutActivity logoutActivity;

    private Map<Class<? extends CenterPlace>, AbstractActivity> placeActivityMap =
            new HashMap<Class<? extends CenterPlace>, AbstractActivity>();
    @Inject
    public CenterActivityMapper(LoginActivity loginActivity, NoroleActivity noroleActivity, Role1Activity role1Activity, Role2Activity role2Activity, LogoutActivity logoutActivity) {
        this.loginActivity = loginActivity;
        this.noroleActivity = noroleActivity;
        this.role1Activity = role1Activity;
        this.role2Activity = role2Activity;
        this.logoutActivity = logoutActivity;
        placeActivityMap.put(NorolePlace.class, noroleActivity);
        placeActivityMap.put(Role1Place.class, role1Activity);
        placeActivityMap.put(Role2Place.class, role2Activity);
    }

    @Override
    public Activity getActivity(Place place) {
        if (place.getClass() == LoginPlace.class)
            return loginActivity;
        if (place.getClass() == LogoutPlace.class)
            return logoutActivity;
        if (place instanceof Role2Place){
            role2Activity.setSomeValue(((Role2Place) place).getSomeValue());
            return role2Activity;
        }else{
            return super.getActivity(place);
        }

    }

    @Override
    protected Activity getInternalActivity(CenterPlace place) {
        return placeActivityMap.get(place.getClass());
    }
}
