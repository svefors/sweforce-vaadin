package sweforce.vaadin.table.editor.traversal;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import sweforce.command.AbstractCommand;
import sweforce.vaadin.table.editor.CellGridId;
import sweforce.vaadin.table.editor.EditableTable;

/**
 * A Traversal Command is used to traverse over a grid. It will be executable if the current cell is not null and the
 * GridTraversalOrder.hasNext(Table table, CellGridId cellGridId) returns true.
 */
public class TraversalCommand extends AbstractCommand {

    private final GridTraversalOrder gridTraversal;

    /**
     * @param gridTraversal
     */
    public TraversalCommand(GridTraversalOrder gridTraversal) {
        this.gridTraversal = gridTraversal;
//        this.table = table;
//        this.currentCell = currentCell;
//        this.currentCell.addValueChangeListener(new CurrentCellChangeListener());
//        if (this.table.getContainerDataSource() instanceof Container.ItemSetChangeNotifier)
//            ((Container.ItemSetChangeNotifier) this.table.getContainerDataSource()).addItemSetChangeListener(new GridRowsSetChangeListener());
//        this.table.addColumnReorderListener(new ColumnReorderListener());
    }

    private EditableTable editableTable;

    public void bind(EditableTable editableTable) {
        unbind();
        this.editableTable = editableTable;
        this.editableTable.currentCell.addValueChangeListener(currentCellChangeListener);
        this.editableTable.addColumnReorderListener(columnReorderListener);
        if (this.editableTable.getContainerDataSource() != null
                && this.editableTable.getContainerDataSource() instanceof Container.ItemSetChangeNotifier
                ) {
            ((Container.ItemSetChangeNotifier) this.editableTable.getContainerDataSource())
                    .addItemSetChangeListener(gridRowsSetChangeListener);
        }
    }

    public void unbind() {
        if (this.editableTable != null) {
            this.editableTable.currentCell.removeValueChangeListener(currentCellChangeListener);
            this.editableTable.removeColumnReorderListener(columnReorderListener);
            if (this.editableTable.getContainerDataSource() instanceof Container.ItemSetChangeNotifier
                    ) {
                ((Container.ItemSetChangeNotifier) this.editableTable.getContainerDataSource())
                        .removeItemSetChangeListener(gridRowsSetChangeListener);
            }
            this.editableTable = null;
        }

    }

    private ColumnReorderListener columnReorderListener = new ColumnReorderListener();

    private class ColumnReorderListener implements Table.ColumnReorderListener {
        @Override
        public void columnReorder(Table.ColumnReorderEvent event) {
            refreshIsExecutableFromCellProperty(editableTable.currentCell);
        }
    }

    private Property.ValueChangeListener currentCellChangeListener = new CurrentCellChangeListener();

    private class CurrentCellChangeListener implements Property.ValueChangeListener {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            TraversalCommand.this.refreshIsExecutableFromCellProperty((CellGridId.Property) event.getProperty());
        }
    }

    private GridRowsSetChangeListener gridRowsSetChangeListener = new GridRowsSetChangeListener();

    private class GridRowsSetChangeListener implements Container.ItemSetChangeListener {
        @Override
        public void containerItemSetChange(Container.ItemSetChangeEvent event) {
            refreshIsExecutableFromCellProperty(editableTable.currentCell);
        }
    }

    private void refreshIsExecutableFromCellProperty(CellGridId.Property property) {
        editableTable.getClass();
        boolean hasNext = !property.getValue().equals(CellGridId.NULL) && gridTraversal.hasNext(editableTable, property.getValue());
        setExecutable(hasNext);
    }


    @Override
    protected void internalExecute() {
        CellGridId nextCell = gridTraversal.next(editableTable, editableTable.currentCell.getValue());
        editableTable.currentCell.setCell(nextCell);
    }


}
