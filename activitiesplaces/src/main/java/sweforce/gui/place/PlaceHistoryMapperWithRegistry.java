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
public class PlaceHistoryMapperWithRegistry extends AbstractPlaceHistoryMapperImpl {



    private final PrefixPlaceTokenizerRegistry registry;

    public PlaceHistoryMapperWithRegistry(PrefixPlaceTokenizerRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected PlaceTokenizer getPlaceTokenizer(String prefix) {
        return registry.findTokenizer(prefix);
    }

    /**
     * @param newPlace what needs tokenizing
     * @return the token, or null
     */
    @Override
    @SuppressWarnings("unchecked")
    protected PrefixAndToken getPrefixAndToken(Place newPlace) {
        String prefix = registry.findPrefix(newPlace.getClass());
        if (prefix == null)
            throw new IllegalStateException("can't find prefix for place: " + newPlace.getClass());
        PlaceTokenizer placeTokenizer = getPlaceTokenizer(prefix);
        return new PrefixAndToken(prefix, placeTokenizer.getToken(newPlace));
    }

}
