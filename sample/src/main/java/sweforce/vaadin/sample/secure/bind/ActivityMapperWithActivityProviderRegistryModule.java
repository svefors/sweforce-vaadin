package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.*;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.activity.registry.ActivityMapperWithRegistry;
import sweforce.gui.region.Region;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/8/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityMapperWithActivityProviderRegistryModule extends BinderModule {

//    private final Region region;

    public final String name;

    public final ActivityFactoryRegistry activityFactoryRegistry = new ActivityFactoryRegistry.Impl();

    private final ActivityMapper activityMapper = new ActivityMapperWithRegistry(activityFactoryRegistry);

    public ActivityMapperWithActivityProviderRegistryModule(String name) {
        this.name = name;
    }

    public ActivityMapperWithActivityProviderRegistryModule(Region region) {
        this.name = region.toString();
    }

    @Override
    protected void declare() {
//        bind(Name.named(name), ActivityMapper.class);
        bind(Name.named(name), ActivityMapper.class).to(activityMapper);

//        bind(Name.named(name), ActivityProviderRegistry.class).to(activityProviderRegistry);

//        bind(Name.named(name), ActivityProviderRegistry.Impl.class);
//        injectingInto(Name.named(name), ActivityProviderRegistry.Impl.class)
//                .bind(ActivityProviderRegistry.Entry[].class).to(Name.named(name), ActivityProviderRegistry.Entry[].class);
//
//        arraybind(ActivityProviderRegistry.Entry[].class).to(new ActivityProviderRegistry.Entry[0]);

//        bind()
//        bind(Name.named(name), CompositeActivityMapper.class).toConstructor();
        //injectingInto( left, B.class )
        // .bind( A[].class ).to( special, A[].class );
//        			arraybind( A[].class ).to( new A[0] );
//        injectingInto(Name.named(name), CompositeActivityMapper.class)
//                .bind(ActivityMapper[].class).to(Name.named(multiBinderName),  ActivityMapper[].class);
//        arraybind(ActivityMapper[].class).to(new ActivityMapper[0]);
    }




    /*
    bind(ActivityProviderRegistry.Entry.class,
     */

//
//    public  BinderModule newModuleWith(final ActivityProviderRegistry.Entry entry){
//        return new BinderModule() {
//            @Override
//            protected void declare() {
////                within(Name.named(name), ActivityProviderRegistry.Impl.class).
//                injectingInto(Name.named(name), ActivityProviderRegistry.Impl.class)
//                        .multibind(ActivityProviderRegistry.Entry.class).to(entry);
//            }
//        };
//    }
//
//    public  BinderModule newModuleWith(final Class<? extends ActivityProviderRegistry.Entry> entryClass){
//        return new BinderModule() {
//            @Override
//            protected void declare() {
//                provide(entryClass);
//                injectingInto(Name.named(name), ActivityProviderRegistry.Impl.class)
//                        .multibind(ActivityProviderRegistry.Entry.class).to(entryClass);
//            }
//        };
//    }




}
