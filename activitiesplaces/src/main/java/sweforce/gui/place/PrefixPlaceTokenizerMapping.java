package sweforce.gui.place;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/21/13
* Time: 12:08 PM
* To change this template use File | Settings | File Templates.
*/
public class PrefixPlaceTokenizerMapping {
    public final String prefix;
    public final PlaceTokenizer placeTokenizer;

    public PrefixPlaceTokenizerMapping(String prefix, PlaceTokenizer placeTokenizer) {
        this.placeTokenizer = placeTokenizer;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public PlaceTokenizer getPlaceTokenizer() {
        return placeTokenizer;
    }

    public interface PrefixPlaceTokenizerMapper {
        public void addMapping(PrefixPlaceTokenizerMapping prefixPlaceTokenizerMapping);

//        /**
//         * Assumes that the
//         * @param prefix
//         * @param place
//         * @param <P>
//         */
//        public <P extends Place> void add(String prefix, Class<P> place);
    }
}
