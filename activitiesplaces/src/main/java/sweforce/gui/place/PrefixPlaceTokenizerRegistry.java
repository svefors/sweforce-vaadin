package sweforce.gui.place;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/10/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PrefixPlaceTokenizerRegistry {

    public String findPrefix(Class<? extends Place> placeClass);

    public PlaceTokenizer<? extends Place> findTokenizer(String prefix);


    public class Impl implements PrefixPlaceTokenizerRegistry, PrefixPlaceTokenizerConfiguration<PrefixPlaceTokenizerConfiguration> {
        /**
         * prefix -> placeTokenizer mapping
         */
        private Map<String, PlaceTokenizer<? extends Place>> tokenizerMap =
                new HashMap<String, PlaceTokenizer<? extends Place>>();

        /**
         * placeClass -> prefix mapping
         */
        private Map<Class<? extends Place>, String> placeClassPrefixMap = new HashMap<Class<? extends Place>, String>();

        public PlaceTokenizer<? extends Place> findTokenizer(String prefix) {
            return tokenizerMap.get(prefix);
        }

        public String findPrefix(Class<? extends Place> placeClass) {
            return placeClassPrefixMap.get(placeClass);
        }


        private void add(String prefix, PlaceTokenizer placeTokenizer) {
            tokenizerMap.put(prefix, placeTokenizer);
            try {
                Class<? extends Place> placeClass = (Class<? extends Place>) placeTokenizer
                        .getClass().getMethod("getPlace", String.class).getReturnType();
                placeClassPrefixMap.put(placeClass, prefix);
            } catch (NoSuchMethodException nsme) {
                throw new IllegalStateException("Placetokenizer getPlace(String) does not have return type Place");
            }
        }


        public UseTokenizer<PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration> prefix(final String prefix) {
            return new UseTokenizer<PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration>() {
                @Override
                public PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration useTokenizer(PlaceTokenizer placeTokenizer) {
                    add(prefix, placeTokenizer);
                    return PrefixPlaceTokenizerRegistry.Impl.this;
                }
            };
        }
    }

    public static interface PrefixPlaceTokenizerConfiguration<T> {
        public UseTokenizer<T> prefix(String prefix);
    }

    public static interface UseTokenizer<T> {
        public T useTokenizer(PlaceTokenizer placeTokenizer);
    }

}
