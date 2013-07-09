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

import java.util.UUID;

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

    public final String multiBinderName = UUID.randomUUID().toString();

    public ActivityRegionModule(String name) {
        this.name = name;
    }

    public ActivityRegionModule(Region region) {
        this.name = region.toString();
    }

    @Override
    protected void declare() {
//        bind(Name.named(name), ActivityMapper.class).to(CompositeActivityMapper.class);
        bind(Name.named(name), CompositeActivityMapper.class).toConstructor();
        //injectingInto( left, B.class )
        // .bind( A[].class ).to( special, A[].class );
//        			arraybind( A[].class ).to( new A[0] );
        injectingInto(Name.named(name), CompositeActivityMapper.class)
                .bind(ActivityMapper[].class).to(Name.named(multiBinderName),  ActivityMapper[].class);
        arraybind(ActivityMapper[].class).to(new ActivityMapper[0]);
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

    public  BinderModule newActivityMapping(final String name, final Class<? extends ActivityMapper> activityMapperClass){
        return new BinderModule() {
            @Override
            protected void declare() {
//                require(CompositeActivityMapper.class);
                injectingInto(Name.named(name), CompositeActivityMapper.class).multibind(Name.named(multiBinderName), ActivityMapper.class).to(activityMapperClass);
            }
        };
    }

    public  BinderModule newActivityMapping(final String name, final ActivityMapper activityMapper){
        return new BinderModule() {
            @Override
            protected void declare() {
//                require(CompositeActivityMapper.class);
                injectingInto(Name.named(name), CompositeActivityMapper.class).multibind(Name.named(multiBinderName), ActivityMapper.class).to(activityMapper);
            }
        };
    }

}
