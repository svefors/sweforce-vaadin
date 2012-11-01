package sweforce.collections;

import sweforce.event.EventHandler;
import sweforce.event.EventNotifier;
import sweforce.event.HandlerRegistration;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/24/12
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObservableSet<E> implements ObservableCollection<E>, Set<E>{

    private final Set<E> delegate;

    private final CollectionEventNotifier collectionEventNotifier = new CollectionEventNotifier();

    public ObservableSet(Set delegate) {
        this.delegate = delegate;
    }

    @Override
    public HandlerRegistration addHandler(CollectionEvent.Handler<E> handler) {
        return collectionEventNotifier.addHandler(handler);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return delegate.toArray(ts);
    }

    public boolean add(E e) {
        return delegate.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return delegate.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return delegate.containsAll(objects);
    }

    public boolean addAll(Collection<? extends E> es) {
        return delegate.addAll(es);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return delegate.retainAll(objects);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return delegate.removeAll(objects);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }
}
