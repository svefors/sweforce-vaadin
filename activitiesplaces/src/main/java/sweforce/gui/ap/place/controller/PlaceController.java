package sweforce.gui.ap.place.controller;

import sweforce.gui.ap.place.Place;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/5/12
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PlaceController {

    public Place getWhere();

    public void goTo(final Place newPlace);
}
