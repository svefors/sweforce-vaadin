package sweforce.collections;

import sweforce.event.Event;
import sweforce.event.EventHandler;

import java.util.Collection;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/24/12
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CollectionEvent<E> extends Event<CollectionEvent.Handler<E>> {


    public static class ElementAdded<E> implements CollectionEvent<E> {
        public final E element;

        public ElementAdded(E element) {
            this.element = element;
        }

        @Override
        public void dispatch(Handler handler) {
            handler.onAdded(element);
        }
    }

    public static class ElementRemoved<E> implements CollectionEvent<E> {
        public final E element;

        public ElementRemoved(E element) {
            this.element = element;
        }

        @Override
        public void dispatch(Handler<E> handler) {
            handler.onRemoved(element);
        }
    }

    public static class AllElementsAdded<E> implements CollectionEvent<E> {
        public final Collection<E> allAdded;

        public AllElementsAdded(Collection<E> allAdded) {
            this.allAdded = Collections.unmodifiableCollection(allAdded);
        }

        @Override
        public void dispatch(Handler<E> handler) {
            handler.onAllAdded(allAdded);
        }
    }

    public static class CollectionCleared<E> implements CollectionEvent<E> {

        @Override
        public void dispatch(Handler handler) {
            handler.onCleared();
        }
    }

    public static interface Handler<E> extends EventHandler {
        public <E> void onRemoved(E element);

        public <E> void onAdded(E element);

        public <E> void onAllAdded(Collection<E> elements);

        public void onCleared();
    }
}
