package sweforce.vaadin.table.editor;

import com.vaadin.ui.Table;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.Test;
import sweforce.vaadin.table.editor.traversal.GridTraversalOrder;
import sweforce.vaadin.table.editor.traversal.TraversalCommand;
import sweforce.vaadin.table.editor.traversal.VerticalTraversalOrder;

/**
 * Test for the TraversalCommand
 */
public class TraversalCommandTest {

    private Table table;

    private CellGridId.Property currentCell;

    private GridTraversalOrder gridTraversal;

    private TraversalCommand command;



    @Before
    public void setUp() throws Exception {
        table = new Table();
        currentCell = new CellGridId.Property();
        gridTraversal = new VerticalTraversalOrder();
        table.addContainerProperty("a", String.class, "");
        table.addContainerProperty("b", Integer.class, 0);
        table.addContainerProperty("c", Integer.class, 0);
        table.addItem("item1");
        table.addItem("item2");
        currentCell.setCell(CellGridId.NULL.withItemId("item1").withPropertyId("a"));
//        command = new TraversalCommand(table, currentCell, gridTraversal);
    }

    @Test
    public void testCurrentCellNull(){
        assertTrue(command.isExecutable());
        currentCell.setCell(CellGridId.NULL);
        assertFalse(command.isExecutable());
    }

    @Test
    public void testContainerEmpty(){
        assertTrue(command.isExecutable());
        table.removeAllItems();
        assertFalse(command.isExecutable());
    }

}
