package sweforce.vaadin.table.editor;

import com.vaadin.data.Container;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/3/12
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class FocusedCellProperty extends CellGridId.Property {

    public FocusedCellProperty(Object itemId, Object propertyId) {
        super(itemId, propertyId);
    }

    public FocusedCellProperty(CellGridId value) {
        super(value);
    }

    public FocusedCellProperty() {
    }

    public TableFieldFactory focusDecorator(TableFieldFactory tableFieldFactory){
        return new TableFieldFactoryDecorator(tableFieldFactory);
    }

    private class TableFieldFactoryDecorator implements TableFieldFactory {

        private final TableFieldFactory inner;

        public TableFieldFactoryDecorator(TableFieldFactory inner) {
            this.inner = inner;
        }

        @Override
        public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
            Field<?> field = inner.createField(container, itemId, propertyId, uiContext);
            if (field != null && FocusedCellProperty.this.getValue().equals(new CellGridId(itemId,  propertyId))){
                field.focus();
            }
            return field;
        }

    }
}
