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
package sweforce.gui.activity;


import sweforce.gui.place.Place;
import sweforce.gui.display.Display;
import sweforce.event.EventBus;

/**
 * Implemented by objects that control a piece of user interface, with a life
 * cycle managed by an {@link MultiThreadedActivityManager}, in response to
 * {@link sweforce.gui.place.PlaceChangeEvent} events as the user
 * navigates through the app.
 */
public interface Activity {
    /**
     * Called when the user is trying to navigate away from this activity.
     *
     * @return A message to display to the user, e.g. to warn of unsaved work, or
     *         null to say nothing
     */
    String mayStop();

    /**
     * Called when {@link #start} has not yet replied to its callback, but the
     * user has lost interest.
     */
    void onCancel();

    /**
     * Called when the Activity's widget has been removed from view. All event
     * handlers it registered will have been removed before this method is called.
     */
    void onStop();

    /**
     * Called when the Activity should ready its widget for the user. When the
     * widget is ready (typically after an RPC response has been received),
     * receiver should present it by calling
     * {@link Display#setView(sweforce.gui.display.View)} on the given panel.
     * <p/>
     * Any handlers attached to the provided event bus will be de-registered when
     * the activity is stopped, so activities will rarely need to hold on to the
     * {@link sweforce.event.HandlerRegistration HandlerRegistration}
     * instances returned by {@link EventBus#addHandler}.
     *
     * @param panel    the panel to display this activity's widget when it is ready
     * @param eventBus the event bus
     */
    void start(Display panel, EventBus eventBus);

    /**
     * If the activity can be configured with parameters from the place
     */
    public interface ConfigurableFromPlace<P extends Place> {

        public void withPlace(P place);

    }

}
