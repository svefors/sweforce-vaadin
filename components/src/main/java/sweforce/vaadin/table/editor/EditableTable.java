package sweforce.vaadin.table.editor;

import com.vaadin.data.Container;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import sweforce.vaadin.table.editor.traversal.*;

/**
 * A simple EditableTable
 */
public class EditableTable extends Table {

    public final CellGridId.Property currentCell = new CellGridId.Property();

    public final CellGridId.SetProperty dirtyCells = new CellGridId.SetProperty();



    public EditableTable(String caption) {
        super(caption);
    }

    public EditableTable(String caption, Container dataSource) {
        super(caption, dataSource);
    }

    public EditableTable() {
        super();
        //add the double click listener
        this.addItemClickListener(new GridClickListener(currentCell));
    }

    /**
     *
     * @param newDataSource
     */
    @Override
    public void setContainerDataSource(Container newDataSource) {
        super.setContainerDataSource(newDataSource);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private TraversalControl traversalControl;

    private Action.Handler traversalGestureHandler;

    public void useTraversal(Gestures gestures, GridTraversalOrder horisontalTraversalOrder, GridTraversalOrder verticalTraversalOrder){
        if(traversalControl!= null)
            traversalControl.unbind();
        traversalControl = new TraversalControl(verticalTraversalOrder, horisontalTraversalOrder);
        traversalControl.bind(this);
        if(traversalGestureHandler != null){
            this.removeActionHandler(traversalGestureHandler);
        }
        traversalGestureHandler = traversalControl.createActionHandler(gestures);
        this.addActionHandler(traversalGestureHandler);
    }

    private class WrappedFieldFactory implements TableFieldFactory {

        private TableFieldFactory inner;

        private WrappedFieldFactory(TableFieldFactory tableFieldFactory) {
            this.inner = tableFieldFactory;
        }

        @Override
        public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
            if(new CellGridId(itemId, propertyId).equals(currentCell.getValue())){
                return inner.createField(container, itemId, propertyId, uiContext);
            }else{
                return null;
            }
        }

    }

    @Override
    public void setTableFieldFactory(TableFieldFactory fieldFactory) {
        super.setTableFieldFactory(new WrappedFieldFactory(fieldFactory));
    }

    /**
     * behavior for dirty fields.
     *
     {
        boolean

     }
     *
     *
     *
     */





}
