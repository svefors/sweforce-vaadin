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

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.Window;
import org.vaadin.dialogs.ConfirmDialog;
import sweforce.gui.ap.place.ConfirmationHandler;
import sweforce.gui.ap.web.BrowserWindow;

import javax.inject.Inject;

/**
 * for now. only support one browser window per injector.
 * comparable with org.eclipse.swt.widgets.Display  ?
 */
public class VaadinBrowserWindow extends Window implements BrowserWindow, ConfirmationHandler {

    private final UriFragmentUtility uriFragmentUtility = new UriFragmentUtility();

    private final String id;

    @Inject
    public VaadinBrowserWindow() {
        super();
        //TODO, think about if this can be some form of counter
        id = "app1";
        uriFragmentUtility.setFragment(id);
    }

    @Override
    public void setContent(ComponentContainer componentContainer) {
        if (componentContainer != null) {
            super.setContent(componentContainer);
            componentContainer.addComponent(uriFragmentUtility);
        }
    }

    @Override
    public UriFragmentUtility getUriFragmentUtility() {
        return uriFragmentUtility;
    }


    @Override
    public void askForConfirmation(String message, final ConfirmationHandler.Listener listener) {
        ConfirmDialog.show(this, message, new ConfirmDialog.Listener() {

            @Override
            public void onClose(ConfirmDialog dialog) {
                if (dialog.isConfirmed()) {
                    listener.onConfirm();
                } else {
                    listener.onCancel();
                }
            }
        });
    }


}
