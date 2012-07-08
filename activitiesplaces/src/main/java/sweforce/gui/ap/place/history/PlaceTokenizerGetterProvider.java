package sweforce.gui.ap.place.history;

import com.google.inject.ProvisionException;
import sweforce.gui.ap.place.Place;
import sweforce.gui.ap.place.token.PlaceTokenizer;
import sweforce.gui.ap.place.token.Prefix;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/6/12
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceTokenizerGetterProvider implements Provider<PlaceTokenizerStore> {

    Collection<Class<? extends Place>> clazzes;

    @Inject
    public PlaceTokenizerGetterProvider(@Named("Place Classes") Collection<Class<? extends Place>> clazzes) {
        this.clazzes = clazzes;
    }

    @Override
    public PlaceTokenizerStore get() {
        try {
            return PlaceTokenizerStore.MapStore.create(this.clazzes);
        } catch (RuntimeException re) {
            throw new ProvisionException("Could not create PlaceTokenizerGetter", re);
        }
    }


}
