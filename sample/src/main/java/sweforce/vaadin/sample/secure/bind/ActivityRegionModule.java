package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.CompositeActivityMapper;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PlaceTokenizerRegistryPlaceHistoryMapper;
import sweforce.gui.region.Region;
import sweforce.vaadin.sample.secure.role1.Role1Place;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/8/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityRegionModule extends BinderModule {

//    private final Region region;

    public final String name;

    public ActivityRegionModule(String name) {
        this.name = name;
    }

    public ActivityRegionModule(Region region) {
        this.name = region.toString();
    }

    @Override
    protected void declare() {
        bind(Name.named(name), ActivityMapper.class).to(CompositeActivityMapper.class);
    }

    /**
     * you will need to declare your toConstructor call somewhere.
     * @param activityMapperClass
     * @return
     */
    public BinderModule newActivityMapping(final Class<? extends ActivityMapper> activityMapperClass){
        return newActivityMapping(name, activityMapperClass);
    }

    /**
     * will bind the activity mapper instance to the thing
     * @param activityMapper
     * @return
     */
    public BinderModule newActivityMapping(final ActivityMapper activityMapper){
        return newActivityMapping(name, activityMapper);
    }

    public static BinderModule newActivityMapping(final String name, final Class<? extends ActivityMapper> activityMapperClass){
        return new BinderModule() {
            @Override
            protected void declare() {
                require(CompositeActivityMapper.class);
                within(Name.named(name), CompositeActivityMapper.class).multibind(ActivityMapper.class).to(activityMapperClass);
            }
        };
    }

    public static BinderModule newActivityMapping(final String name, final ActivityMapper activityMapper){
        return new BinderModule() {
            @Override
            protected void declare() {
                require(CompositeActivityMapper.class);
                within(Name.named(name), CompositeActivityMapper.class).multibind(ActivityMapper.class).to(activityMapper);
            }
        };
    }

}
