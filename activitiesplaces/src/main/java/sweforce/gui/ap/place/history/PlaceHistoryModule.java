package sweforce.gui.ap.place.history;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import sweforce.gui.ap.place.Place;

import javax.inject.Named;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryModule extends AbstractModule {

    public static final String NAMED_PLACE_CLASSES = "PlaceHistoryModule.PLACE_CLASSES";

    @Override
    protected void configure() {
//        bind(PlaceTokenizerStore.class).toProvider(PlaceTokenizerGetterProvider.class);
        bind(PlaceHistoryMapper.class).to(PlaceHistoryMapperImpl.class);
        bind(PlaceHistoryHandler.class);
    }



//    @Provides
//    PlaceTokenizerStore providePlaceTokenizerStore(@Named(NAMED_PLACE_CLASSES) Collection<Class<? extends Place>> clazzes){
//        return new PlaceTokenizerStore.Builder().addPlaces(clazzes).build();
//    }

}
