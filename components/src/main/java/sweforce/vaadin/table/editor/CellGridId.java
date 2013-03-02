package sweforce.vaadin.table.editor;

import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Table;
import sweforce.vaadin.data.SetProperty;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A immutable class representing a cell in a table identified by row(itemId) and column(propertyId)
 *
 */
public class CellGridId {

    public final Object itemId;

    public final Object propertyId;

    private CellGridId() {
        itemId = null;
        propertyId = null;
    }

    public static CellGridId NULL = new CellGridId();

    public CellGridId withItemId(Object itemId) {
        if (itemId == null)
            throw new IllegalArgumentException("item Id can't be null, try using CellGrid.NULL");
        return new CellGridId(itemId, this.propertyId);
    }

    public CellGridId withPropertyId(Object propertyId) {
        if (propertyId == null)
            throw new IllegalArgumentException("property Id can't be null, try using CellGrid.NULL");
        return new CellGridId(this.itemId, propertyId);
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

        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (propertyId != null ? !propertyId.equals(that.propertyId) : that.propertyId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        return result;
    }

    /**
     * A Vaadin Property containing a CellGridId
     */
    public static class Property extends ObjectProperty<CellGridId> {

        public Property(Object itemId, Object propertyId) {
            super(new CellGridId(itemId, propertyId));
        }

        public Property(CellGridId value) {
            super(value);
        }

        public Property() {
            super(CellGridId.NULL);
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

        /**
         * I will refresh the row cache on the table when my value changes.
         * @param table
         */
        public void refreshRowCacheOnValueChange(Table table){
            addValueChangeListener(new RefreshRowCacheListener(table));
        }

        private static class RefreshRowCacheListener implements ValueChangeListener{

            final Table table;

            public RefreshRowCacheListener(Table table) {
                this.table = table;
            }

            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                table.refreshRowCache();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                RefreshRowCacheListener that = (RefreshRowCacheListener) o;

                if (table != null ? !table.equals(that.table) : that.table != null) return false;

                return true;
            }

            @Override
            public int hashCode() {
                return table != null ? table.hashCode() : 0;
            }
        }

    }

    /**
     * A Vaadin Property containing a set of CellGridIds
     */
    public static class SetProperty extends sweforce.vaadin.data.SetProperty<CellGridId>{
        public SetProperty() {
        }
    }
}

