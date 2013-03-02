package sweforce.vaadin.table.editor.traversal;

import com.vaadin.ui.Table;
import sweforce.vaadin.table.editor.CellGridId;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/4/12
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class HorizontalTraversalOrder implements GridTraversalOrder {

    private final boolean reverse;

    private final LeftRightOverflowMode leftRightOverflowMode;


    public HorizontalTraversalOrder(boolean reverse, LeftRightOverflowMode leftRightOverflowMode) {
        this.reverse = reverse;
        this.leftRightOverflowMode = leftRightOverflowMode;
    }

    public HorizontalTraversalOrder(LeftRightOverflowMode leftRightOverflowMode) {
        this(false, leftRightOverflowMode);
    }

    public HorizontalTraversalOrder() {
        this(false, LeftRightOverflowMode.NEXT_ROW);
    }

    @Override
    public boolean hasNext(Table table, CellGridId cellGridId) {
        return next(table, cellGridId) != null;
    }

    @Override
    public CellGridId next(Table table, CellGridId cellGridId) {
        List cols = Arrays.asList(table.getVisibleColumns());
        if (reverse) {
            Collections.reverse(cols);
        }
        int startFrom = cols.lastIndexOf(cellGridId.propertyId);
        if (startFrom == cols.size()) {
            //EOL
            switch (leftRightOverflowMode) {
                case NEXT_ROW: {
                    //next row, firstId
                    Object nextRowItemId = reverse ? table.prevItemId(cellGridId.itemId)
                            : table.nextItemId(cellGridId.itemId);
                    if (nextRowItemId == null)
                        nextRowItemId = reverse ? table.lastItemId() : table.firstItemId();
                    Object firstPropertyId = cols.get(0);
                    if (table.getContainerProperty(nextRowItemId, firstPropertyId).isReadOnly()) {
                        return next(table, new CellGridId(nextRowItemId, firstPropertyId));
                    }
                    return new CellGridId(nextRowItemId, firstPropertyId);
                }
                case SAME_ROW: {
                    Object firstPropertyId = cols.get(0);
                    if (table.getContainerProperty(cellGridId.itemId, firstPropertyId).isReadOnly()) {
                        return next(table, (cellGridId.withPropertyId(firstPropertyId)));
                    }
                    return cellGridId.withPropertyId(firstPropertyId);
                }
                case STOP: {
                    return cellGridId;
                }
            }
        } else if (!table.getContainerProperty(cellGridId.itemId, cols.get(startFrom + 1)).isReadOnly()) {
            return cellGridId.withPropertyId(cols.get(startFrom + 1));
        } else {
            return next(table, cellGridId.withPropertyId(cols.get(startFrom + 1)));
        }
        return null;
    }


    @Override
    public GridTraversalOrder reverse() {
        return new HorizontalTraversalOrder(!reverse, leftRightOverflowMode);
    }

    /**
    * Created with IntelliJ IDEA.
    * User: SERVICE-NOW\mats.svefors
    * Date: 09/12/2012
    * Time: 01:03
    * To change this template use File | Settings | File Templates.
    */
    public static enum LeftRightOverflowMode {
        NEXT_ROW, SAME_ROW, STOP
    }
}
