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
package sweforce.vaadin.security.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import sweforce.gui.ap.place.Place;
import sweforce.vaadin.security.SecurityFacade;
import sweforce.vaadin.security.Subject;
import sweforce.vaadin.security.place.PlaceRequiresAuthentication;
import sweforce.vaadin.security.place.PlaceRequiresRoles;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/1/12
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShiroSecurityFacade implements SecurityFacade {

    @Override
    public Subject getSubject() {
        final org.apache.shiro.subject.Subject subject =  SecurityUtils.getSubject();
        return new Subject() {
            @Override
            public boolean hasRole(String rolename) {
                return subject.hasRole(rolename);
            }

            @Override
            public boolean isAuthenticated() {
                return subject.isAuthenticated();
            }

            @Override
            public boolean isAuthorized(Place place) {
                if (!place.getClass().isAnnotationPresent(PlaceRequiresRoles.class)) {
                    return true;
                } else {
                    PlaceRequiresRoles placeRequiresRoles = place.getClass().getAnnotation(PlaceRequiresRoles.class);
                    for (String rolename : placeRequiresRoles.value()) {
                        if (hasRole(rolename))
                            return true;
                    }
                }
                return false;
            }

            @Override
            public void login(String username, String password) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
            }

            @Override
            public void logout() {
                subject.logout();
            }
        };

    }

    @Override
    public boolean isAuthenticationRequired(Place place) {
        PlaceRequiresAuthentication placeRequiresAuthentication = place.getClass().getAnnotation(PlaceRequiresAuthentication.class);
        return placeRequiresAuthentication != null ? placeRequiresAuthentication.value() : false;
    }
}
