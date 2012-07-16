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

import sweforce.gui.ap.activity.AbstractActivity;
import sweforce.gui.display.Display;
import sweforce.gui.event.EventBus;
import sweforce.gui.view.AcceptsOneWidget;
import sweforce.vaadin.sample.secure.SecureApplication;

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
}
