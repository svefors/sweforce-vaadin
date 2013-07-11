package sweforce.vaadin.sample.secure;

import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.region.RegionalActivityFactoryRegistry;
import sweforce.vaadin.layout.style1.Style1Layout;

/**
 * Created with IntelliJ IDEA.
 * User: msvefors
 * Date: 7/11/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityMapper {

    private RegionalActivityFactoryRegistry regionalActivityFactoryRegistry;

    public MainActivityMapper(RegionalActivityFactoryRegistry regionalActivityFactoryRegistry, Plugin[] plugins) {
        this.regionalActivityFactoryRegistry = regionalActivityFactoryRegistry;
        for(Plugin plugin : plugins){
            regionalActivityFactoryRegistry.plugin(Style1Layout.MyRegion.MAIN, plugin);
        }
    }

    /**
     * marker interface
     */
    public static interface Plugin extends ActivityFactoryRegistry.Plugin{

    }
}
