package sweforce.vaadin.table.editor;

import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/1/12
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CellGridId {

    public final Object itemId;

    public final Object propertyId;

    public CellGridId withItemId(Object itemId) {
        return new CellGridId(itemId, this.propertyId);
    }

    public CellGridId withPropertyId(Object propertyId) {
        return new CellGridId(propertyId, this.itemId);
    }

    public CellGridId(Object itemId, Object propertyId) {
        this.itemId = itemId;
        this.propertyId = propertyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellGridId that = (CellGridId) o;

        if (!itemId.equals(that.itemId)) return false;
        if (!propertyId.equals(that.propertyId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + propertyId.hashCode();
        return result;
    }

    public static class Property extends ObjectProperty<CellGridId> {

        public Property(Object itemId, Object propertyId) {
            super(new CellGridId(itemId, propertyId));
        }

        public Property(CellGridId value) {
            super(value);
        }

        public Property() {
            super((CellGridId) null);
        }

        public Property setIds(Object itemId, Object propertyId) {
            this.setValue(new CellGridId(itemId, propertyId));
            return this;
        }

        public void setCell(CellGridId cellGridId) {
            this.setValue(cellGridId);
        }

        public Property setItemId(Object itemId) {
            this.setValue(new CellGridId(itemId, this.getValue() != null ? this.getValue().propertyId : null));
            return this;
        }

        public Property setPropertyId(Object propertyId) {
            this.setValue(new CellGridId(this.getValue() != null ? this.getValue().itemId : null, propertyId));
            return this;
        }
    }


    public static class SetProperty extends ObjectProperty<Set<CellGridId>> {

        public SetProperty() {
            super(Collections.newSetFromMap(new ConcurrentHashMap()));
        }

        @Override
        public void setValue(Object newValue) throws ReadOnlyException {
            Set<CellGridId> incoming = (Set<CellGridId>) newValue;
            HashSet<CellGridId> copy = new HashSet<CellGridId>();
            copy.addAll(incoming);
            internalSet(copy);
        }

        private final void internalSet(Set<CellGridId> value) {
            super.setValue(value);
        }

        /**
         *
         *
         * @return
         */
        @Override
        public Set<CellGridId> getValue() {
            return Collections.unmodifiableSet(super.getValue());
        }

        public SetProperty addCell(CellGridId cellGridId) {
            if (!this.getValue().contains(cellGridId)) {
                super.getValue().add(cellGridId);
                fireValueChange();
            }
            return this;
        }

        public SetProperty removeCell(CellGridId cellGridId) {
            if (!this.getValue().contains(cellGridId)) {
                super.getValue().add(cellGridId);
                fireValueChange();
            }
            return this;
        }
    }
}

