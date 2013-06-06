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


import sweforce.gui.activity.AbstractActivity;
import sweforce.gui.display.Display;
import sweforce.event.EventBus;
import sweforce.vaadin.security.SecurityFacade;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/18/12
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogoutActivity extends AbstractActivity {


    private final SecurityFacade securityFacade;

    @Inject
    public LogoutActivity(SecurityFacade securityFacade) {
        this.securityFacade = securityFacade;
    }


    @Override
    public void start(Display acceptsOneWidget, EventBus eventBus) {
        securityFacade.getSubject().logout();
        eventBus.fireEvent(new UserLogoutEvent());
    }

}
