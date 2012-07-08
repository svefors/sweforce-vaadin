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
package sweforce.gui.ap.place.history;

import sweforce.gui.ap.history.Historian;
import sweforce.gui.ap.history.HistoryChangedEvent;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.PlaceChangeEvent;
import sweforce.gui.ap.place.controller.PlaceController;
import sweforce.gui.ap.place.history.PlaceHistoryMapper;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.HandlerRegistration;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/30/12
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryHandler {

    private PlaceController placeController;

    private Place defaultPlace = Place.NOWHERE;

    private final PlaceHistoryMapper mapper;

    private final Historian historian;

    @Inject
    public PlaceHistoryHandler(PlaceHistoryMapper mapper, Historian historian) {
        this.mapper = mapper;
        this.historian = historian;
    }




    public HandlerRegistration register(PlaceController placeController, EventBus eventBus,
                                        Place defaultPlace) {
        this.placeController = placeController;
        this.defaultPlace = defaultPlace;
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
                PlaceHistoryHandler.this.defaultPlace = Place.NOWHERE;
                PlaceHistoryHandler.this.placeController = null;
                placeReg.removeHandler();
                historyReg.removeHandler();
            }
        };
    }



    private String tokenForPlace(Place newPlace) {
        if (defaultPlace.equals(newPlace)) {
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
            newPlace = defaultPlace;
        }

        if (newPlace == null) {
            newPlace = mapper.getPlace(token);
        }

        if (newPlace == null) {
            newPlace = defaultPlace;
        }

        placeController.goTo(newPlace);
    }

    public void handleCurrentFragment() {
        handleHistoryToken(historian.getToken());
    }
}
