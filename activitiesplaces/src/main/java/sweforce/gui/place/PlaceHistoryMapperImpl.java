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

import javax.inject.Inject;
import java.util.Map;

/**
 * An implementation of {@link PlaceHistoryMapper} that uses the place tokenizers to map between fragment and place.
 */
class PlaceHistoryMapperImpl implements PlaceHistoryMapper {


    private Map<String, PlaceTokenizer<Place>> tokenizerMap;

    @Inject
    public PlaceHistoryMapperImpl(Map<String, PlaceTokenizer<Place>> tokenizerMap) {
        this.tokenizerMap = tokenizerMap;
    }

    /**
     * Return value for
     * {@link PlaceHistoryMapperImpl#getPrefixAndToken(Place)}.
     */
    public static class PrefixAndToken {
        public final String prefix;
        public final String token;

        public PrefixAndToken(String prefix, String token) {
            assert prefix != null && !prefix.contains(":");
            this.prefix = prefix;
            this.token = token;
        }

        @Override
        public String toString() {
            return (prefix.length() == 0) ? token : prefix + ":" + token;
        }
    }

//    protected F factory;

    public Place getPlace(String token) {
        if(token == null)
            return null;
        int colonAt = token.indexOf(':');
        String initial;
        String rest;
        if (colonAt >= 0) {
            initial = token.substring(0, colonAt);
            rest = token.substring(colonAt + 1);
        } else {
            initial = "";
            rest = token;
        }
        PlaceTokenizer<?> tokenizer = tokenizerMap.get(initial);
        if (tokenizer != null) {
            return tokenizer.getPlace(rest);
        }
        return null;
    }

    public String getToken(Place place) {
        PrefixAndToken token = getPrefixAndToken(place);
        if (token != null) {
            return token.toString();
        }
        return null;
    }


    /**
     * @param newPlace what needs tokenizing
     * @return the token, or null
     */
    @SuppressWarnings("unchecked")
    protected PrefixAndToken getPrefixAndToken(Place newPlace) {
        Prefix prefix = newPlace.getClass().getAnnotation(Prefix.class);
        if (prefix != null) {
            return new PrefixAndToken(prefix.value(), tokenizerMap.get(prefix.value()).getToken(newPlace));
        }
        return null;
    }


}