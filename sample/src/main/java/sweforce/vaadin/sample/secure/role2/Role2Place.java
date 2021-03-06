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

import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.Prefix;
import sweforce.vaadin.security.place.annotation.PlaceRequiresRoles;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Prefix("role2")
@PlaceRequiresRoles("user_role_2")
public class Role2Place extends Place {

    private String someValue;

    public Role2Place(String someValue) {
        this.someValue = someValue;
    }

    public String getSomeValue() {
        return someValue;
    }

    public static class Tokenizer implements PlaceTokenizer<Role2Place>{
        @Override
        public Role2Place getPlace(String token) {
            return new Role2Place(token);
        }

        @Override
        public String getToken(Role2Place place) {
            return place.someValue;
        }
    }

}
