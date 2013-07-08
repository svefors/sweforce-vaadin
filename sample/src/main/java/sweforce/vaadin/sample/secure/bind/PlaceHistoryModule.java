package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.*;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/13
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryModule extends BinderModule {
    @Override
    protected void declare() {
//        bind(PlaceTokenizerRegistryPlaceHistoryMapper.class).toConstructor();
        bind(PlaceHistoryMapper.class).to(PlaceTokenizerRegistryPlaceHistoryMapper.class);
        bind(PlaceHistoryHandler.class).toConstructor();
    }


    public static PlacePrefixMatcher placePrefixMatcher(String prefix, PlaceTokenizer placeTokenizer){
        return new PlacePrefixMatcher(prefix, placeTokenizer);
    }

    public static class PlacePrefixMatcher extends BinderModule {
        private final String prefix;
        private final PlaceTokenizer placeTokenizer;

        public PlacePrefixMatcher(String prefix, PlaceTokenizer placeTokenizer) {
            this.prefix = prefix;
            this.placeTokenizer = placeTokenizer;
        }

        @Override
        protected void declare() {
            require(PlaceTokenizerRegistryPlaceHistoryMapper.class);
            injectingInto(PlaceTokenizerRegistryPlaceHistoryMapper.class).multibind(
                    PlaceTokenizerRegistryPlaceHistoryMapper.PrefixPlaceTokenizerMapping.class).to(
                    new PlaceTokenizerRegistryPlaceHistoryMapper.PrefixPlaceTokenizerMapping() {
                        @Override
                        public String prefix() {
                            return prefix;
                        }

                        @Override
                        public PlaceTokenizer placeTokenizer() {
                            return placeTokenizer;
                        }
                    });
        }
    }
}
