package sweforce.gui.place;

import org.junit.Test;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.event.HandlerRegistration;
import sweforce.gui.place.*;


import static org.junit.Assert.*;
import static sweforce.gui.place.PlaceSilkModule.bindPrefixMapping;

/**
* Created with IntelliJ IDEA.
* User: msvefors
* Date: 7/8/13
* Time: 4:38 PM
* To change this template use File | Settings | File Templates.
*/
public class TestPlaceSilkModule {

    private static final String TOKEN = "TOKEN";

    private static final String PREFIX = "PREFIX";

    private static class SomePlace extends Place {
        private static SomePlace INSTANCE = new SomePlace();

        private SomePlace() {
        }

        private static SomePlace getINSTANCE() {
            return INSTANCE;
        }

        private final static class Tokenizer implements PlaceTokenizer<SomePlace> {
            @Override
            public SomePlace getPlace(String token) {
                return SomePlace.getINSTANCE();
            }

            @Override
            public String getToken(SomePlace somePlace) {
                return TOKEN;
            }
        }

        ;
    }


    private static class PlaceHistoryBundle extends BootstrapperBundle {
        @Override
        protected void bootstrap() {
            install(PlaceSilkModule.class);
            install(new BinderModule() {
                @Override
                protected void declare() {
                    bindPrefixMapping(this, new PrefixPlaceTokenizerMapping(PREFIX, new SomePlace.Tokenizer()));
                    bind(PlaceHistoryHandler.Historian.class).to(new PlaceHistoryHandler.Historian(){
                        @Override
                        public String getToken() {
                            return null;  //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public void newItem(String historyToken) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public HandlerRegistration addValueChangeHandler(PlaceHistoryHandler.HistoryChangedEvent.Handler handler) {
                            return null;  //To change body of implemented methods use File | Settings | File Templates.
                        }
                    });
                }
            });
        }
    }

    @Test
    public void testPlaceHistoryMapper_not_null() {
        Injector injector = Bootstrap.injector(PlaceHistoryBundle.class);
        PlaceHistoryMapper placeHistoryMapper =
                injector.resolve(Dependency.dependency(PlaceHistoryMapper.class));
        assertNotNull(placeHistoryMapper);
    }

    @Test
    public void testPlaceHistoryMapper_application_singleton() {
        Injector injector = Bootstrap.injector(PlaceHistoryBundle.class);
        PlaceHistoryMapper placeHistoryMapper =
                injector.resolve(Dependency.dependency(PlaceHistoryMapper.class));
        assertSame(placeHistoryMapper, injector.resolve(Dependency.dependency(PlaceHistoryMapper.class)));
    }



    @Test
    public void testPlaceHistoryMapper_prefix_tokenizer() {
        Injector injector = Bootstrap.injector(PlaceHistoryBundle.class);
        PlaceHistoryMapper placeHistoryMapper =
                injector.resolve(Dependency.dependency(PlaceHistoryMapper.class));
        assertEquals(PREFIX + ':' + new SomePlace.Tokenizer().getToken(SomePlace.getINSTANCE()),
                placeHistoryMapper.getToken(SomePlace.getINSTANCE()));
        assertEquals(new SomePlace.Tokenizer().getPlace(TOKEN), placeHistoryMapper.getPlace(PREFIX + ':' + TOKEN));
    }

    @Test
    public void testPlaceHistoryHandler_not_null(){
        Injector injector = Bootstrap.injector(PlaceHistoryBundle.class);
        PlaceHistoryHandler placeHistoryHandler = injector.resolve(Dependency.dependency(PlaceHistoryHandler.class));
        assertNotNull(placeHistoryHandler);
    }

    @Test
    public void testPlaceHistoryHandler_application_singleton(){
        Injector injector = Bootstrap.injector(PlaceHistoryBundle.class);
        PlaceHistoryHandler placeHistoryHandler = injector.resolve(Dependency.dependency(PlaceHistoryHandler.class));
        assertSame(placeHistoryHandler, injector.resolve(Dependency.dependency(PlaceHistoryHandler.class)));
    }
}
