package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.PlaceHistoryHandler;
import sweforce.gui.place.PlaceHistoryMapper;
import sweforce.gui.place.PlacesWithPrefixPlaceHistoryMapperImpl;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:01 AM
* To change this template use File | Settings | File Templates.
*/
public class PlaceHistoryModule extends BinderModule {
    @Override
    protected void declare() {
//            require(PlaceHistoryHandler.Historian.class);
        bind(PlaceHistoryMapper.class).to(PlacesWithPrefixPlaceHistoryMapperImpl.class);
        bind(PlaceHistoryHandler.class);
    }
}
