package sweforce.vaadin.layout;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import sweforce.gui.display.Display;
import sweforce.gui.display.View;
import static sweforce.gui.display.VaadinView.Util.convertViewToComponent;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/13/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class VaadinComponentDisplay<T extends ComponentContainer> extends CustomComponent implements Display {

    private final T innerContainer;

    public VaadinComponentDisplay(T innerContainer) {
        this.innerContainer = innerContainer;
        setCompositionRoot(innerContainer);
        updateVisibility();

    }

    @Override
    public void setView(View view) {
        innerContainer.removeAllComponents();
        innerContainer.addComponent(convertViewToComponent(view));
        updateVisibility();
    }

    protected void updateVisibility(){
        if (innerContainer.getComponentCount() == 0){
            hide();
        }else{
            show();
        }
    }

    private boolean hidden;


    protected void hide(){
        if(!hidden){

        }
        hidden = true;
    }

    protected void show(){
        if(hidden){

        }
        hidden = false;
    }
}
