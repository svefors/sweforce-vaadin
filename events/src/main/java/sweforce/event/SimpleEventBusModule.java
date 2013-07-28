package sweforce.event;

import se.jbee.inject.bind.BinderModule;
import sweforce.event.EventBus;
import sweforce.event.SimpleEventBus;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:00 AM
* To change this template use File | Settings | File Templates.
*/
public class SimpleEventBusModule extends BinderModule {
    @Override
    protected void declare() {
        bind(EventBus.class).to(SimpleEventBus.class);

    }
}
