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

import java.util.Map;

/**
 * An implementation of {@link PlaceHistoryMapper} that uses the place tokenizers to map between fragment and place.
 */
public class PlacesWithPrefixPlaceHistoryMapperImpl extends AbstractPlaceHistoryMapperImpl {

    /**
     * prefix -> placeTokenizer mapping
     */
    private Map<String, PlaceTokenizer<Place>> tokenizerMap;

    /**
     * placeClass -> prefix mapping
     */
    private Map<Class<? extends Place>, String> placeClassPrefixMap;


    public PlacesWithPrefixPlaceHistoryMapperImpl(Map<String, PlaceTokenizer<Place>> tokenizerMap) {
        this.tokenizerMap = tokenizerMap;
    }


    @Override
    protected PlaceTokenizer getPlaceTokenizer(String prefix) {
        return tokenizerMap.get(prefix);
    }

//    @SuppressWarnings("unchecked")
//    public PlacesWithPrefixPlaceHistoryMapperImpl(Class<? extends Place>... placeClasses) {
//        for (Class<? extends Place> placeClass : placeClasses) {
//            Class<? extends PlaceTokenizer> placeTokenizerClass =
//                    PlaceTokenizerUtil.getDeclaredPlaceTokenizerClass(placeClass);
//            String prefix = PlaceTokenizerUtil.getPrefixAnnotationValue(placeTokenizerClass);
//            try {
//                tokenizerMap.put(prefix, placeTokenizerClass.newInstance());
//            } catch (InstantiationException e) {
//                throw new IllegalStateException(e);
//            } catch (IllegalAccessException e) {
//                throw new IllegalStateException(e);
//            }
//        }
//
//    }

    public PlacesWithPrefixPlaceHistoryMapperImpl(PrefixPlaceTokenizerMapping... prefixPlaceTokenizerMappings) {
        for (PrefixPlaceTokenizerMapping prefixPlaceTokenizerMapping : prefixPlaceTokenizerMappings) {
            tokenizerMap.put(prefixPlaceTokenizerMapping.prefix, prefixPlaceTokenizerMapping.placeTokenizer);
        }
    }

    public static class PrefixPlaceTokenizerMapping {
        public final String prefix;
        public final PlaceTokenizer placeTokenizer;

        public PrefixPlaceTokenizerMapping(String prefix, PlaceTokenizer placeTokenizer) {
            this.placeTokenizer = placeTokenizer;
            this.prefix = prefix;
        }
    }


    /**
     * @param newPlace what needs tokenizing
     * @return the token, or null
     */
    @Override
    @SuppressWarnings("unchecked")
    protected PrefixAndToken getPrefixAndToken(Place newPlace) {
        Prefix prefix = newPlace.getClass().getAnnotation(Prefix.class);
        if (prefix != null) {
            return new PrefixAndToken(prefix.value(), tokenizerMap.get(prefix.value()).getToken(newPlace));
        }
        return null;
    }


}