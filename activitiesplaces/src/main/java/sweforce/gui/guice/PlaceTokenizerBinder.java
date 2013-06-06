package sweforce.gui.guice;

import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PlaceTokenizerUtil;


/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 3/8/13
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceTokenizerBinder {

    private final MapBinder<String, PlaceTokenizer> placeTokenizerMapBinder;

    private PlaceTokenizerBinder(MapBinder<String, PlaceTokenizer> placeTokenizerMapBinder) {
        this.placeTokenizerMapBinder = placeTokenizerMapBinder;
    }

    public static PlaceTokenizerBinder newPlaceTokenizerBinder(Binder binder) {
        final MapBinder<String, PlaceTokenizer> placeTokenizerMapBinder =
                MapBinder.newMapBinder(binder, String.class, PlaceTokenizer.class).permitDuplicates();
        return new PlaceTokenizerBinder(placeTokenizerMapBinder);
    }


    public <P extends Place> PlaceTokenizerBinder bind(Class<P> placeClass) {
        Class<? extends PlaceTokenizer<P>> placeTokenizerClass = PlaceTokenizerUtil.getDeclaredPlaceTokenizerClass(placeClass);
        String prefix = PlaceTokenizerUtil.getPrefixAnnotationValue(placeTokenizerClass);
        placeTokenizerMapBinder.addBinding(prefix).to(placeTokenizerClass);
        return this;
    }
}
