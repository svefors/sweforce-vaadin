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
package sweforce.gui.ap.place.mapper;

import sweforce.gui.ap.place.Place;

/**
 *
 *
 * (Renamed from GWT PlaceHistoryMapper)
 */
public interface PlaceFragmentMapper {

    /**
     * Returns the {@link sweforce.gui.ap.place.Place} associated with the given token.
     *
     * @param token a String token
     * @return a {@link sweforce.gui.ap.place.Place} instance
     */
    Place getPlace(String token);

    /**
     * Returns the String token associated with the given {@link Place}.
     *
     * @param place a {@link Place} instance
     * @return a String token
     */
    String getToken(Place place);


}
