package sweforce.gui.ap.web;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UriFragmentUtility;


/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/3/12
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BrowserWindow {

    public UriFragmentUtility getUriFragmentUtility();

    public void setContent(ComponentContainer componentContainer);
}
