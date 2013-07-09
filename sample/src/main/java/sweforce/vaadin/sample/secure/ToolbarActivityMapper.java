package sweforce.vaadin.sample.secure;

import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutPlace;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/9/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToolbarActivityMapper implements ActivityMapper {

    private MenuActivity menuActivity;

    public ToolbarActivityMapper(MenuActivity menuActivity) {
        this.menuActivity = menuActivity;
    }

    @Override
    public Activity getActivity(Place place) {
        if(!(place instanceof LoginPlace || place instanceof LogoutPlace))
            return menuActivity;
        return null;
    }
}
