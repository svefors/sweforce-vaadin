package sweforce.vaadin.table.editor.fieldfactory;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import sweforce.vaadin.table.editor.CellGridId;

/**
 * Created with IntelliJ IDEA.
 * User: SERVICE-NOW\mats.svefors
 * Date: 09/12/2012
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class TableFieldFactoryWrapper implements TableFieldFactory {

    private final TableFieldFactory inner;

    private CellGridId.SetProperty cellsInEditMode;

    public TableFieldFactoryWrapper(TableFieldFactory inner) {
        this.inner = inner;
    }

    public void bind(CellGridId.SetProperty cellsInEditMode){
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
