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
package sweforce.gui.ap.web.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.Window;
import sweforce.gui.ap.web.BrowserWindow;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/4/12
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class VaadinBrowserWindow implements BrowserWindow {

    private final Application application;

    private final UriFragmentUtility uriFragmentUtility = new com.vaadin.ui.UriFragmentUtility();

    public VaadinBrowserWindow(Application application) {
        this.application = application;
        application.addWindow(new Window());

    }

    @Override
    public void setContent(ComponentContainer componentContainer) {
        application.getMainWindow().addComponent(componentContainer);
        componentContainer.addComponent(uriFragmentUtility);
    }

    @Override
    public UriFragmentUtility getUriFragmentUtility() {
        return uriFragmentUtility;
    }
}
