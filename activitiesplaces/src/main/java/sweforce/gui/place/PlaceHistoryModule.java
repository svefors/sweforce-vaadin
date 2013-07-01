package sweforce.gui.place;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryModule extends AbstractModule {

    private MapBinder<String, PlaceTokenizer<? extends Place>> stringPlaceTokenizerMapBinder;

    private final Class<?extends Place> placeClasses[];

    public PlaceHistoryModule(Class<? extends Place>... placeClasses) {
        this.placeClasses = placeClasses;
    }

    @Override
    protected void configure() {
        stringPlaceTokenizerMapBinder = MapBinder.newMapBinder(binder(),
                        new TypeLiteral<String>() {
                        },
                        new TypeLiteral<PlaceTokenizer<? extends Place>>() {
                        }
                );
        bind(PlaceHistoryMapper.class).to(PlaceHistoryMapperImpl.class);
        bind(PlaceHistoryHandler.class);
        for (Class<?extends Place> placeClass : placeClasses){
            bindPlace(placeClass);
        }
    }

    protected final void bindPlace(Class<? extends Place> placeClass){
        PlaceTokenizerUtil.bind(placeClass, stringPlaceTokenizerMapBinder);
    }






}
