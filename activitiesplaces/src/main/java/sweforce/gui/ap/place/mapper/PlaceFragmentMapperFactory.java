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
package sweforce.gui.ap.place.mapper;


import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory to create an PlaceFragmentMapper
 */
public class PlaceFragmentMapperFactory {

    public PlaceFragmentMapper create(Class<? extends Place>... placeClasses) {
        Map<String, PlaceTokenizer<Place>> tokenizerMap = new HashMap<String, PlaceTokenizer<Place>>();
        for (Class<? extends Place> placeClass : placeClasses) {

            Prefix prefix = placeClass.getAnnotation(Prefix.class);

            Class declaredClasses[] = placeClass.getDeclaredClasses();
            for (Class declaredClass : declaredClasses) {
                if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                    try {
                        PlaceTokenizer pt = (PlaceTokenizer) declaredClass.newInstance();
                        tokenizerMap.put(prefix.value(), pt);
                    } catch (InstantiationException e) {
                        throw new IllegalStateException(e);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
        return new PlaceTokenizerPlaceFragmentMapper(tokenizerMap);
    }
}
