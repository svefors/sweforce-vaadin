package sweforce.vaadin.table.editor;

import com.vaadin.ui.Table;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/3/12
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GridNavigatorControl {

    public static enum LeftRightOverflowMode{
        NEXT_ROW, SAME_ROW, STOP
    }

    public static enum UpDownOverflowMode{
        ROLL_OVER, STOP
    }


    public void setLeftRightOverflowMode(LeftRightOverflowMode leftRightOverflowMode);

    public void setUpDownOverflowMode(UpDownOverflowMode upDownOverflowMode);

    /*

     */

    public void left();

    public void right();

    public void up();

    public void down();

    public static class DefaultGridNavigatorControl implements GridNavigatorControl {

        private LeftRightOverflowMode leftRightOverflowMode;

        private UpDownOverflowMode upDownOverflowMode;

        private final Table table;

        private final CellGridId.Property selectedCell;

        public DefaultGridNavigatorControl(Table table, CellGridId.Property selectedCell) {
            this.table = table;
            this.selectedCell = selectedCell;
            bind();
        }

        public void setLeftRightOverflowMode(LeftRightOverflowMode leftRightOverflowMode) {
            this.leftRightOverflowMode = leftRightOverflowMode;
        }

        public void setUpDownOverflowMode(UpDownOverflowMode upDownOverflowMode) {
            this.upDownOverflowMode = upDownOverflowMode;
        }

        private void bind(){

        }

        private void unbind(){

        }

        @Override
        protected void finalize() throws Throwable {
            unbind();
            super.finalize();
        }


        public Object nextEditableCellGridId(boolean reverse){
            List cols = Arrays.asList(table.getVisibleColumns());
            if (reverse)
                Collections.reverse(cols);
            int index = cols.indexOf(this.selectedCell.getValue().propertyId);
            if (index == -1)
                return null;
            // 4 size will return index 3 if last
            Object nextPropertyId;
            if(index == cols.size() -1 ){
                //we are at last cell. must start over
                nextPropertyId = cols.get(0);
            }else{
                nextPropertyId = cols.get(index++);
            }

            for (Object col : cols){
                if(leftRightOverflowMode == LeftRightOverflowMode.STOP){
                    return this.selectedCell.getValue();
                }else{

                }
            }
        }


        private boolean hasLeft(){
            List cols = Arrays.asList(table.getVisibleColumns());
            if (cols.size()< 2)
                return false;
            int index = cols.indexOf(this.selectedCell.getValue().propertyId);


            boolean readOnly = table.getContainerProperty(selectedCell.getValue().itemId, selectedCell.getValue().propertyId).isReadOnly();

            return true;
        }

        private boolean hasAnotherEditableRight(){
            return true;
        }

        private boolean hasUp(){
            return true;
        }

        private boolean hasDown(){
            return true;
        }

        @Override
        public void left() {
            /*
            default behavior, if end of row
             */
//            table.getVisibleColumns();

        }

        @Override
        public void right() {
            CellGridId adjacentCellGridId = adjacentCellGridId()
            if()
            if (hasAnotherEditableRight())

            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void up() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void down() {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
