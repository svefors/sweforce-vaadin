package sweforce.vaadin.table.editor.navigator;

import com.vaadin.data.Container;
import sweforce.command.Command;
import sweforce.vaadin.keyboard.CommandActionHandler;
import sweforce.vaadin.table.editor.CellGridId;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/26/12
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class GridNavigationViewModel {

    public final Command leftCommand;

    public final Command rightCommand;

    public final Command upCommand;

    public final Command downCommand;

    public GridNavigationViewModel(Container.Indexed container, CellGridId.Property currentCell, GridTraversalOrder verticalTraversalOrder, GridTraversalOrder horizontalTraversalOrder) {
        leftCommand = new TraversalCommand(container, currentCell, horizontalTraversalOrder.reverse());
        rightCommand = new TraversalCommand(container, currentCell, horizontalTraversalOrder);
        upCommand = new TraversalCommand(container, currentCell, verticalTraversalOrder.reverse());
        downCommand = new TraversalCommand(container, currentCell, verticalTraversalOrder);
    }


}
