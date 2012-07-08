package sweforce.gui.ap.vaadin;

import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Root;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/2/12
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActivitiesAndPlacesRoot extends Root {


    public ActivitiesAndPlacesRoot() {

    }

    @Override
    protected void init(WrappedRequest request) {
        getApplication().setRootPreserved(true);
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
