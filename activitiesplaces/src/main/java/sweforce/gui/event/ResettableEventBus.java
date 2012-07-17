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
package sweforce.gui.event;

import java.util.HashSet;
import java.util.Set;

/**
 * Wraps {com.google.web.bindery.event.shared.ResettableEventBus} for legacy
 * compatibility.
 */
public class ResettableEventBus implements EventBus {

    private final EventBus real;

    private final Set<HandlerRegistration> handlerRegistrations = new HashSet<HandlerRegistration>();

    public ResettableEventBus(EventBus eventBus) {
        real = eventBus;
    }

    @Override
    public <H extends EventHandler> HandlerRegistration addHandler(Class<? extends Event<H>> eventClass, H handler) {

        final HandlerRegistration registration = real.addHandler(eventClass, handler);
        return doRegisterHandler(registration);
    }

    @Override
    public <H extends EventHandler> void fireEvent(Event<H> event) {
        real.fireEvent(event);
    }

    public void removeHandlers() {
        for (HandlerRegistration registration : handlerRegistrations) {
            registration.removeHandler();
        }
        handlerRegistrations.clear();
    }

    private HandlerRegistration doRegisterHandler(final HandlerRegistration registration) {
        handlerRegistrations.add(registration);
        return new HandlerRegistration() {
            public void removeHandler() {
                doUnregisterHandler(registration);
            }
        };
    }

    private void doUnregisterHandler(HandlerRegistration registration) {
        synchronized (ResettableEventBus.this) {
            if (handlerRegistrations.contains(registration)) {
                registration.removeHandler();
                handlerRegistrations.remove(registration);
            }
        }
    }

    /**
     * Visible for testing.
     */
    public int getRegistrationSize() {
        return handlerRegistrations.size();
    }
}
