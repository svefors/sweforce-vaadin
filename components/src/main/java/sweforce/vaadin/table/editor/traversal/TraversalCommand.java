package sweforce.vaadin.table.editor.traversal;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import sweforce.command.AbstractCommand;
import sweforce.vaadin.table.editor.CellGridId;

/**
 *
 */
public class TraversalCommand extends AbstractCommand {

    public Table table;

    public CellGridId.Property currentCell;

    private final GridTraversalOrder gridTraversal;

//    public TraversalCommand(Table table, GridTraversalOrder gridTraversal) {
//        this.gridTraversal = gridTraversal;
//        bind(table);
//        bind(currentCell);
//    }
//
//    public TraversalCommand(Table table, CellGridId.Property currentCell, GridTraversalOrder gridTraversal) {
//        this.gridTraversal = gridTraversal;
//        bind(table);
//        bind(currentCell);
//    }

    public TraversalCommand(GridTraversalOrder gridTraversal) {
        this.gridTraversal = gridTraversal;
    }

    private CurrentCellChangeListener currentCellChangeListener = new CurrentCellChangeListener();

    private class CurrentCellChangeListener implements Property.ValueChangeListener {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            TraversalCommand.this.refreshIsExeutableFromCellProperty((CellGridId.Property) event.getProperty());
        }
    }

    private GridRowsSetChangeListener gridRowsSetChangeListener = new GridRowsSetChangeListener();


    private class GridRowsSetChangeListener implements Container.ItemSetChangeListener {
        @Override
        public void containerItemSetChange(Container.ItemSetChangeEvent event) {
            refreshIsExeutableFromCellProperty(currentCell);
        }
    }

    private void refreshIsExeutableFromCellProperty(CellGridId.Property property) {
        boolean hasNext;
        if (property.getValue() == null) {
            hasNext = false;
        } else {
            hasNext = gridTraversal.hasNext(table, property.getValue());

        }
        setExecutable(hasNext);
    }

    @Override
    protected void internalExecute() {
        CellGridId nextCell = gridTraversal.next(table, currentCell.getValue());
        currentCell.setCell(nextCell);
    }

    public void bind(CellGridId.Property currentCell){
        if (this.currentCell != null)
            this.currentCell.removeValueChangeListener(this.currentCellChangeListener);
        this.currentCell = currentCell;
        if (this.currentCell != null)
            currentCell.addValueChangeListener(currentCellChangeListener);
    }

    public void bind(Container container) {
        if (this.table != null && container instanceof Container.ItemSetChangeNotifier)
            ((Container.ItemSetChangeNotifier) container).removeItemSetChangeListener(gridRowsSetChangeListener);
        this.table = table;
        if (this.table != null && container instanceof Container.ItemSetChangeNotifier)
            ((Container.ItemSetChangeNotifier) container).addItemSetChangeListener(gridRowsSetChangeListener);
    }

}
