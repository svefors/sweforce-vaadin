package sweforce.vaadin.table.editor.traversal;

import com.vaadin.ui.Table;
import sweforce.vaadin.table.editor.CellGridId;

/**
 *
 */
public interface GridTraversalOrder {

    /**
     * TODO: replace table with
     * @param table
     * @param cellGridId
     * @return
     */
    public boolean hasNext(Table table, CellGridId cellGridId);

    public CellGridId next(Table table, CellGridId cellGridId);

    public GridTraversalOrder reverse();

}
