package sweforce.vaadin.sample.secure.di;

import org.junit.Test;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;
import sweforce.event.EventBus;
import sweforce.event.SimpleEventBus;
import sweforce.event.EventModule;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/8/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestEventModuleDI {

    @Test
    public void testEventModuleInstance() {
        Injector injector = Bootstrap.injector(EventModule.class);
        assertNotNull(injector);
        EventBus eventBus = injector.resolve(Dependency.dependency(EventBus.class));
        assertNotNull(eventBus);
        assertEquals(SimpleEventBus.class, eventBus.getClass());
        assertSame(eventBus, injector.resolve(Dependency.dependency(EventBus.class)));
    }


}
