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
package sweforce.event;


/**
 * Dispatches {@link com.google.web.bindery.event.shared.Event}s to interested parties. Eases decoupling by allowing
 * objects to interact without having direct dependencies upon one another, and
 * without requiring event sources to deal with maintaining handler lists. There
 * will typically be one EventBus per application, broadcasting events that may
 * be of general interest.
 *
 * @see com.google.web.bindery.event.shared.SimpleEventBus
 * @see com.google.web.bindery.event.shared.ResettableEventBus
 * @see com.google.web.bindery.event.shared.testing.CountingEventBus
 */
public interface EventBus {



  /**
   * Adds an unfiltered handler to receive events of this type from all sources.
   * <p>
   * It is rare to call this method directly. More typically an {@link com.google.web.bindery.event.shared.Event}
   * subclass will provide a static <code>register</code> method, or a widget
   * will accept handlers directly.
   *
   * @param <H> The type of handler
   * @param eventClass the event type associated with this handler
   * @param handler the handler
   * @return the handler registration, can be stored in order to remove the
   *         handler later
   */
  public <H extends EventHandler> HandlerRegistration addHandler(Class<? extends Event<H>> eventClass, H handler);

//  /**
//   * Adds a handler to receive events of this type from the given source.
//   * <p>
//   * It is rare to call this method directly. More typically a {@link com.google.web.bindery.event.shared.Event}
//   * subclass will provide a static <code>register</code> method, or a widget
//   * will accept handlers directly.
//   *
//   * @param <H> The type of handler
//   * @param eventClass the event type associated with this handler
//   * @param source the source associated with this handler
//   * @param handler the handler
//   * @return the handler registration, can be stored in order to remove the
//   *         handler later
//   */Not needed?
//  public abstract <H extends EventHandler> HandlerRegistration addHandlerToSource(Class<? extends Event<H>> eventClass, Object source, H handler);

  /**
   * Fires the event from no source. Only unfiltered handlers will receive it.
   * <p>
   * Any exceptions thrown by handlers will be bundled into a
   * {@link com.google.web.bindery.event.shared.UmbrellaException} and then re-thrown after all handlers have
   * completed. An exception thrown by a handler will not prevent other handlers
   * from executing.
   *
   * @throws com.google.web.bindery.event.shared.UmbrellaException wrapping exceptions thrown by handlers
   *
   * @param event the event to fire
   */
  <H extends EventHandler> void fireEvent(Event<H> event);


}
