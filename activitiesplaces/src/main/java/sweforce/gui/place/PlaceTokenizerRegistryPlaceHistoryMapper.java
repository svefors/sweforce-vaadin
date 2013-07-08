package sweforce.gui.place;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/7/13
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceTokenizerRegistryPlaceHistoryMapper extends AbstractPlaceHistoryMapperImpl {


    /**
     * prefix -> placeTokenizer mapping
     */
    private Map<String, PlaceTokenizer<? extends Place>> tokenizerMap =
            new HashMap<String, PlaceTokenizer<? extends Place>>();

    /**
     * placeClass -> prefix mapping
     */
    private Map<Class<? extends Place>, String> placeClassPrefixMap = new HashMap<Class<? extends Place>, String>();

    public PlaceTokenizerRegistryPlaceHistoryMapper(PrefixPlaceTokenizerMapping[] prefixPlaceTokenizerMappings) {
        for (PrefixPlaceTokenizerMapping<?> mapping : prefixPlaceTokenizerMappings) {
            tokenizerMap.put(mapping.prefix(), mapping.placeTokenizer());
            try {
                Class<? extends Place> placeClass = (Class<? extends Place>) mapping.placeTokenizer()
                        .getClass().getMethod("getPlace", String.class).getReturnType();
                placeClassPrefixMap.put(placeClass, mapping.prefix());
            } catch (NoSuchMethodException nsme) {
                throw new IllegalStateException("Placetokenizer getPlace(String) does not have return type Place");
            }
        }
    }

    @Override
    protected PlaceTokenizer getPlaceTokenizer(String prefix) {
        return tokenizerMap.get(prefix);
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
            throw new IllegalStateException("can't find prefix for place: " + newPlace.getClass());
        PlaceTokenizer placeTokenizer = getPlaceTokenizer(prefix);
        return new PrefixAndToken(prefix, placeTokenizer.getToken(newPlace));
    }

    public static interface PrefixPlaceTokenizerMapping<P extends Place> {
        public String prefix();

        public PlaceTokenizer<P> placeTokenizer();

    }
}
