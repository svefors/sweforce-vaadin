package sweforce.gui.activity;

import se.jbee.inject.Name;
import se.jbee.inject.Parameter;
import se.jbee.inject.bind.BinderModule;

import static se.jbee.inject.Type.raw;
import static se.jbee.inject.bootstrap.Parameterize.asType;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/13/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class NamedActivitiesSectionSilkModule extends BinderModule {

    private final Name name;

    public NamedActivitiesSectionSilkModule(Name name) {
        this.name = name;
    }

    public NamedActivitiesSectionSilkModule(String name) {
        this(Name.named(name));
    }

    @Override
    protected final void declare() {

        bind(name, SingleThreadedActivityManager.class).toConstructor();

        bind(name, ActivityMapperWithActivityMappings.class).toConstructor();

        bind(name, ActivityMapper.class).to(name, ActivityMapperWithActivityMappings.class);

        injectingInto(name, ActivityManager.class)
                .bind(name, ActivityMapper.class).to(ActivityMapperWithActivityMappings.class);

//        injectingInto(name, ActivityMapperWithActivityMappings.class)
//                          .bindPrefixMapping(ActivityMapping[].class).to(name, ActivityMapping[].class);

//        arraybind(ActivityMapping[].class).to(new ActivityMapping[0]);


        bind(name, ActivityManager.class).to(name, SingleThreadedActivityManager.class);

        for (ActivityMapping activityMapping : getActivityMappings()) {
            declareActivityMapping(activityMapping);
        }
    }

    /**
     * empty method. override to add you own activity mappings
     */
    protected ActivityMapping[] getActivityMappings() {
        return new ActivityMapping[0];
    }

    /**
     * helper method, is called from declare
     *
     * @param activityMapping
     */
    private final void declareActivityMapping(ActivityMapping activityMapping) {
        bindActivityMapping(this, this.name, activityMapping);
    }

    public static void bindActivityMapping(BinderModule binderModule, Name name, ActivityMapping activityMapping) {
        binderModule.injectingInto(name, ActivityMapperWithActivityMappings.class).multibind(name, ActivityMapping.class).to(activityMapping);
    }
}
