package sweforce.gui.region;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 6/30/13
 * Time: 2:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegionalPresenterModule extends AbstractModule{



    @Override
    protected void configure() {
        bind(RegionalDisplayPresenter.class);
    }


}
