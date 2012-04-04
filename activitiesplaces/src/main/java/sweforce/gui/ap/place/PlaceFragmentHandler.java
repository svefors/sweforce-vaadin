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
package sweforce.gui.ap.place;

import com.vaadin.ui.UriFragmentUtility;
import sweforce.gui.ap.place.mapper.PlaceFragmentMapper;
import sweforce.gui.ap.web.BrowserWindow;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.HandlerRegistration;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 3/30/12
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceFragmentHandler implements UriFragmentUtility.FragmentChangedListener {

    private PlaceController placeController;

    private Place defaultPlace = Place.NOWHERE;


    private final PlaceFragmentMapper mapper;

    private final BrowserWindow browserWindow;

    public PlaceFragmentHandler(PlaceFragmentMapper mapper, BrowserWindow browserWindow) {
        this.mapper = mapper;
        this.browserWindow = browserWindow;
    }

    @Override
    public void fragmentChanged(UriFragmentUtility.FragmentChangedEvent fragmentChangedEvent) {
        String fragment = fragmentChangedEvent.getUriFragmentUtility().getFragment();
        handleHistoryToken(fragment);
    }


    public HandlerRegistration register(PlaceController placeController, EventBus eventBus,
                                        Place defaultPlace) {
        this.placeController = placeController;
        this.defaultPlace = defaultPlace;
        final HandlerRegistration placeReg =
                eventBus.addHandler(PlaceChangeEvent.class, new PlaceChangeEvent.Handler() {
                    public void onPlaceChange(PlaceChangeEvent event) {
                        Place newPlace = event.getNewPlace();
                        browserWindow.getUriFragmentUtility().setFragment(tokenForPlace(newPlace));
                    }
                });

        return new HandlerRegistration() {
            public void removeHandler() {
                PlaceFragmentHandler.this.defaultPlace = Place.NOWHERE;
                PlaceFragmentHandler.this.placeController = null;
                placeReg.removeHandler();

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

    public void handleCurrentHistory() {
        handleHistoryToken(browserWindow.getUriFragmentUtility().getFragment());
    }
}
