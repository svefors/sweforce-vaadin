package sweforce.gui.ap.place.history;

import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PlaceTokenizerStore {
    public PlaceTokenizer getTokenizer(String prefix);

    public static class MapStore implements PlaceTokenizerStore {
        private final Map<String, PlaceTokenizer<Place>> tokenizerMap;

        public MapStore(Map<String, PlaceTokenizer<Place>> tokenizerMap) {
            this.tokenizerMap = tokenizerMap;
        }

        @Override
        public PlaceTokenizer getTokenizer(String prefix) {
            return tokenizerMap.get(prefix);
        }

        static MapStore create(Collection<Class<? extends Place>> clazzes) {
            return new MapStore(createTokenizerMap(clazzes));
        }


        static Map<String, PlaceTokenizer<Place>> createTokenizerMap(Collection<Class<? extends Place>> clazzes) {
            Map<String, PlaceTokenizer<Place>> tokenizerMap = new HashMap<String, PlaceTokenizer<Place>>();
            for (Class<? extends Place> clazz : clazzes) {
                Prefix prefix = clazz.getAnnotation(Prefix.class);
                Class declaredClasses[] = clazz.getDeclaredClasses();
                boolean hasTokenizer = false;
                for (Class declaredClass : declaredClasses) {
                    if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                        try {
                            PlaceTokenizer<Place> tokenizer = (PlaceTokenizer<Place>) declaredClass.newInstance();
                            tokenizerMap.put(prefix.value(), tokenizer);
                            hasTokenizer = true;
                        } catch (InstantiationException e) {
                            throw new IllegalStateException(e);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                }
                if (!hasTokenizer)
                    throw new IllegalArgumentException("Place does not have tokenizer and/or prefix annotaion: " + clazz.getName());
            }
            return tokenizerMap;
        }
    }


}
