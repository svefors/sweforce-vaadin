/*
 * Copyright 2010 Mats Svefors
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
package sweforce.gui.place;

import sweforce.event.EventBus;


/**
 * In charge of the user's place/location in the app.
 * Copied and modified from GWt PLace Controller
 */
public class PlaceControllerImpl implements PlaceController {

    private final EventBus eventBus;

    private Place where = Place.NOWHERE;

    private ConfirmationHandler delegate;

    public PlaceControllerImpl(EventBus eventBus, ConfirmationHandler confirmationHandler) {
        this.eventBus = eventBus;
        this.delegate = confirmationHandler;
    }

    public Place getWhere() {
        return where;
    }

    public void goTo(final Place newPlace) {
        if (getWhere().equals(newPlace)) {
            return;
        }
        String warning = maybeGoTo(newPlace);
        if (warning == null){
            goToAfterConfirmed(newPlace);
        } else{
          delegate.askForConfirmation(warning, new ConfirmationHandler.Listener(){
              @Override
              public void onConfirm() {
                  goToAfterConfirmed(newPlace);
              }

              @Override
              public void onCancel() {
                  //do nothing
              }
          });
        }
    }

    private String maybeGoTo(Place newPlace) {
        PlaceChangeRequestEvent willChange = new PlaceChangeRequestEvent(newPlace);
        eventBus.fireEvent(willChange);
        String warning = willChange.getWarning();
        return warning;
    }

    protected void goToAfterConfirmed(Place newPlace){
        this.where = newPlace;
        eventBus.fireEvent(new PlaceChangeEvent(newPlace));
    }
}
