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
package sweforce.vaadin.sample.secure.role2;

import sweforce.event.EventBus;
import sweforce.gui.activity.AbstractActivity;
import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.display.Display;
import sweforce.gui.place.Place;
import sweforce.vaadin.sample.secure.role1.Role1Place;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Role2Activity extends AbstractActivity {

    private String someValue;

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    @Override
    public void start(Display panel, EventBus eventBus) {
        Role2View role2View = new Role2View();
        if(someValue!= null)
            role2View.getLabel_1().setValue("Parameter was: " + someValue);
        panel.setView(role2View);
    }

    public static class ActivityMapper implements sweforce.gui.activity.ActivityMapper {
        @Override
        public Activity getActivity(Place place) {
            if(place instanceof Role2Place)
                return new Role2Activity();
            else
                return null;
        }
    }
}
