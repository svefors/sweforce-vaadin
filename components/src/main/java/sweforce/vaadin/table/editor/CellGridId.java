package sweforce.vaadin.table.editor;

import com.vaadin.data.util.ObjectProperty;

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

        public void setCell(CellGridId cellGridId){
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
}

