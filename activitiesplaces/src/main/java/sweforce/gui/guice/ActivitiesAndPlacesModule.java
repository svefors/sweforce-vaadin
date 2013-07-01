package sweforce.gui.guice;

import com.google.inject.AbstractModule;
import sweforce.gui.place.PlaceControllerModule;
import sweforce.gui.place.PlaceHistoryModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 6/30/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivitiesAndPlacesModule extends AbstractModule {


    @Override
    protected void configure() {
        install(new PlaceControllerModule());
        install(new PlaceHistoryModule());
    }


}
