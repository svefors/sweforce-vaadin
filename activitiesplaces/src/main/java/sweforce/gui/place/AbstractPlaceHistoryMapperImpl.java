package sweforce.gui.place;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/7/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractPlaceHistoryMapperImpl implements PlaceHistoryMapper {

    public final char separator;

    protected AbstractPlaceHistoryMapperImpl(char separator) {
        this.separator = separator;
    }

    protected AbstractPlaceHistoryMapperImpl() {
        this(':');
    }

    public Place getPlace(String token) {
        if (token == null)
            return null;
        int colonAt = token.indexOf(separator);
        String initial;
        String rest;
        if (colonAt >= 0) {
            initial = token.substring(0, colonAt);
            rest = token.substring(colonAt + 1);
        } else {
            initial = "";
            rest = token;
        }
        PlaceTokenizer<?> tokenizer = getPlaceTokenizer(initial);
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

    protected abstract PlaceTokenizer getPlaceTokenizer(String prefix);

    @SuppressWarnings("unchecked")
    protected abstract PrefixAndToken getPrefixAndToken(Place newPlace);

    /**
     * Return value for
     * {@link PlaceHistoryMapperImpl#getPrefixAndToken(sweforce.gui.place.Place)}.
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
}
