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
package sweforce.vaadin.security.login;


import sweforce.gui.ap.place.Place;
import sweforce.gui.event.Event;
import sweforce.gui.event.EventHandler;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/1/12
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserLoginSuccessEvent implements Event<UserLoginSuccessEvent.Handler> {

    private final Place wantedPlace;

    public UserLoginSuccessEvent(Place wantedPlace) {
        this.wantedPlace = wantedPlace;
    }

    public UserLoginSuccessEvent() {
        this.wantedPlace = null;
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onAfterLogin(wantedPlace);
    }

    public static interface Handler extends EventHandler {
        public void onAfterLogin(Place wantedPlace);
    }
}
