package sweforce.gui.ap.place.controller;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlaceController.class).to(DefaultPlaceController.class);
    }
}
