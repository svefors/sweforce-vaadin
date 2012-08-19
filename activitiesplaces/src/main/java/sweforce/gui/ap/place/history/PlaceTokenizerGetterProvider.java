package sweforce.gui.ap.place.history;

import com.google.inject.ProvisionException;
import sweforce.gui.ap.place.Place;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/6/12
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceTokenizerGetterProvider implements Provider<PlaceTokenizerStore> {

    private Collection<Class<? extends Place>> clazzes;

    @Inject
    public PlaceTokenizerGetterProvider(@Named("Place Classes") Collection<Class<? extends Place>> clazzes) {
        this.clazzes = clazzes;
    }

    @Override
    public PlaceTokenizerStore get() {
        try {
            return new PlaceTokenizerStore.Builder().addPlaces(this.clazzes).build();
        } catch (RuntimeException re) {
            throw new ProvisionException("Could not create PlaceTokenizerGetter", re);
        }
    }


}
