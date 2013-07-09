package sweforce.vaadin.sample.secure.di;


import org.junit.Test;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.event.EventBus;
import sweforce.gui.activity.*;
import sweforce.gui.display.Display;
import sweforce.gui.place.Place;
import sweforce.vaadin.sample.secure.bind.ActivityMapperWithActivityProviderRegistryModule;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/8/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestActivityRegionModule {

    private static class Bundle extends BootstrapperBundle {
        @Override
        protected void bootstrap() {
            ActivityMapperWithActivityProviderRegistryModule leftActivityMapperModule =
                    new ActivityMapperWithActivityProviderRegistryModule(left);

            ActivityMapperWithActivityProviderRegistryModule rightActivityMapperModule =
                    new ActivityMapperWithActivityProviderRegistryModule(right);

            install(leftActivityMapperModule);
            install(rightActivityMapperModule);

            leftActivityMapperModule.activityProviderRegistry.add(
                    new ActivityProviderRegistry.Entry(placeA, new FirstActivityProvider()));

            leftActivityMapperModule.activityProviderRegistry.add(
                    new ActivityProviderRegistry.Entry(placeB, activityLeft2));

            rightActivityMapperModule.activityProviderRegistry.add(
                    new ActivityProviderRegistry.Entry(placeA, activityRight));
        }
    }

    /*
    what is good and what is bad?
    how to
     */

    private static final String left = "left";
    private static final String right = "right";

    private static final Place placeA = new Place() {
    };
    private static final Place placeB = new Place() {
    };

    private static final Activity activityLeft1 = new AbstractActivity() {
        @Override
        public void start(Display panel, EventBus eventBus) {
        }
        public String toString(){ return "activityLeft1";}
    };

    private static final Activity activityLeft2 = new AbstractActivity() {
        @Override
        public void start(Display panel, EventBus eventBus) {
        }
        public String toString(){ return "activityLeft2";}
    };

    private static final Activity activityRight = new AbstractActivity() {
        @Override
        public void start(Display panel, EventBus eventBus) {
        }
        public String toString(){ return "activityRight";}
    };


    private static class FirstActivityProvider implements ActivityProvider<Activity> {
        /*
        there might be some dependencies injected into this provider
         */
        @Override
        public Activity provide() {
            return activityLeft1;
        }

    }

    @Test
    public void testLeftCompositeActivityMapper_not_null() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(left));
        assertNotNull(activityMapper);
    }

    @Test
    public void testLeftRightActivityMapper_not_same() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper leftActivityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(left));
        ActivityMapper rightActivityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(right));
        assertNotSame(leftActivityMapper, rightActivityMapper);
    }


    @Test
    public void testLeftActivityMapper_activityMappers() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(left));
        assertSame(activityLeft1, activityMapper.getActivity(placeA));
        assertSame(activityLeft2, activityMapper.getActivity(placeB));
    }

    @Test
    public void testRightActivityMapper_activityMappers() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(right));
        assertSame(activityRight, activityMapper.getActivity(placeA));
        assertNull(activityMapper.getActivity(placeB));
    }

    @Test
    public void testRightCompositeActivityMapper_not_null() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(right));
        assertNotNull(activityMapper);
    }

}
