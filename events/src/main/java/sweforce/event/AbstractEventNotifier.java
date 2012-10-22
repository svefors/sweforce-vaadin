package sweforce.event;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/17/12
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractEventNotifier<H extends EventHandler, E extends Event<H>>
        implements EventNotifier<H> {

    private Set<H> handlers = new HashSet<H>();

    @Override
    public HandlerRegistration addHandler(final H handler) {
        handlers.add(handler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                handlers.remove(handler);
            }
        };
    }

    protected void fireEvent(E event){
        for (H handler: handlers){
            event.dispatch(handler);
        }
    }
}
