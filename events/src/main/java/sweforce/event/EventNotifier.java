package sweforce.event;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 5:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EventNotifier<H extends EventHandler> {

    public HandlerRegistration addHandler(H handler);

}
