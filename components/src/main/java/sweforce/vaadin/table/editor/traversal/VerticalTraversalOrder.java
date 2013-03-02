package sweforce.vaadin.table.editor.traversal;

import com.vaadin.ui.Table;
import sweforce.vaadin.table.editor.CellGridId;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 11/4/12
* Time: 11:25 PM
* To change this template use File | Settings | File Templates.
*/
public class VerticalTraversalOrder implements GridTraversalOrder {

    private final boolean reverse;

    public VerticalTraversalOrder(boolean reverse) {
        this.reverse = reverse;
    }

    public VerticalTraversalOrder() {
        this(false);
    }

    @Override
    public boolean hasNext(Table orderedContainer, CellGridId cellGridId) {
        return next(orderedContainer, cellGridId) != null;
    }

    /**
     * by default
     * @param orderedContainer
     * @param cellGridId
     * @return
     */
    @Override
    public CellGridId next(Table orderedContainer, CellGridId cellGridId) {
        Object nextRowItemId = reverse ? orderedContainer.prevItemId(cellGridId.itemId) : orderedContainer.nextItemId(cellGridId.itemId);
        if (nextRowItemId == null)
            nextRowItemId = reverse ? orderedContainer.lastItemId() : orderedContainer.firstItemId();
        if (orderedContainer.getContainerDataSource().size() <= 1){
            return null;
        }
        if (!orderedContainer.getContainerProperty(nextRowItemId, cellGridId.propertyId).isReadOnly()) {
            return cellGridId.withItemId(nextRowItemId);
        }
        return next(orderedContainer, cellGridId.withItemId(nextRowItemId));
    }


    @Override
    public GridTraversalOrder reverse() {
        return new VerticalTraversalOrder(!reverse);
    }

    /**
    * Created with IntelliJ IDEA.
    * User: SERVICE-NOW\mats.svefors
    * Date: 09/12/2012
    * Time: 01:04
    * To change this template use File | Settings | File Templates.
    */
    public static enum UpDownOverflowMode {
        ROLL_OVER, STOP
    }
}
