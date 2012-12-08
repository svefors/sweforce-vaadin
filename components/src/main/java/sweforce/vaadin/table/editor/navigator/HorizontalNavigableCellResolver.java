package sweforce.vaadin.table.editor.navigator;

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
public class HorizontalNavigableCellResolver implements NavigableCellResolver {

    private final Table table;

    private final boolean reverse;

    private final GridNavigatorControl.LeftRightOverflowMode leftRightOverflowMode;


    public HorizontalNavigableCellResolver(Table table, boolean reverse, GridNavigatorControl.LeftRightOverflowMode leftRightOverflowMode) {
        this.table = table;
        this.reverse = reverse;
        this.leftRightOverflowMode = leftRightOverflowMode;
    }

    public HorizontalNavigableCellResolver(Table table, GridNavigatorControl.LeftRightOverflowMode leftRightOverflowMode) {
        this(table, false, leftRightOverflowMode);
    }

    public HorizontalNavigableCellResolver(Table table, boolean reverse) {
        this(table, reverse, GridNavigatorControl.LeftRightOverflowMode.NEXT_ROW);
    }

    public HorizontalNavigableCellResolver(Table table) {
        this(table, false);
    }

    @Override
    public CellGridId nextId(CellGridId cellGridId) {
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
                        return nextId(new CellGridId(nextRowItemId, firstPropertyId));
                    }
                    return new CellGridId(nextRowItemId, firstPropertyId);
                }
                case SAME_ROW: {
                    Object firstPropertyId = cols.get(0);
                    if (table.getContainerProperty(cellGridId.itemId, firstPropertyId).isReadOnly()) {
                        return nextId((cellGridId.withPropertyId(firstPropertyId)));
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
            return nextId(cellGridId.withPropertyId(cols.get(startFrom + 1)));
        }
        return null;
    }


    @Override
    public NavigableCellResolver reverse() {
        return new sweforce.vaadin.table.editor.navigator.HorizontalNavigableCellResolver(table, !reverse);
    }
}
