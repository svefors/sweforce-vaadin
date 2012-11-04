package sweforce.vaadin.data;

import com.vaadin.data.util.ObjectProperty;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/1/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetProperty<T> extends ObjectProperty<Set<T>> {

    private Set<T> values = new HashSet<T>();

    public SetProperty() {
        super(new HashSet<T>());
    }

    public boolean addValue(T value) {
        if(this.values.add(value)){
            this.setValue(this.values);
            return true;
        }
        return false;
    }

    @Override
    public void setValue(Object newValue) throws ReadOnlyException {
        values = (Set<T>) newValue;
        super.setValue(newValue);
    }

    @Override
    public Set<T> getValue() {
        return Collections.unmodifiableSet(super.getValue());
    }


}
