package sweforce.vaadin.layout;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import sweforce.gui.display.Display;
import sweforce.gui.display.View;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/13/12
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class VerticalLayoutDisplay extends AbstractComponent implements Display{

    private final VerticalLayout rootLayout = new VerticalLayout();


    @Override
    public void setView(View view) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
