/*
 * Copyright 2010 Google Inc.
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
package sweforce.gui.ap.place;


import sweforce.gui.event.Event;
import sweforce.gui.event.EventHandler;

/**
 * Event thrown when the user may go to a new place in the app, or tries to
 * leave it. Receivers can call {@link #setWarning(String)} request that the
 * user be prompted to confirm the change.
 */
public class PlaceChangeRequestEvent implements
        Event<PlaceChangeRequestEvent.Handler> {

    /**
     * Implemented by handlers of PlaceChangeRequestEvent.
     */
    public interface Handler extends EventHandler {
        /**
         * Called when a {@link sweforce.gui.ap.place.PlaceChangeRequestEvent} is fired.
         *
         * @param event the {@link sweforce.gui.ap.place.PlaceChangeRequestEvent}
         */
        void onPlaceChangeRequest(PlaceChangeRequestEvent event);
    }


    private String warning;

    private final Place newPlace;

    /**
     * Constructs a PlaceChangeRequestEvent for the given {@link Place}.
     *
     * @param newPlace a {@link Place} instance
     */
    public PlaceChangeRequestEvent(Place newPlace) {
        this.newPlace = newPlace;
    }


    /**
     * Returns the place we may navigate to, or null on window close.
     *
     * @return a {@link Place} instance
     */
    public Place getNewPlace() {
        return newPlace;
    }

    /**
     * Returns the warning message to show the user before allowing the place
     * change, or null if none has been set.
     *
     * @return the warning message as a String
     * @see #setWarning(String)
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Set a message to warn the user that it might be unwise to navigate away
     * from the current place, e.g. due to unsaved changes. If the user clicks
     * okay to that message, navigation will be canceled.
     * <p/>
     * Calling with a null warning is the same as not calling the method at all --
     * the user will not be prompted.
     * <p/>
     * Only the first non-null call to setWarning has any effect. That is, once
     * the warning message has been set it cannot be cleared.
     *
     * @param warning the warning message as a String
     * @see #getWarning()
     */
    public void setWarning(String warning) {
        if (this.warning == null) {
            this.warning = warning;
        }
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onPlaceChangeRequest(this);
    }
}
