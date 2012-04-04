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

/**
 * Optional delegate in charge of Window-related events. Provides nice
 * isolation for unit testing, and allows customization of confirmation
 * handling.
 */
public interface ConfirmationHandler {

    boolean confirm(String message);

//    /**
//     * Adds a {@link ClosingHandler} to the Delegate.
//     *
//     * @param handler a {@link ClosingHandler} instance
//     * @return a {@link HandlerRegistration} instance
//     */
//    HandlerRegistration addWindowClosingHandler(ClosingHandler handler);
//
//    /**
//     * Called to confirm a window closing event.
//     *
//     * @param message a warning message
//     * @return true to allow the window closing
//     */
//    boolean confirm(String message);
}
