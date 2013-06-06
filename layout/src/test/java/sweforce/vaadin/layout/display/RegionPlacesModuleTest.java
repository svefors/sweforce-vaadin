package sweforce.vaadin.layout.display;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.junit.Test;

import static org.junit.Assert.*;

import sweforce.event.EventBus;
import sweforce.gui.activity.AbstractActivity;
import sweforce.gui.activity.Activity;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.Prefix;
import sweforce.gui.display.Display;
import sweforce.gui.display.region.RegionActivityMapperFactory;
import sweforce.gui.guice.RegionPlacesModule;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 3/2/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegionPlacesModuleTest {

    RegionPlacesModule module = new RegionPlacesModule() {

        @Override
        protected void configurePlaces() {
            this.configurePlaceRegionActivity(Place1.class, Region.LEFT, LeftActivity.class);
            this.configurePlaceRegionActivity(Place2.class, Region.LEFT, LeftActivity.class);
            this.configurePlaceRegionActivity(Place1.class, Region.RIGHT, Place1RightActivity.class);
            this.configurePlaceRegionActivity(Place2.class, Region.RIGHT, Place2RightActivity.class);
            this.bind(Injectee.class);
        }

    };

    @Test
    public void testMultiplePlacesAndRegions() {

        Injector injector = Guice.createInjector(module);
        Injectee injectee = injector.getInstance(Injectee.class);
        assertNotNull(injectee);
        assertNotNull(injectee.regionActivityMapperFactory);

        Activity leftPlace1Activity = injectee.regionActivityMapperFactory.create(Region.LEFT).getActivity(new Place1());
        assertNotNull(leftPlace1Activity);
        assertEquals(LeftActivity.class, leftPlace1Activity.getClass());

        Activity leftPlace2Activity = injectee.regionActivityMapperFactory.create(Region.LEFT).getActivity(new Place2());
        assertNotNull(leftPlace1Activity);
        assertEquals(LeftActivity.class, leftPlace2Activity.getClass());

        Activity rightPlace1Activity = injectee.regionActivityMapperFactory.create(Region.RIGHT).getActivity(new Place1());
        assertNotNull(rightPlace1Activity);
        assertEquals(Place1RightActivity.class, rightPlace1Activity.getClass());

        Activity rightPlace2Activity = injectee.regionActivityMapperFactory.create(Region.RIGHT).getActivity(new Place2());
        assertNotNull(rightPlace2Activity);
        assertEquals(Place2RightActivity.class, rightPlace2Activity.getClass());
    }


    public static class Injectee {

        public final RegionActivityMapperFactory regionActivityMapperFactory;

        @Inject
        public Injectee(RegionActivityMapperFactory regionActivityMapperFactory) {
            this.regionActivityMapperFactory = regionActivityMapperFactory;
        }
    }

    @Prefix("p1")
    public static class Place1 extends Place {

        public static class Tokenizer implements PlaceTokenizer<Place1> {
            @Override
            public Place1 getPlace(String s) {
                return new Place1();
            }

            @Override
            public String getToken(Place1 loginPlace) {

                return "";
            }
        }
    }

    @Prefix("p2")
    public static class Place2 extends Place {

        public static class Tokenizer implements PlaceTokenizer<Place2> {
            @Override
            public Place2 getPlace(String s) {
                return new Place2();
            }

            @Override
            public String getToken(Place2 loginPlace) {

                return "";
            }
        }
    }

    public static class LeftActivity extends AbstractActivity {
        @Override
        public void start(Display panel, EventBus eventBus) {
        }
    }

    public static class Place2RightActivity extends AbstractActivity {
        @Override
        public void start(Display panel, EventBus eventBus) {
        }
    }

    public static class Place1RightActivity extends AbstractActivity {
        @Override
        public void start(Display panel, EventBus eventBus) {
        }
    }

    public static enum Region implements sweforce.gui.display.region.Region {
        LEFT, RIGHT
    }
}
