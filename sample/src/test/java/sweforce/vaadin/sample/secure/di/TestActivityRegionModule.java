package sweforce.vaadin.sample.secure.di;


import org.junit.Test;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.event.EventBus;
import sweforce.gui.activity.AbstractActivity;
import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.CompositeActivityMapper;
import sweforce.gui.display.Display;
import sweforce.gui.place.Place;
import sweforce.vaadin.sample.secure.bind.ActivityRegionModule;

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
            ActivityRegionModule leftActivityRegionModule = new ActivityRegionModule(left);
            ActivityRegionModule rightActivityRegionModule = new ActivityRegionModule(right);
            install(leftActivityRegionModule);
            install(rightActivityRegionModule);
            leftActivityRegionModule.newActivityMapping(secondActivity);
            leftActivityRegionModule.newActivityMapping(FirstActivityMapper.class);
            install(new BinderModule() {
                @Override
                protected void declare() {
                    bind(FirstActivityMapper.class).toConstructor();
                }
            });
            rightActivityRegionModule.newActivityMapping(thirdActivity);
        }
    }

    private static final String left = "left";
    private static final String right = "right";



    private static final Place placeA = new Place(){};
    private static final Place placeB = new Place(){};


    private static class FirstActivityMapper implements ActivityMapper {
        @Override
        public Activity getActivity(Place place) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }



    private static ActivityMapper secondActivity = new ActivityMapper() {
        @Override
        public Activity getActivity(Place place) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    private static ActivityMapper thirdActivity = new ActivityMapper() {
        @Override
        public Activity getActivity(Place place) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    @Test
    public void testLeftCompositeActivityMapper_not_null() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(CompositeActivityMapper.class).named(left));
        assertNotNull(activityMapper);
    }

    @Test
    public void testFirstActivityMapper() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(left));

    }

    @Test
    public void testRightCompositeActivityMapper_not_null() {
        Injector injector = Bootstrap.injector(Bundle.class);
        ActivityMapper activityMapper = injector.resolve(Dependency.dependency(ActivityMapper.class).named(right));
        assertNotNull(activityMapper);
    }

}
