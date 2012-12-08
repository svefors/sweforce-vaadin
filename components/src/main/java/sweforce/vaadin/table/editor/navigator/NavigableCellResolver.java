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
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface NavigableCellResolver {

    public CellGridId nextId(CellGridId cellGridId);

    public NavigableCellResolver reverse();

}
