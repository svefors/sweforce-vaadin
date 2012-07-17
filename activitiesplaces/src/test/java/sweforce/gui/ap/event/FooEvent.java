package sweforce.gui.ap.event;

import sweforce.gui.event.Event;
import sweforce.gui.event.EventBus;
import sweforce.gui.event.EventHandler;
import sweforce.gui.event.HandlerRegistration;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class FooEvent implements Event<FooEvent.Handler>{

    @Override
    public void dispatch(Handler handler) {
        handler.onFoo(this);
    }

    public static interface Handler extends EventHandler{
        public void onFoo(FooEvent fooEvent);
    }

    public static HandlerRegistration register(EventBus bus, Handler handler) {
        return bus.addHandler(FooEvent.class, handler);
    }
}
