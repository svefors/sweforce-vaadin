package sweforce.gui.ap.event;


import sweforce.gui.event.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountingEventBus extends SimpleEventBus {

    private final KeyedCounter<Class<? extends Event>> handlerCounts = new KeyedCounter<Class<? extends Event>>();
    private final KeyedCounter<Class<? extends Event>> firedCounts = new KeyedCounter<Class<? extends Event>>();
    private final KeyedCounter<TypeSourcePair> sourceCounts = new KeyedCounter<TypeSourcePair>();
    private final EventBus wrapped;

    public CountingEventBus() {
        this(new SimpleEventBus());
    }

    public CountingEventBus(EventBus wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void fireEvent(Event event) {
        wrapped.fireEvent(event);
        firedCounts.increment(event.getClass());
        sourceCounts.increment(new TypeSourcePair(event.getClass(), null));
    }


    @Override
    public <H extends EventHandler> HandlerRegistration addHandler(Class<? extends Event<H>> eventClass, H handler) {
        final HandlerRegistration superReg = wrapped.addHandler(eventClass, handler);
        handlerCounts.increment(eventClass);
        return makeReg(eventClass, superReg);
    }


    /**
     * How many events have fired for the given {@code type}. These events may not have been
     * passed to any handlers.
     */
    public int getFiredCount(Class<? extends Event> type) {
        return firedCounts.getCount(type);
    }

    /**
     * How many events have fired for the given pairing  of {@code type} and {@code source}. These
     * events may not have been passed to any handlers.
     */
    public int getFiredCountFromSource(Class<? extends Event> type, Object source) {
        return sourceCounts.getCount(new TypeSourcePair(type, source));
    }

    /**
     * How many handlers are registered for the given {@code type}.
     */
    public int getHandlerCount(Class<? extends Event> type) {
        return handlerCounts.getCount(type);
    }

    private <H> HandlerRegistration makeReg(final Class<? extends Event> type, final HandlerRegistration superReg) {
        return new HandlerRegistration() {
            public void removeHandler() {
                handlerCounts.decrement(type);
                superReg.removeHandler();
            }
        };
    }

    private class TypeSourcePair {
        final Class<? extends Event> type;
        final Object source;

        TypeSourcePair(Class<? extends Event> type, Object source) {
            this.type = type;
            this.source = source;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TypeSourcePair)) {
                return false;
            }

            TypeSourcePair pair = (TypeSourcePair) o;
            return doNullEquals(type, pair.type) && doNullEquals(source, pair.source);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = (hash * 31) + (type == null ? 0 : type.hashCode());
            hash = (hash * 31) + (source == null ? 0 : source.hashCode());
            return hash;
        }

        private boolean doNullEquals(Object a, Object b) {
            if ((a == null) ^ (b == null)) {
                return false;
            }
            return ((a == null) && (b == null)) || a.equals(b);
        }
    }

    private class KeyedCounter<K> {
        private Map<K, Integer> counts = new HashMap<K, Integer>();

        int getCount(K key) {
            Integer count = counts.get(key);
            return count == null ? 0 : count;
        }

        void decrement(K key) {
            counts.put(key, getCount(key) - 1);
        }

        void increment(K key) {
            counts.put(key, getCount(key) + 1);
        }
    }
}
