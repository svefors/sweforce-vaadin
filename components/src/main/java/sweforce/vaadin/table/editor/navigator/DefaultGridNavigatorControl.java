package sweforce.vaadin.table.editor.navigator;

import com.vaadin.ui.Table;
import sweforce.vaadin.table.editor.CellGridId;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/4/12
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultGridNavigatorControl implements GridNavigatorControl {

    private CellGridId.Property selectedCell = new CellGridId.Property();

    private final NavigableCellResolver verticalCellResolver;

    private final NavigableCellResolver horizontalCellResolver;

    public static DefaultGridNavigatorControl forTable(Table table){
        return new DefaultGridNavigatorControl(new VerticalNavigableCellResolver(table),
                new HorizontalNavigableCellResolver(table));
    }

    public DefaultGridNavigatorControl(NavigableCellResolver verticalCellResolver, NavigableCellResolver horizontalCellResolver) {
        this.verticalCellResolver = verticalCellResolver;
        this.horizontalCellResolver = horizontalCellResolver;
    }


    public void bind(CellGridId.Property selectedCell){
        this.selectedCell = selectedCell;
    }



    @Override
    public void left() {
        if (this.selectedCell.getValue() == null) {
            return;
        }
        CellGridId cellGridId = horizontalCellResolver.reverse().nextId(this.selectedCell.getValue());
        this.selectedCell.setValue(cellGridId);
    }


    @Override
    public void right() {
        if (this.selectedCell.getValue() == null) {
            return;
        }
        CellGridId cellGridId = horizontalCellResolver.nextId(this.selectedCell.getValue());
        this.selectedCell.setValue(cellGridId);

    }

    @Override
    public void up() {
        if (this.selectedCell.getValue() == null) {
            return;
        }
        CellGridId cellGridId = verticalCellResolver.nextId(this.selectedCell.getValue());
        this.selectedCell.setValue(cellGridId);
    }

    @Override
    public void down() {
        if (this.selectedCell.getValue() == null) {
            return;
        }
        CellGridId cellGridId = verticalCellResolver.reverse().nextId(this.selectedCell.getValue());
        this.selectedCell.setValue(cellGridId);
    }
}
