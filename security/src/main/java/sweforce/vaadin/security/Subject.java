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
package sweforce.vaadin.security;

import sweforce.gui.ap.place.Place;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/31/12
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Subject {

    public boolean hasRole(String rolename);

    public boolean isAuthenticated();

    public boolean isAuthorized(Place place);

    public void login(String username, String password);

    public void logout();
}
