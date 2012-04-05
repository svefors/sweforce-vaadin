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

import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;
import sweforce.vaadin.layout.places.CenterPlace;
import sweforce.vaadin.layout.places.NorthPlace;
import sweforce.vaadin.security.place.PlaceRequiresRoles;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Prefix("role2")
@PlaceRequiresRoles("user_role_2")
public class Role2Place extends Place implements CenterPlace, NorthPlace {

    public static class Tokenizer implements PlaceTokenizer<Role2Place>{
        @Override
        public Role2Place getPlace(String token) {
            return new Role2Place();
        }

        @Override
        public String getToken(Role2Place place) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
