package sweforce.vaadin.table.editor.navigator;

import com.vaadin.data.Container;
import sweforce.vaadin.table.editor.CellGridId;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 12/1/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GridTraversalOrder {

    public boolean hasNext(Container.Ordered orderedContainer, CellGridId cellGridId);

    public CellGridId next(Container.Ordered orderedContainer, CellGridId cellGridId);

    public GridTraversalOrder reverse();


}
