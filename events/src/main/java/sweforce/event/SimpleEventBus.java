package sweforce.event;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/4/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Basic implementation of {@link EventBus}.
 */
@Singleton
public class SimpleEventBus implements EventBus {


    private final Multimap<Class<? extends Event>, EventHandler> eventHandlers = ArrayListMultimap.create();

    /**
     //     * Add and remove operations received during dispatch.
     //     */
//    private List<Command> deferredDeltas;

    /**
     * Class<? extends Event></?>
     * Map of event type to map of event source to list of their handlers.
     */
//    private final Map<Class<? extends Event>, Map<Object, List<?>>> map =
//            new HashMap<Class<? extends Event>, Map<Object, List<?>>>();
    public SimpleEventBus() {

    }


    @Override
    public <H extends EventHandler> HandlerRegistration addHandler(Class<? extends Event<H>> eventClass, H handler) {
        return doAdd(eventClass, handler);
    }

    @Override
    public <H extends EventHandler> void fireEvent(Event<H> event) {
        doFire(event, null);
    }


    protected <H extends EventHandler> void doRemove(Class<? extends Event<H>> eventClass, H handler) {
        eventHandlers.remove(eventClass, handler);

    }




    private <H extends EventHandler> HandlerRegistration doAdd(final Class<? extends Event<H>> eventClass,
                                                               final H handler) {
        if (eventClass == null) {
            throw new NullPointerException("Cannot add a handler with a null type");
        }
        if (handler == null) {
            throw new NullPointerException("Cannot add a null handler");
        }
        if (!eventHandlers.containsEntry(eventClass, handler)) {
            eventHandlers.put(eventClass, handler);
        }
        return new HandlerRegistration() {
            public void removeHandler() {
                doRemove(eventClass, handler);
            }
        };
    }


    @SuppressWarnings("unchecked")
    private synchronized <H extends EventHandler> Collection<H> getHandlers(Event<H> event) {
        return new ArrayList<H>((Collection<H>) eventHandlers.get(event.getClass()));
    }

    private <H extends EventHandler> void doFire(Event<H> event, Object source) {
        if (event == null) {
            throw new NullPointerException("Cannot fire null event");
        }
        for (H eventHandler : getHandlers(event)) {
           event.dispatch(eventHandler);
        }
    }


}
