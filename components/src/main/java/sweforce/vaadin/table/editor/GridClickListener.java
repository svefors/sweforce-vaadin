package sweforce.vaadin.table.editor;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;

/**
 */
public class GridClickListener implements ItemClickEvent.ItemClickListener {

    public final CellGridId.Property currentCell;

    public GridClickListener(CellGridId.Property currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (event.getSource() instanceof Table){
            Table table = ((Table) event.getSource());
            if (event.isDoubleClick()){
                currentCell.setCell(currentCell.getValue()
                        .withItemId(event.getItemId())
                        .withPropertyId(event.getPropertyId()));

                if (table.isEditable()){
                    table.setEditable(true);
                }
            }else if(!event.isDoubleClick() && table.isEditable()){
                table.setEditable(false);
            }
        }
    }
}
