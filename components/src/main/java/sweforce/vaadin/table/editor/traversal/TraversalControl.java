package sweforce.vaadin.table.editor.traversal;

import com.vaadin.event.Action;
import com.vaadin.ui.Table;
import sweforce.vaadin.keyboard.CommandActionHandler;
import sweforce.vaadin.keyboard.KeyBinding;
import sweforce.vaadin.table.editor.CellGridId;
import sweforce.vaadin.table.editor.EditableTable;

/**
 *
 */
public class TraversalControl {

    public final TraversalCommand upCommand;

    public final TraversalCommand downCommand;

    public final TraversalCommand leftCommand;

    public final TraversalCommand rightCommand;

    public TraversalControl(GridTraversalOrder verticalTraversalOrder, GridTraversalOrder horizontalTraversalOrder) {
        upCommand = new TraversalCommand(verticalTraversalOrder);
        downCommand = new TraversalCommand(verticalTraversalOrder.reverse());
        rightCommand = new TraversalCommand(horizontalTraversalOrder);
        leftCommand = new TraversalCommand(horizontalTraversalOrder.reverse());
    }

    public void bind(EditableTable editableTable){
        upCommand.bind(editableTable);
        downCommand.bind(editableTable);
        leftCommand.bind(editableTable);
        rightCommand.bind(editableTable);
    }

    public void unbind(){
        upCommand.unbind();
        downCommand.unbind();
        rightCommand.unbind();
        leftCommand.unbind();
    }

    public Action.Handler createActionHandler(Gestures traversalGestures) {
        return CommandActionHandler.create(
                new KeyBinding.DefaultImpl(traversalGestures.upGesture(), upCommand),
                new KeyBinding.DefaultImpl(traversalGestures.downGesture(), downCommand),
                new KeyBinding.DefaultImpl(traversalGestures.leftGesture(), leftCommand),
                new KeyBinding.DefaultImpl(traversalGestures.rightGesture(), rightCommand));
    }
}
