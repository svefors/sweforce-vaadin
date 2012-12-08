package sweforce.vaadin.table.editor.fieldfactory;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 11/5/12
* Time: 12:13 AM
* To change this template use File | Settings | File Templates.
*/
public interface FieldCreationDiscriminator {

    public boolean passesDiscrimination(Container container, Object itemId, Object propertyId, Component uiContext);

    /*
    there is a better name for this concept

    expected behavior would be that the current Cell is done
     or that all properties that have been edited
     */

}
