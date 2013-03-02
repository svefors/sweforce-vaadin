package sweforce.vaadin.table.editor.fieldfactory;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import sweforce.vaadin.table.editor.CellGridId;

/**
 *
 */
public class TableFieldFactoryWrapper implements TableFieldFactory {

    private final TableFieldFactory inner;

    public final CellGridId.SetProperty cellsInEditMode;

    public TableFieldFactoryWrapper(CellGridId.SetProperty cellsInEditMode, TableFieldFactory inner) {
        this.inner = inner;
        this.cellsInEditMode = cellsInEditMode;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        if(this.cellsInEditMode.getValue().contains(new CellGridId(itemId, propertyId))){
            return inner.createField(container, itemId, propertyId, uiContext);
        }else{
            return null;
        }
    }
}
