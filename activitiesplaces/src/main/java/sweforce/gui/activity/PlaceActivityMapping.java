package sweforce.gui.activity;

import sweforce.gui.place.Place;

import javax.inject.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 6/30/13
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PlaceActivityMapping<A extends Activity> {

    public Provider<A> getProvider(Place place);


}
