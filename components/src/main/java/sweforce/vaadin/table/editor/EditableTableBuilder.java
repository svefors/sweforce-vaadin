package sweforce.vaadin.table.editor;

import com.vaadin.data.Container;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import sweforce.command.Command;
import sweforce.vaadin.keyboard.CommandActionHandler;
import sweforce.vaadin.keyboard.KeyBinding;
import sweforce.vaadin.table.editor.traversal.*;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/5/12
 * Time: 12:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class EditableTableBuilder {

    private Gestures traversalGestures;

    private GridTraversalOrder horizontalTraversalOrder;

    private GridTraversalOrder verticalTraversalOrder;

    private CellGridId.Property currentCell = new CellGridId.Property();

    private void init() {
        traversalGestures = new Gestures.Default();
        verticalTraversalOrder = new VerticalTraversalOrder();
        horizontalTraversalOrder = new HorizontalTraversalOrder();
    }

    public EditableTableBuilder withTraversalGestures(Gestures gestures) {
        traversalGestures = gestures;
        return this;
    }

    public EditableTableBuilder withHorizontalTraversalOrder(GridTraversalOrder horizontalTraversalOrder) {
        this.horizontalTraversalOrder = horizontalTraversalOrder;
        return this;
    }

    public EditableTableBuilder withVerticalTraversalOrder(GridTraversalOrder verticalTraversalOrder) {
        this.verticalTraversalOrder = verticalTraversalOrder;
        return this;
    }







    /*
     user double clicks on cell
     cell opens
     user edits field
     user presses tab


     * either leave fields open if they are dirty,
     * or mark them with a color?
     *
     *
     *
     */

    public Table build() {
        final Table table = new Table() {
            @Override
            public void setContainerDataSource(Container newDataSource) {
                super.setContainerDataSource(newDataSource);    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override
            public void setTableFieldFactory(TableFieldFactory fieldFactory) {
                super.setTableFieldFactory(fieldFactory);    //To change body of overridden methods use File | Settings | File Templates.
            }
        };
        final CellGridId.Property currentCell = new CellGridId.Property();
        final Command leftCommand = new TraversalCommand(table, currentCell, horizontalTraversalOrder.reverse());
        final Command rightCommand = new TraversalCommand(table, currentCell, horizontalTraversalOrder);
        final Command upCommand = new TraversalCommand(table, currentCell, verticalTraversalOrder.reverse());
        final Command downCommand = new TraversalCommand(table, currentCell, verticalTraversalOrder);
        CommandActionHandler commandActionHandler = CommandActionHandler.create(
                new KeyBinding.DefaultImpl(traversalGestures.upGesture(), upCommand),
                new KeyBinding.DefaultImpl(traversalGestures.downGesture(), downCommand),
                new KeyBinding.DefaultImpl(traversalGestures.leftGesture(), leftCommand),
                new KeyBinding.DefaultImpl(traversalGestures.rightGesture(), rightCommand));
        table.addActionHandler(commandActionHandler);
        //double click listener should listen for clicks
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick() && currentCell.getValue() == null){
                    currentCell.setIds(event.getItemId(), event.getPropertyId());
                    table.setEditable(true);
                }else if (event.isDoubleClick()&& currentCell.getValue() != null){
                    currentCell.setCell(null);
                    table.setEditable(false);
                }else{

                }
            }
        });
        init();
        return table;
    }


}
