package sweforce.event;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/21/12
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class MessageEvent<T> implements Event<MessageEvent.Handler<T>> {

    private final T message;

    public MessageEvent(T message) {
        this.message = message;
    }

    @Override
    public void dispatch(Handler handler) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public static interface Handler<T> extends EventHandler{
        public void onMessage(T message);
    }
}
