package sweforce.vaadin.table.editor;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

/**
 * Created with IntelliJ IDEA.
 * User: SERVICE-NOW\mats.svefors
 * Date: 09/12/2012
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class EditableTable extends Table {

    private CellGridId.Property currentCell;

    private CellGridId.SetProperty cellsInEditMode;

    public EditableTable() {
    }

    public EditableTable(String caption) {
        super(caption);
    }

    public EditableTable(String caption, Container dataSource) {
        super(caption, dataSource);
    }


    public void bind(CellGridId.Property currentCell, CellGridId.SetProperty cellsInEditMode){
        this.currentCell = currentCell;
        this.cellsInEditMode = cellsInEditMode;
    }

    @Override
    public void setContainerDataSource(Container newDataSource) {
        super.setContainerDataSource(newDataSource);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
