package sweforce.gui.place;

import se.jbee.inject.bind.Binder;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.*;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/13
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceSilkModule extends BinderModule {
    @Override
    protected void declare() {
        //mapper
        bind(PlaceHistoryMapper.class).to(PlaceHistoryMapperImpl.class);
        bind(PlaceHistoryMapperImpl.class).toConstructor();
//        //registry
//        autobind(PrefixPlaceTokenizerRegistry.Impl.class);



        //handler
        bind(PlaceHistoryHandler.class).toConstructor();

    }

    public static void bindPrefixMapping(BinderModule binderModule, PrefixPlaceTokenizerMapping mapping){
        binderModule.injectingInto(PlaceHistoryMapperImpl.class).multibind(PrefixPlaceTokenizerMapping.class).to(mapping);
    }

}
