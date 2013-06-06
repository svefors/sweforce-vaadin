package sweforce.gui.place;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/8/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceHistoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlaceHistoryMapper.class).to(PlaceHistoryMapperImpl.class);
        bind(PlaceHistoryHandler.class);
    }


}
