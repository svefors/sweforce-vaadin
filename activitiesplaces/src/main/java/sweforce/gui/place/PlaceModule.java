package sweforce.gui.place;

import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.*;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/13
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceModule extends BinderModule {
    @Override
    protected void declare() {
        bind(PlaceHistoryMapper.class).to(PlaceHistoryMapperWithRegistry.class);
        bind(PlaceHistoryHandler.class).toConstructor();
        autobind(PrefixPlaceTokenizerRegistry.Impl.class);
    }

}
