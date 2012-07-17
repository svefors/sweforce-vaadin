package sweforce.vaadin.layout.style2;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/16/12
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayBehaviorChain {

    private DisplayBehaviorChain displayBehavior;

    public void changeComponent(ComponentContainer componentContainer,
                        Component oldComponent, Component newComponent){
        displayBehavior.changeComponent(componentContainer, oldComponent, newComponent);

    }


}
