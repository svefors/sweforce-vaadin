package sweforce.gui.ap.place;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlacesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlacesRunner.class);
    }

}
