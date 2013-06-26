package sweforce.gui.display.region;

import sweforce.gui.activity.ActivityMapper;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 3/2/13
* Time: 10:19 PM
* To change this template use File | Settings | File Templates.
*/
@Deprecated
public interface RegionActivityMapperFactory {
    public ActivityMapper create(Region region);
}
