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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An implementation of {@link PlaceHistoryMapper} that uses the place tokenizers to map between fragment and place.
 */
public class PlaceHistoryMapperImpl extends AbstractPlaceHistoryMapperImpl implements PrefixPlaceTokenizerMapping.PrefixPlaceTokenizerMapper {

    /**
     * prefix -> placeTokenizer mapping
     */
    private final Map<String, PlaceTokenizer<Place>> tokenizerMap = new LinkedHashMap<String, PlaceTokenizer<Place>>();

    /**
     * placeClass -> prefix mapping
     */
    private final Map<Class<? extends Place>, String> placeClassPrefixMap = new LinkedHashMap<Class<? extends Place>, String>();

    public PlaceHistoryMapperImpl(PrefixPlaceTokenizerMapping... prefixPlaceTokenizerMappings) {
//        this();
        for (PrefixPlaceTokenizerMapping prefixPlaceTokenizerMapping : prefixPlaceTokenizerMappings) {
            addMapping(prefixPlaceTokenizerMapping);
        }
    }

//    public PlaceHistoryMapperImpl() {
//        this(new LinkedHashMap<String, PlaceTokenizer<Place>>(), new LinkedHashMap<Class<? extends Place>, String>());
//    }
//
//    private PlaceHistoryMapperImpl(Map<String, PlaceTokenizer<Place>> tokenizerMap
//            , Map<Class<? extends Place>, String> placeClassPrefixMap) {
//        this.tokenizerMap = tokenizerMap;
//        this.placeClassPrefixMap = placeClassPrefixMap;
//    }

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


    public void addMapping(PrefixPlaceTokenizerMapping prefixPlaceTokenizerMapping) {
        tokenizerMap.put(prefixPlaceTokenizerMapping.prefix, prefixPlaceTokenizerMapping.placeTokenizer);
        //TODO move to static util method.
        try {
            Class<? extends Place> placeClass = (Class<? extends Place>) prefixPlaceTokenizerMapping.getPlaceTokenizer()
                    .getClass().getMethod("getPlace", String.class).getReturnType();
            placeClassPrefixMap.put(placeClass, prefixPlaceTokenizerMapping.prefix);
            tokenizerMap.put(prefixPlaceTokenizerMapping.prefix, prefixPlaceTokenizerMapping.getPlaceTokenizer());
        } catch (NoSuchMethodException nsme) {
            throw new IllegalStateException("Placetokenizer getPlace(String) does not have return type Place");
        }
    }


    /**
     * @param newPlace what needs tokenizing
     * @return the token, or null
     */
    @Override
    @SuppressWarnings("unchecked")
    protected PrefixAndToken getPrefixAndToken(Place newPlace) {
        String prefix = placeClassPrefixMap.get(newPlace.getClass());
        if (prefix == null)
            return null;
        return new PrefixAndToken(prefix, tokenizerMap.get(prefix).getToken(newPlace));
//
//        Prefix prefix = newPlace.getClass().getAnnotation(Prefix.class);
//        if (prefix != null) {
//            return new PrefixAndToken(prefix.value(), tokenizerMap.get(prefix.value()).getToken(newPlace));
//        }
//        return null;
    }


}