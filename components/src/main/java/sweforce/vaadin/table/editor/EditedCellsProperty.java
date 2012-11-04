package sweforce.vaadin.table.editor;

import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import sweforce.vaadin.data.SetProperty;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/3/12
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditedCellsProperty extends SetProperty<CellGridId> {

    public void refreshTableOnValueChange(Table table){
        this.addValueChangeListener(new TableRefreshListener(table));
    }






    public static class TableRefreshListener implements ValueChangeListener {

        private final Table table;

        public TableRefreshListener(Table table) {
            this.table = table;
        }

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            table.markAsDirty();
        }
    }
}
