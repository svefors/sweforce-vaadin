package sweforce.vaadin.layout.display;

import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Component;
import sweforce.gui.display.Display;
import sweforce.gui.display.View;

import static sweforce.gui.display.VaadinView.Util.convertViewToComponent;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplitPanelDisplay {

    private AbstractSplitPanel splitPanel;

    private Size size;

    private boolean minimized;

    public SplitPanelDisplay(AbstractSplitPanel splitPanel) {
        this.splitPanel = splitPanel;
        size = Size.getSize(splitPanel);
        minimize();
    }

    private void resizeIfNecessary(){
        if(minimized && splitPanel.getComponentCount() > 0){
            upsize();
        }else if (!minimized && splitPanel.getComponentCount() == 0){
            minimize();
        }
    }

    public class First implements Display {

        private Component component;

        @Override
        public void setView(View view) {
            Component newComponent = convertViewToComponent(view);
            if (component != null && newComponent == null) {
                splitPanel.removeComponent(component);
                //activate
            } else if (component != null) {
                splitPanel.replaceComponent(component, newComponent);
            } else {
                splitPanel.setFirstComponent(newComponent);
            }
            this.component = newComponent;
            resizeIfNecessary();
        }
    }

    public class Second implements Display {
        private Component component;

        @Override
        public void setView(View view) {
            Component newComponent = convertViewToComponent(view);
            if (component != null && newComponent == null) {
                splitPanel.removeComponent(component);

            } else if (component != null) {
                splitPanel.replaceComponent(component, newComponent);
            } else {
                splitPanel.setFirstComponent(newComponent);
            }
            this.component = newComponent;
            resizeIfNecessary();
        }
    }


    private void minimize() {
        splitPanel.setWidth("0px");
        splitPanel.setHeight("0px");
        minimized = true;
        splitPanel.setVisible(false);
    }

    private void upsize() {
        size.setSizeOn(splitPanel);
        minimized = false;
        splitPanel.setVisible(true);
    }
}
