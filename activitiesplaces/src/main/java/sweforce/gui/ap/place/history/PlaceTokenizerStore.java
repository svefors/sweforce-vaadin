package sweforce.gui.ap.place.history;

import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
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

    public static class Builder {

        private Map<String, PlaceTokenizer<? extends Place>> tokenizerMap =
                new HashMap<String, PlaceTokenizer<? extends Place>>();

        public Builder addTokenizer(PlaceTokenizer<?> tokenizer) {
            try {
                Method method = tokenizer.getClass().getMethod("getPlace", String.class);
                if (method.getReturnType().isAnnotationPresent(Prefix.class)) {
                    Prefix prefixAnnotation = method.getReturnType().getAnnotation(Prefix.class);
                    String prefix = prefixAnnotation.value();
                    return addTokenizer(prefix, tokenizer);
                }
                throw new RuntimeException("Returned place class does not have Prefix annotaion");
            } catch (NoSuchMethodException nsme) {
                throw new RuntimeException(nsme);
            }
        }

        public Builder addTokenizer(Class<? extends PlaceTokenizer<? extends Place>> tokenizerClass) {
            try {
                return addTokenizer(tokenizerClass.newInstance());
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        }

//        public Builder addPlace(Class<? extends Place> placeClass, PlaceTokenizer<? extends Place> tokenizer) {
//
//            if (placeClass.isAnnotationPresent(Prefix.class)){
//                Prefix prefix = placeClass.getAnnotation(Prefix.class);
//            }
//
//            return this;
//        }

        public Builder addPlaces(Collection<Class<? extends Place>> clazzes) {
            for (Class<? extends Place> clazz : clazzes) {
                this.addPlace(clazz);
            }
            return this;
        }


        public Builder addPlace(Class<? extends Place> placeClass) {
            Class declaredClasses[] = placeClass.getDeclaredClasses();
            for (Class declaredClass : declaredClasses) {
                if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
                    return addTokenizer(declaredClass);
                }
            }
            throw new IllegalArgumentException("Place does not have tokenizer: " + placeClass.getName());
        }

        public Builder addTokenizer(String prefix, PlaceTokenizer<? extends Place> tokenizer) {
            tokenizerMap.put(prefix, tokenizer);
            return this;
        }

        public PlaceTokenizerStore build() {
            return new MapStore(tokenizerMap);
        }

    }

    public static class MapStore implements PlaceTokenizerStore {
        private final Map<String, PlaceTokenizer<? extends Place>> tokenizerMap;

        public MapStore(Map<String, PlaceTokenizer<? extends Place>> tokenizerMap) {
            this.tokenizerMap = tokenizerMap;
        }

        @Override
        public PlaceTokenizer getTokenizer(String prefix) {
            return tokenizerMap.get(prefix);
        }

//        static MapStore create(Collection<Class<? extends Place>> clazzes) {
//            return new MapStore(createTokenizerMap(clazzes));
//        }
//
//
//        @SuppressWarnings("unchecked")
//        static Map<String, PlaceTokenizer<Place>> createMap(Collection<PlaceTokenizer<Place>> tokenizers) {
//            Map<String, PlaceTokenizer<Place>> tokenizerMap = new HashMap<String, PlaceTokenizer<Place>>();
//            for (PlaceTokenizer<Place> tokenizer : tokenizers) {
//                Class<? extends PlaceTokenizer> clazz = tokenizer.getClass();
//                Method method;
//                try {
//                    method = clazz.getMethod("getPlace", String.class);
//                    if (method != null && Place.class.isAssignableFrom(method.getReturnType()) &&
//                            method.getReturnType().isAnnotationPresent(Prefix.class)) {
//                        Prefix prefix = method.getReturnType().getAnnotation(Prefix.class);
//                        tokenizerMap.put(prefix.value(), tokenizer);
//                    }
//                } catch (NoSuchMethodException nsme) {
//
//                }
//
//            }
//            return tokenizerMap;
//        }
//
//        @SuppressWarnings("unchecked")
//        static Map<String, PlaceTokenizer<? extends Place>> createTokenizerMap(Collection<Class<? extends Place>> clazzes) {
//            Map<String, PlaceTokenizer<? extends Place>> tokenizerMap = new HashMap<String, PlaceTokenizer<? extends Place>>();
//            for (Class<? extends Place> clazz : clazzes) {
//                Prefix prefix = clazz.getAnnotation(Prefix.class);
//                Class declaredClasses[] = clazz.getDeclaredClasses();
//                boolean hasTokenizer = false;
//                for (Class declaredClass : declaredClasses) {
//                    if (PlaceTokenizer.class.isAssignableFrom(declaredClass)) {
//                        try {
//                            PlaceTokenizer<Place> tokenizer = (PlaceTokenizer<Place>) declaredClass.newInstance();
//                            tokenizerMap.put(prefix.value(), tokenizer);
//                            hasTokenizer = true;
//                        } catch (InstantiationException e) {
//                            throw new IllegalStateException(e);
//                        } catch (IllegalAccessException e) {
//                            throw new IllegalStateException(e);
//                        }
//                    }
//                }
//                if (!hasTokenizer)
//                    throw new IllegalArgumentException("Place does not have tokenizer and/or prefix annotaion: " + clazz.getName());
//            }
//            return tokenizerMap;
//        }
    }


}
