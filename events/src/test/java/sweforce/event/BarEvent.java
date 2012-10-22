package sweforce.event;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BarEvent implements Event<BarEvent.Handler> {

    @Override
    public void dispatch(Handler handler) {
        handler.onBar(this);
    }

    public static interface Handler extends EventHandler {
        public void onBar(BarEvent event);
    }

    public static HandlerRegistration register(EventBus bus, Handler handler) {
        return bus.addHandler(BarEvent.class, handler);
    }
}
