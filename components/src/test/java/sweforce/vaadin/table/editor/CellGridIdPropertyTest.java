package sweforce.vaadin.table.editor;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class CellGridIdPropertyTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSetItemId_null() {
        CellGridId.Property property = new CellGridId.Property();
        CellGridId cellGridId = CellGridId.NULL.withItemId("itemId").withPropertyId("propertyId");
        cellGridId.withItemId(null);
    }

    public void testSetItemId() {
        CellGridId.Property property = new CellGridId.Property();
        CellGridId cellGridId = CellGridId.NULL.withItemId("itemId").withPropertyId("propertyId");
        cellGridId = cellGridId.withItemId("Asdf");
        assertEquals("Asdf", cellGridId.itemId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPropertyId_null() {
        CellGridId.Property property = new CellGridId.Property();
        CellGridId cellGridId = CellGridId.NULL.withItemId("itemId").withPropertyId("propertyId");
        cellGridId.withPropertyId(null);
    }

    public void testSetPropertyId(){
        CellGridId.Property property = new CellGridId.Property();
        CellGridId cellGridId = CellGridId.NULL.withItemId("itemId").withPropertyId("propertyId");
        cellGridId = cellGridId.withPropertyId("Asdf");
        assertEquals("Asdf", cellGridId.propertyId);
    }


}
