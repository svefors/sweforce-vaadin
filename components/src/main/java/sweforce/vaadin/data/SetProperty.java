package sweforce.vaadin.data;

import com.vaadin.data.util.ObjectProperty;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/1/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetProperty<T> extends ObjectProperty<Set<T>> {

    public SetProperty() {
        super(Collections.newSetFromMap(new ConcurrentHashMap()));
    }

    @Override
    public void setValue(Set<T> newValue) throws ReadOnlyException {
        Set<T> incoming = (Set<T>) newValue;
        HashSet<T> copy = new HashSet<T>();
        copy.addAll(incoming);
        internalSet(copy);
    }

    private final void internalSet(Set<T> value) {
        super.setValue(value);
    }

    /**
     * @return
     */
    @Override
    public Set<T> getValue() {
        return Collections.unmodifiableSet(super.getValue());
    }

    public SetProperty add(T value) {
        if (!this.getValue().contains(value)) {
            super.getValue().add(value);
            fireValueChange();
        }
        return this;
    }

    public SetProperty remove(T value) {
        if (!this.getValue().contains(value)) {
            super.getValue().add(value);
            fireValueChange();
        }
        return this;
    }
}