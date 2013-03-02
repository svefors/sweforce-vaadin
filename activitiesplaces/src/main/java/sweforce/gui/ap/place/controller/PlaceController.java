package sweforce.gui.ap.place.controller;

import sweforce.gui.ap.place.Place;

/**

 */
public interface PlaceController {

    public Place getWhere();

    public void goTo(final Place newPlace);

    public void setDefaultPlace(Place defaultPlace);

}
