package sweforce.vaadin.table.editor.navigator;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import sweforce.command.AbstractCommand;
import sweforce.vaadin.table.editor.CellGridId;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 12/2/12
* Time: 10:39 PM
* To change this template use File | Settings | File Templates.
*/
public class TraversalCommand extends AbstractCommand {

    public final Container.Ordered container;

    public final CellGridId.Property currentCell;

    private final GridTraversalOrder gridTraversal;

    public TraversalCommand(Container.Indexed container, CellGridId.Property currentCell, GridTraversalOrder gridTraversal) {
        this.gridTraversal = gridTraversal;
        this.container = container;
        this.currentCell = currentCell;
        currentCell.addValueChangeListener(new CurrentCellChangeListener());
        if (container instanceof Container.ItemSetChangeNotifier){
            ((Container.ItemSetChangeNotifier) container).addItemSetChangeListener(new GridRowsSetChangeListener());
        }
        refreshIsExeutableFromCellProperty(currentCell);
    }

    private class CurrentCellChangeListener implements Property.ValueChangeListener {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            TraversalCommand.this.refreshIsExeutableFromCellProperty((CellGridId.Property) event.getProperty());
        }
    }

    private class GridRowsSetChangeListener implements  Container.ItemSetChangeListener {
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
            hasNext = gridTraversal.hasNext(container, property.getValue());

        }
        setExecutable(hasNext);
    }

    @Override
    protected void internalExecute() {
        CellGridId nextCell = gridTraversal.next(container, currentCell.getValue());
        currentCell.setCell(nextCell);
    }

}
