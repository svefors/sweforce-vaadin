package sweforce.vaadin.table.editor.navigator;

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

    public static enum LeftRightOverflowMode {
        NEXT_ROW, SAME_ROW, STOP
    }

    public static enum UpDownOverflowMode {
        ROLL_OVER, STOP
    }

    public void left();

    public void right();

    public void up();

    public void down();

}