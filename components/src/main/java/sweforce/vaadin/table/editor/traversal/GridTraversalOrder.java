package sweforce.vaadin.table.editor.traversal;

import com.vaadin.ui.Table;
import sweforce.vaadin.table.editor.CellGridId;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 12/1/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GridTraversalOrder {

    public boolean hasNext(Table table, CellGridId cellGridId);

    public CellGridId next(Table table, CellGridId cellGridId);

    public GridTraversalOrder reverse();

}
