package sweforce.vaadin.layout.style2;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import sweforce.gui.display.Display;
import sweforce.gui.display.View;

import static sweforce.gui.vaadin.VaadinView.Util.convertViewToComponent;

/**
 * Contains a single component.
 */
public class SingleComponentDisplay implements Display {

    private ComponentContainer container;

    private Size size;

    private Component component;

    private boolean minimized;

    public SingleComponentDisplay(ComponentContainer container) {
        this.container = container;
        size = Size.getSize(container);
        minimize();
    }

    @Override
    public void setView(View view) {
        Component newComponent = convertViewToComponent(view);
        if (component != null && newComponent == null) {
            container.removeAllComponents();
            //activate
        } else if (component != null) {
            container.replaceComponent(component, newComponent);
        } else {
            container.addComponent(newComponent);
        }
        this.component = newComponent;
        resizeIfNecessary();
    }

    private void minimize() {
        container.setWidth("0px");
        container.setHeight("0px");
        minimized = true;
        container.setVisible(false);

    }

    private void upsize() {
        size.setSizeOn(container);
        minimized = false;
        container.setVisible(true);
    }

    private void resizeIfNecessary() {
        if (minimized && container.getComponentCount() == 1) {
            upsize();
        } else if (!minimized && container.getComponentCount() == 0) {
            minimize();
        }
    }
}
