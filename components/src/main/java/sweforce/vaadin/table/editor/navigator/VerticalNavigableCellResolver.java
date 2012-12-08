package sweforce.vaadin.table.editor.navigator;

import com.vaadin.ui.Table;
import sweforce.vaadin.table.editor.CellGridId;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 11/4/12
* Time: 11:25 PM
* To change this template use File | Settings | File Templates.
*/
public class VerticalNavigableCellResolver implements NavigableCellResolver {
    private final Table table;

    private final boolean reverse;

    private final GridNavigatorControl.UpDownOverflowMode upDownOverflowMode;

    public VerticalNavigableCellResolver(Table table, boolean reverse, GridNavigatorControl.UpDownOverflowMode upDownOverflowMode) {
        this.table = table;
        this.reverse = reverse;
        this.upDownOverflowMode = upDownOverflowMode;
    }

    public VerticalNavigableCellResolver(Table table) {
        this(table, false, GridNavigatorControl.UpDownOverflowMode.ROLL_OVER);
    }

    public VerticalNavigableCellResolver(Table table, GridNavigatorControl.UpDownOverflowMode upDownOverflowMode) {
        this(table, false, upDownOverflowMode);
    }

    @Override
    public CellGridId nextId(CellGridId cellGridId) {
        Object nextRowItemId = reverse ? table.prevItemId(cellGridId.itemId) : table.nextItemId(cellGridId.itemId);
        if (nextRowItemId == null)
            nextRowItemId = reverse ? table.lastItemId() : table.firstItemId();
        if (!table.getContainerProperty(nextRowItemId, cellGridId.propertyId).isReadOnly()) {
            return cellGridId.withItemId(nextRowItemId);
        }
        return nextId(cellGridId.withItemId(nextRowItemId));
    }

    public Object nextRowItemId(CellGridId cellGridId){
        return reverse ? table.prevItemId(cellGridId.itemId) : table.nextItemId(cellGridId.itemId);
    }

    public boolean hasNext(CellGridId cellGridId){
        return nextRowItemId(cellGridId) != null;
    }



    @Override
    public NavigableCellResolver reverse() {
        return new sweforce.vaadin.table.editor.navigator.VerticalNavigableCellResolver(table, !reverse, upDownOverflowMode);
    }
}
