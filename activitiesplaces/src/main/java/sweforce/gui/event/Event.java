/*
 * Copyright 2011 Google Inc.
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

/**
 * Base Event object.
 * 
 * @param <H> interface implemented by handlers of this kind of event
 */
public interface Event<H extends EventHandler> {


  /**
   * Implemented by subclasses to to invoke their handlers in a type safe
   * manner. Intended to be called by {@link EventBus#fireEvent(Event)}
   *
   * @param handler handler
   * @see EventBus#fireEvent(Event)
   */
  public void dispatch(H handler);

}
