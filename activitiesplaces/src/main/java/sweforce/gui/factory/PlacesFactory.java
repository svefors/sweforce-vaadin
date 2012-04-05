package sweforce.gui.factory;

import sweforce.gui.ap.place.Place;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 1:58 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PlacesFactory {

    public Class<? extends Place> supportedPlaces();
}
