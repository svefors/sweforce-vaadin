package sweforce.gui.activity.registry;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.ActivityMapperWithActivityMappings;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityMapperSilkModule extends BinderModule{

    private final Name name;

    public ActivityMapperSilkModule(Name name) {
        this.name = name;
    }

    @Override
    protected void declare() {
        bind(name, ActivityMapper.class)
                        .to(name, ActivityMapperWithActivityMappings.class);
//        injectingInto(name, ActivityManager.class)
//                .bindPrefixMapping(name, ActivityMapper.class)
//                .to(name, ActivityMapperWithRegistry.class);

    }

//    public static class ActivityMappingSilkModule extends BinderModule{
//        private final Name name;
//
//        public ActivityMappingSilkModule(Name name) {
//            this.name = name;
//        }
//
//        @Override
//        protected void declare() {
//            injectingInto(name, ActivityMapperWithActivityMappings.class))
//            bindPrefixMapping(name, ActivityMapperWithActivityMappings.class).
//        }
//    }
}
