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
package sweforce.gui.place;

import sweforce.event.Event;
import sweforce.event.EventHandler;
import sweforce.event.HandlerRegistration;
import sweforce.event.EventBus;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 *
 */
public class PlaceHistoryHandler {

    private PlaceController placeController;

    private final PlaceHistoryMapper mapper;

    private final Historian historian;

    private final Provider<Place> defaultPlaceProvider;

    @Inject
    public PlaceHistoryHandler(PlaceHistoryMapper mapper, Historian historian, @DefaultPlace Provider<Place> defaultPlaceProvider) {
        this.mapper = mapper;
        this.historian = historian;
        this.defaultPlaceProvider = defaultPlaceProvider;
    }

    /**
     *
     * @param placeController
     * @param eventBus
     * @return
     */
    public HandlerRegistration register(PlaceController placeController, EventBus eventBus) {
        this.placeController = placeController;
        final HandlerRegistration placeReg =
                eventBus.addHandler(PlaceChangeEvent.class, new PlaceChangeEvent.Handler() {
                    public void onPlaceChange(PlaceChangeEvent event) {
                        Place newPlace = event.getNewPlace();
                        historian.newItem(tokenForPlace(newPlace));
                    }
                });
        final HandlerRegistration historyReg = historian.addValueChangeHandler(new HistoryChangedEvent.Handler(){
            @Override
            public void onHistoryChange(HistoryChangedEvent event) {
                PlaceHistoryHandler.this.handleHistoryToken(event.getPlaceToken());
            }
        });

        return new HandlerRegistration() {
            public void removeHandler() {
                PlaceHistoryHandler.this.placeController = null;
                placeReg.removeHandler();
                historyReg.removeHandler();
            }
        };
    }



    private String tokenForPlace(Place newPlace) {
        if (defaultPlaceProvider.get().equals(newPlace)) {
            return "";
        }

        String token = mapper.getToken(newPlace);
        if (token != null) {
            return token;
        }
        return "";
    }

    private void handleHistoryToken(String token) {

        Place newPlace = null;

        if ("".equals(token)) {
            newPlace = defaultPlaceProvider.get();
        }

        if (newPlace == null) {
            newPlace = mapper.getPlace(token);
        }

        if (newPlace == null) {
            newPlace = defaultPlaceProvider.get();
        }

        placeController.goTo(newPlace);
    }

    public void handleCurrentFragment() {
        handleHistoryToken(historian.getToken());
    }

    /**
     * Created with IntelliJ IDEA.
     * User: sveffa
     * Date: 7/1/12
     * Time: 12:13 PM
     * To change this template use File | Settings | File Templates.
     */
    public static interface Historian {


        /**
         * Gets the current history token.
         *
         * @return the initial token, or the empty string if none is present.
         */
        public String getToken();

        /**
           * Adds a new browser history entry.
           * Calling this method will cause
           * {@link PlaceHistoryHandler.HistoryChangedEvent.Handler#onHistoryChange(PlaceHistoryHandler.HistoryChangedEvent)}
           * to be called as well.
           *
           * @param historyToken the token to associate with the new history item
           */
        public void newItem(String historyToken);


        HandlerRegistration addValueChangeHandler(
                HistoryChangedEvent.Handler handler);

    }

    /**
     * Created by IntelliJ IDEA.
     * User: sveffa
     * Date: 4/4/12
     * Time: 2:07 PM
     * To change this template use File | Settings | File Templates.
     */
    public static class HistoryChangedEvent implements Event<HistoryChangedEvent.Handler> {

        private final String placeToken;

        public HistoryChangedEvent(String placeToken) {
            this.placeToken = placeToken;
        }

        public String getPlaceToken() {
            return placeToken;
        }

        @Override
        public void dispatch(Handler handler) {
            handler.onHistoryChange(this);
        }

        public static interface Handler extends EventHandler {

            public void onHistoryChange(HistoryChangedEvent event);

        }
    }
}
