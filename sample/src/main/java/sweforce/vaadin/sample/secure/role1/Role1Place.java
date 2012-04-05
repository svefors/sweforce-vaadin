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
 * Time: 12:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Prefix("role1")
@PlaceRequiresRoles("user_role_1")
public class Role1Place extends Place implements CenterPlace, NorthPlace {



    public static class Tokenizer implements PlaceTokenizer<Role1Place>{
        @Override
        public Role1Place getPlace(String token) {
            return new Role1Place();
        }

        @Override
        public String getToken(Role1Place place) {
            return "";
        }
    }

}
