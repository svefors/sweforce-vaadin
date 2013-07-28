package sweforce.gui.place;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/25/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryMapperBuilder {

    private PlaceHistoryMapperImpl placeHistoryMapper = new PlaceHistoryMapperImpl();

    public <P extends Place> PlaceHistoryMapperBuilder add(String prefix, Class<P> placeClass) {
        try {
            PrefixPlaceTokenizerMapping prefixPlaceTokenizerMapping = new PrefixPlaceTokenizerMapping(prefix,
                    PlaceTokenizerUtil.getDeclaredPlaceTokenizerClass(placeClass).newInstance());
            placeHistoryMapper.addMapping(prefixPlaceTokenizerMapping);
        } catch (InstantiationException ie) {
            throw new IllegalArgumentException("Could not instantiate tokenizer!", ie);
        } catch (IllegalAccessException iae){
            throw new IllegalArgumentException("Could not instantiate tokenizer!", iae);
        }
        return this;
    }

    /**
     *
     * @param placeClass must have prefix annotation
     * @param <P>
     * @return
     */
    public <P extends Place> PlaceHistoryMapperBuilder add(Class<P> placeClass) {
            try {
                PrefixPlaceTokenizerMapping prefixPlaceTokenizerMapping = new PrefixPlaceTokenizerMapping(
                        PlaceTokenizerUtil.getPrefixAnnotationValueFromPlaceClass(placeClass),
                        PlaceTokenizerUtil.getDeclaredPlaceTokenizerClass(placeClass).newInstance());
                placeHistoryMapper.addMapping(prefixPlaceTokenizerMapping);
            } catch (InstantiationException ie) {
                throw new IllegalArgumentException("Could not instantiate tokenizer!", ie);
            } catch (IllegalAccessException iae){
                throw new IllegalArgumentException("Could not instantiate tokenizer!", iae);
            }
            return this;
        }

    /**
     * returns a newly constructed place history mapper.
     *
     * @return
     */
    public PlaceHistoryMapper build() {
        PlaceHistoryMapper tmp = placeHistoryMapper;
        placeHistoryMapper = new PlaceHistoryMapperImpl();
        return tmp;
    }
}
