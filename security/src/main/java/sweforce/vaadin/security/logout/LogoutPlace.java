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
package sweforce.vaadin.security.logout;


import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/18/12
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Prefix("logout")
public class LogoutPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<LogoutPlace> {
        @Override
        public LogoutPlace getPlace(String s) {
            return new LogoutPlace();
        }

        @Override
        public String getToken(LogoutPlace logoutPlace) {
            return "";
        }
    }
}
